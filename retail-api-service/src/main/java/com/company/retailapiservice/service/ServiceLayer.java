package com.company.retailapiservice.service;

import com.company.retailapiservice.model.*;
import com.company.retailapiservice.util.feign.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceLayer {

    private CustomerClient customerClient;
    private InventoryClient inventoryClient;
    private InvoiceClient invoiceClient;
    private LevelUpClient levelUpClient;
    private ProductClient productClient;


    @Autowired
    ServiceLayer(CustomerClient customerClient, InventoryClient inventoryClient, InvoiceClient invoiceClient,
                 LevelUpClient levelUpClient, ProductClient productClient) {
        this.customerClient = customerClient;
        this.inventoryClient = inventoryClient;
        this.invoiceClient = invoiceClient;
        this.levelUpClient = levelUpClient;
        this.productClient = productClient;
    }

    @Transactional
    public InvoiceViewModel processOrder(@Valid OrderViewModel orderViewModel) {
        System.out.println(orderViewModel);
        InvoiceViewModel invoice = new InvoiceViewModel();
        invoice.setCustomerId(orderViewModel.getCustomerId());
        invoice.setPurchaseDate(LocalDate.now());
        List<InvoiceItem> invoiceItemList = buildInvoiceItemListFromProductList(orderViewModel.getProducts());
        System.out.println(invoiceItemList);
        invoice.setInvoiceItemList(invoiceItemList);
        invoice = invoiceClient.submitInvoice(invoice);
//        total quantity in cart
        int totalQuantity = 0;
        for (InvoiceItem invoiceItem : invoiceItemList) {
            totalQuantity += invoiceItem.getQuantity();
        }
        invoice.setTotalProductsPurchased(totalQuantity);

//order total
        BigDecimal orderTotal = calculateOrderTotal(invoiceItemList);
        invoice.setTotalPriceAmount(orderTotal);
        Customer customer = customerClient.getCustomerById(orderViewModel.getCustomerId());
        invoice.setCustomer(customer);

        int pointsEarned = calculateOrderLevelUpPoints(orderTotal);
        LevelUp levelUp = new LevelUp();
        levelUp.setCustomerId(customer.getId());
        levelUp.setPoints(pointsEarned);
        levelUp.setMemberDate(invoice.getPurchaseDate());
//        must go thru a queue
        addLevelUpPoints(levelUp);

        invoice.setLevelUpPointsEarnedWithThisOrder(pointsEarned);

//        must be wrapped with circuit breaker patter
        int totalPointsOfCustomer = getTotalLevelUpPointsByCustomerId(customer.getId());
        invoice.setTotalLevelUpPointsInAccount(totalPointsOfCustomer);

        return invoice;
    }

    @Transactional
    public List<ProductViewModel> getAllProducts() {
        return productClient.getAllProducts();
    }

    public ProductViewModel getProductById(int id) {
        return productClient.getProductById(id);
    }

    public List<InvoiceViewModel> getInvoicesByCustomerId(int id) {
        return invoiceClient.getAllInvoices();
    }

//    queue and circuit breaker set up in util.service.LevelUpService;
    @HystrixCommand(fallbackMethod = "reliable")
    public int getTotalLevelUpPointsByCustomerId(int customerId) {
        return levelUpClient.getLevelUpPointsByCustomerId(customerId).getPoints();
    }

    public int reliable() {
        return 0000;
    }

//    must go to a queue
    public LevelUp addLevelUpPoints(LevelUp levelUp) {
        return levelUpClient.postLevelUpPoints(levelUp);
    }

    public BigDecimal calculateOrderTotal(List<InvoiceItem> invoiceItemList) {
        BigDecimal totalPrice = new BigDecimal(0);

        for (InvoiceItem invoiceItem : invoiceItemList) {
            BigDecimal invoiceItemPrice = invoiceItem.getUnitPrice().multiply(new BigDecimal(invoiceItem.getQuantity()));
            totalPrice = totalPrice.add(invoiceItemPrice);
        }
        return totalPrice;
    }

    public int calculateOrderLevelUpPoints(BigDecimal purchasedAmount) {
//        int result = (purchasedAmount.remainder(new BigDecimal(50)).intValue() + 10);
        int pointsEarned = 0;
        if (purchasedAmount.intValue() < 50) return 0;
        for (int i = 50; i <= purchasedAmount.intValue(); i += 50) {
            if (i < purchasedAmount.intValue()) {
                pointsEarned += 10;
            }
        }
        return pointsEarned;
    }


//    this method also checks to make sure enough quantity is in db
    public List<InvoiceItem> buildInvoiceItemListFromProductList(List<Product> productList) {
        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        for (Product product : productList) {

            Inventory inventoryEntry = inventoryClient.getInventoryEntryByProductId(product.getProductId());
            if (product.getQuantityToPurchase() > inventoryEntry.getQuantity()) {
                throw new IllegalArgumentException("not enough stock in inventory for product with Id: " + product.getProductId());
            }
            inventoryEntry.setQuantity(inventoryEntry.getQuantity() - product.getQuantityToPurchase());
            inventoryClient.updateInventory(inventoryEntry);

            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setInventoryId(inventoryEntry.getInventoryId());
            invoiceItem.setQuantity(product.getQuantityToPurchase());
            invoiceItem.setUnitPrice(productClient.getProductById(product.getProductId()).getList_price());

            invoiceItemList.add(invoiceItem);
        }
        return invoiceItemList;
    }

//    private OrderViewModel buildOrderViewModel(InvoiceViewModel invoice) {
//        OrderViewModel order = new OrderViewModel();
//        order.setOrderId(invoice.getInvoiceId());
//        order.setCustomerId(invoice.getCustomerId());
//        order.setCustomerObj(customerClient.getCustomerById(invoice.getCustomerId()));
//        order.setPurchaseDate(invoice.getPurchaseDate());
//
////        calculate total products in cart
//        int totalQuantityInOrder = 0;
//        List<InvoiceItem> invoiceItemList = invoice.getInvoiceItemList();
//        for (InvoiceItem item : invoiceItemList) {
//            totalQuantityInOrder += item.getQuantity();
//        }
//
//        order.setTotalProductsPurchased(totalQuantityInOrder);
//        order.set
//    }
}

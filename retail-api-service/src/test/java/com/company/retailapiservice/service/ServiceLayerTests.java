package com.company.retailapiservice.service;

import com.company.retailapiservice.exception.NotFoundException;
import com.company.retailapiservice.model.*;
import com.company.retailapiservice.util.feign.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ServiceLayerTests {

    @Mock
    CustomerClient customerClient;

    @Mock
    InventoryClient inventoryClient;

    @Mock
    InvoiceClient invoiceClient;

    @Mock
    LevelUpClient levelUpClient;

    @Mock
    ProductClient productClient;

    ServiceLayer service;

    private Customer customer;
    private Inventory inventory1;
    private Inventory inventory2;
    private InvoiceViewModel invoiceViewModel;
    private InvoiceViewModel invoiceViewModel2;
    private LevelUp levelUp;
    private ProductViewModel product1;
    private ProductViewModel product2;
    private OrderViewModel order1;

    @Before
    public void setUp() {
        setUpCustomerClientMock();
        setUpInventoryClientMock();
        setUpInvoiceClientMock();
        setUpLevelUpClientMock();
        setUpProductClientMock();

        service = new ServiceLayer(customerClient, inventoryClient, invoiceClient, levelUpClient, productClient);

        customer = new Customer();
        customer.setId(1);
        customer.setFirstName("harsh");
        customer.setLastName("patel");
        customer.setStreet("88 main st");
        customer.setCity("matawan");
        customer.setZip("07747");
        customer.setEmail("harsh@yahoo.com");
        customer.setPhone("19408327412");

    }


    @Test
    public void shouldReturnAllProductsInDb() {
        product1 = new ProductViewModel();
        product1.setId(5);
        product1.setName("notebook");
        product1.setDescription("200 pg composition notebook");
        product1.setList_price(new BigDecimal(12.99).setScale(2, RoundingMode.FLOOR));
        product1.setUnit_cost(new BigDecimal(2.99).setScale(2, RoundingMode.FLOOR));

        product2 = new ProductViewModel();
        product2.setId(4);
        product2.setName("calculator");
        product2.setDescription("texas instrumental graphing calculator");
        product2.setList_price(new BigDecimal(22.99).setScale(2, RoundingMode.FLOOR));
        product2.setUnit_cost(new BigDecimal(18.99).setScale(2, RoundingMode.FLOOR));

        List<ProductViewModel> productViewModelList = new ArrayList<>();
        productViewModelList.add(product1);
        productViewModelList.add(product2);

        assertEquals(productViewModelList, service.getAllProducts());
    }

//    @Test
//    public void shouldProcessAndReturnOrderWithTotalPriceAndLevelUpPointsCalculated() {
//        order1 = new OrderViewModel();
//        order1.setCustomerId(customer.getCustomerId);
//
////        add products to order
//        List<Product> productList = new ArrayList<>();
//        Product product1 = new Product();
//        product1.setProductId(5);
//        product1.setQuantityToPurchase(12);
//
//        Product product2 = new Product();
//        product2.setProductId(4);
//        product2.setQuantityToPurchase(8);
//
//        productList.add(product1);
//        productList.add(product2);
//        order1.setProducts(productList);
//
//        InvoiceViewModel expectedInvoice = new InvoiceViewModel();
//        expectedInvoice.setInvoiceId(1);
//        expectedInvoice.setCustomerId(1);
//        expectedInvoice.setCustomer(customer);
//        expectedInvoice.setPurchaseDate(LocalDate.now());
//
//        List<InvoiceItem> invoiceItemList = new ArrayList<>();
//        InvoiceItem invoiceItem1 = new InvoiceItem();
//        invoiceItem1.setInvoiceItemId(1);
//        invoiceItem1.setInvoiceId(1);
//        invoiceItem1.setInventoryId(9);
//        invoiceItem1.setQuantity(12);
//        invoiceItem1.setUnitPrice(new BigDecimal(22.99).setScale(2, RoundingMode.FLOOR));
//
//        InvoiceItem invoiceItem2 = new InvoiceItem();
//        invoiceItem2.setInvoiceItemId(2);
//        invoiceItem2.setInvoiceId(1);
//        invoiceItem2.setInventoryId(7);
//        invoiceItem2.setQuantity(8);
//        invoiceItem2.setUnitPrice(new BigDecimal(12.99).setScale(2, RoundingMode.FLOOR));
//
//        invoiceItemList.add(invoiceItem1);
//        invoiceItemList.add(invoiceItem2);
//
//        expectedInvoice.setInvoiceItemList(invoiceItemList);
//        expectedInvoice.setTotalProductsPurchased(20);
//        expectedInvoice.setTotalPriceAmount(new BigDecimal(52.99));
//        expectedInvoice.setLevelUpPointsEarnedWithThisOrder(20);
//        expectedInvoice.setTotalLevelUpPointsInAccount(20);
//
//        assertEquals(expectedInvoice, service.processOrder(order1));
//    }

    public void setUpCustomerClientMock() {
        List<Customer> customerList = new ArrayList<>(Collections.singletonList(customer));

        when(customerClient.getCustomerById(1)).thenReturn(customer);
        when(customerClient.getAllCustomers()).thenReturn(customerList);
        when(customerClient.getCustomerById(99)).thenThrow(new NotFoundException("no customer in db with that id"));
    }

    public void setUpInventoryClientMock() {
        inventory1 = new Inventory();
        inventory1.setInventoryId(9);
        inventory1.setProductId(5);
        inventory1.setQuantity(24);

        inventory2 = new Inventory();
        inventory2.setInventoryId(7);
        inventory2.setProductId(4);
        inventory2.setQuantity(12);

        List<Inventory> inventoryList = new ArrayList<>();
        inventoryList.add(inventory1);
        inventoryList.add(inventory2);

        when(inventoryClient.getAllInventoryEntries()).thenReturn(inventoryList);
        when(inventoryClient.getInventoryEntryByProductId(4)).thenReturn(inventory2);
        when(inventoryClient.getInventoryEntryByProductId(5)).thenReturn(inventory1);
        when(inventoryClient.getInventoryEntryByProductId(14)).thenThrow(new NotFoundException("no entry in inventory" +
                " with that product id"));

    }

    public void setUpInvoiceClientMock() {
        invoiceViewModel2 = new InvoiceViewModel();
        invoiceViewModel2.setCustomerId(1);
        invoiceViewModel2.setPurchaseDate(LocalDate.of(2019, 10, 11));
        List<InvoiceItem> invoiceItemListGoingIn = new ArrayList<>();
        InvoiceItem invoiceItem3 = new InvoiceItem();
        invoiceItem3.setInventoryId(9);
        invoiceItem3.setQuantity(13);
        invoiceItem3.setUnitPrice(new BigDecimal(22.99).setScale(2, RoundingMode.FLOOR));

        InvoiceItem invoiceItem4 = new InvoiceItem();
        invoiceItem4.setInventoryId(7);
        invoiceItem4.setQuantity(10);
        invoiceItem4.setUnitPrice(new BigDecimal(12.99).setScale(2, RoundingMode.FLOOR));

        invoiceItemListGoingIn.add(invoiceItem3);
        invoiceItemListGoingIn.add(invoiceItem4);

        invoiceViewModel2.setInvoiceItemList(invoiceItemListGoingIn);


//        invoice model to return once saved
        invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setInvoiceId(1);
        invoiceViewModel.setCustomerId(1);
        invoiceViewModel.setPurchaseDate(LocalDate.of(2019, 10, 11));

//        create invoice items
        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        InvoiceItem invoiceItem1 = new InvoiceItem();
        invoiceItem1.setInvoiceItemId(12);
        invoiceItem1.setInvoiceId(1);
        invoiceItem1.setInventoryId(9);
        invoiceItem1.setQuantity(13);
        invoiceItem1.setUnitPrice(new BigDecimal(22.99).setScale(2, RoundingMode.FLOOR));

        InvoiceItem invoiceItem2 = new InvoiceItem();
        invoiceItem2.setInvoiceItemId(13);
        invoiceItem2.setInvoiceId(1);
        invoiceItem2.setInventoryId(7);
        invoiceItem2.setQuantity(10);
        invoiceItem2.setUnitPrice(new BigDecimal(12.99).setScale(2, RoundingMode.FLOOR));

        invoiceItemList.add(invoiceItem1);
        invoiceItemList.add(invoiceItem2);

        invoiceViewModel.setInvoiceItemList(invoiceItemList);

        List<InvoiceViewModel> invoiceViewModelList = new ArrayList<>(Collections.singletonList(invoiceViewModel));

        when(invoiceClient.submitInvoice(invoiceViewModel2)).thenReturn(invoiceViewModel);
        when(invoiceClient.getAllInvoices()).thenReturn(invoiceViewModelList);
        when(invoiceClient.getInvoicesByCustomerId(1)).thenReturn(invoiceViewModelList);
        when(invoiceClient.getInvoicesByCustomerId(77)).thenThrow(new NotFoundException("no invoices in db " +
                "associated with that customer id"));
    }

    public void setUpLevelUpClientMock() {
        levelUp = new LevelUp();
        levelUp.setCustomerId(1);
        levelUp.setPoints(80);
        levelUp.setMemberDate(LocalDate.of(2014, 8, 12));

        LevelUp levelUp1 = new LevelUp();
        levelUp1.setLevelUpId(1);
        levelUp1.setCustomerId(1);
        levelUp1.setPoints(80);
        levelUp1.setMemberDate(LocalDate.of(2014, 8, 12));


        when(levelUpClient.postLevelUpPoints(levelUp)).thenReturn(levelUp1);
        when(levelUpClient.getLevelUpPointsByCustomerId(1)).thenReturn(levelUp1);
    }

    public void setUpProductClientMock() {
        product1 = new ProductViewModel();
        product1.setId(5);
        product1.setName("notebook");
        product1.setDescription("200 pg composition notebook");
        product1.setList_price(new BigDecimal(12.99).setScale(2, RoundingMode.FLOOR));
        product1.setUnit_cost(new BigDecimal(2.99).setScale(2, RoundingMode.FLOOR));

        product2 = new ProductViewModel();
        product2.setId(4);
        product2.setName("calculator");
        product2.setDescription("texas instrumental graphing calculator");
        product2.setList_price(new BigDecimal(22.99).setScale(2, RoundingMode.FLOOR));
        product2.setUnit_cost(new BigDecimal(18.99).setScale(2, RoundingMode.FLOOR));

        List<ProductViewModel> productViewModelList = new ArrayList<>();
        productViewModelList.add(product1);
        productViewModelList.add(product2);

        when(productClient.getAllProducts()).thenReturn(productViewModelList);
        when(productClient.getProductById(5)).thenReturn(product1);
        when(productClient.getProductById(4)).thenReturn(product2);
        when(productClient.getProductById(77)).thenThrow(new NotFoundException("no product in db with that id"));
    }

}

package com.company.adminservice.service;

import com.company.adminservice.feign.*;
import com.company.adminservice.util.message.*;
import com.company.adminservice.viewModel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AdminServiceLayer {

    @Autowired
    CustomerFeign customerFeign;

    @Autowired
    ProductFeign productFeign;

    @Autowired
    InventoryFeign inventoryFeign;

    @Autowired
    InvoiceFeign invoiceFeign;

    @Autowired
    LevelUpFeign levelUpFeign;

    public AdminServiceLayer(CustomerFeign customerFeign, ProductFeign productFeign, InventoryFeign inventoryFeign, InvoiceFeign invoiceFeign, LevelUpFeign levelUpFeign) {
        this.customerFeign = customerFeign;
        this.productFeign = productFeign;
        this.inventoryFeign = inventoryFeign;
        this.invoiceFeign = invoiceFeign;
        this.levelUpFeign = levelUpFeign;
    }

    //Product Layer
    public ProductViewModel getProduct(int id) throws IOException {

        ProductClient productClient = productFeign.getProduct(id);
        return createProductViewModel(productClient);
    }

    public List<ProductViewModel> getAllProducts() throws IOException {
        List<ProductClient> productClientList = productFeign.getAllProducts();

        List<ProductViewModel> productViewModelList = new ArrayList<>();

        for(ProductClient productClient: productClientList){
            productViewModelList.add(createProductViewModel(productClient));
        }

        return productViewModelList;
    }

    public ProductViewModel createProduct(ProductClient productClient) throws IOException{
        productClient = productFeign.createProduct(productClient);

        return createProductViewModel(productClient);
    }

    public List<ProductViewModel> getAllProductsByInventory() throws IOException{
        List<InventoryClient> inventoryClientList = inventoryFeign.getAllInventoryEntries();

        List<ProductViewModel> productViewModelList = new ArrayList<>();

        for(InventoryClient inventoryClient: inventoryClientList){
            productViewModelList.add(createProductViewModel(productFeign.getProduct(inventoryClient.getProductId())));
        }

        return productViewModelList;
    }

    public void deleteProduct(int id) throws IOException{
        productFeign.deleteProduct(id);
    }

    public void updateProduct(ProductClient productClient) throws IOException{
        productFeign.updateProduct(productClient);
    }

    //Customer Layer
    public CustomerViewModel getCustomer(int id) throws IOException {
        CustomerClient customerClient = customerFeign.getCustomer(id);
        return createCustomerViewModel(customerClient);
    }

    public List<CustomerViewModel> getAllCustomers() throws IOException{
        List<CustomerClient> customerClientList = customerFeign.getAllCustomer();

        List<CustomerViewModel> customerViewModelList = new ArrayList<>();

        for(CustomerClient customerClient: customerClientList){
            customerViewModelList.add(createCustomerViewModel(customerClient));
        }

        return customerViewModelList;
    }

    public CustomerViewModel createCustomer(CustomerClient customerClient) throws IOException{
        customerClient = customerFeign.createCustomer(customerClient);

        return createCustomerViewModel(customerClient);
    }

    public void deleteCustomer(int id) throws IOException{
        customerFeign.deleteCustomer(id);
    }

    public void updateCustomer(CustomerClient customerClient) throws IOException{
        customerFeign.updateCustomer(customerClient);
    }

    public InventoryViewModel getInventory(int id) throws IOException{
        InventoryClient inventoryClient = inventoryFeign.getInventory(id);
        return createInventoryViewModel(inventoryClient);
    }

    public List<InventoryViewModel> getAllInventory() throws IOException{
        List<InventoryClient> inventoryClientList = inventoryFeign.getAllInventoryEntries();

        List<InventoryViewModel> inventoryViewModelList = new ArrayList<>();

        for(InventoryClient inventoryClient: inventoryClientList){
            inventoryViewModelList.add(createInventoryViewModel(inventoryClient));
        }

        return inventoryViewModelList;
    }

    public InventoryViewModel createInventory(InventoryClient inventoryClient) throws IOException{
        inventoryClient = inventoryFeign.createInventoryEntry(inventoryClient);

        return createInventoryViewModel(inventoryClient);
    }

    public void deleteInventory(int id) throws IOException{
        inventoryFeign.deleteInventoryEntry(id);
    }

    public void updateInventory(InventoryClient inventoryClient) throws IOException{
        inventoryFeign.updateInventoryEntry(inventoryClient);
    }

    public LevelUpViewModel getLevelUp(int id) throws IOException{
        LevelUpClient levelUpClient = levelUpFeign.getLevelUp(id);

        return createLevelUpViewModel(levelUpClient);
    }

    public List<LevelUpViewModel> getAllLevelUps() throws IOException{
        List<LevelUpClient> levelUpClientList = levelUpFeign.getAllLevelUps();

        List<LevelUpViewModel> levelUpViewModelList = new ArrayList<>();

        for(LevelUpClient levelUpClient: levelUpClientList){
            levelUpViewModelList.add(createLevelUpViewModel(levelUpClient));
        }

        return levelUpViewModelList;
    }

    public LevelUpViewModel createLevelUp(LevelUpClient levelUpClient) throws IOException{
        levelUpClient = levelUpFeign.createLevelUp(levelUpClient);
        return createLevelUpViewModel(levelUpClient);
    }

    public void deleteLevelUp(int id) throws IOException{
        levelUpFeign.deleteLevelUp(id);
    }

    public void updateLevelUp(LevelUpClient levelUpClient) throws IOException{
        levelUpFeign.updateLevelUp(levelUpClient);
    }
    public InvoiceViewModel getInvoice(int id) throws IOException{
        InvoiceClient invoiceClient = invoiceFeign.getInvoiceById(id);
        return createInvoiceViewModel(invoiceClient);
    }

    public List<InvoiceViewModel> getAllInvoices() throws IOException{
        List<InvoiceClient> invoiceClientList = invoiceFeign.getAllInvoices();

        List<InvoiceViewModel> invoiceViewModelList = new ArrayList<>();

        for(InvoiceClient invoiceClient: invoiceClientList){
            invoiceViewModelList.add(createInvoiceViewModel(invoiceClient));
        }

        return invoiceViewModelList;
    }

    public InvoiceViewModel createInvoice(InvoiceClient invoiceClient) throws IOException{
        invoiceClient = invoiceFeign.submitInvoice(invoiceClient);
        return createInvoiceViewModel(invoiceClient);
    }

    public void deleteInvoice(int id) throws IOException{
        invoiceFeign.deleteInvoiceViewModel(id);
    }

    public void updateInvoice(InvoiceClient invoiceClient) throws IOException{
        invoiceFeign.updateInvoiceViewModel(invoiceClient);
    }

    public ProductViewModel createProductViewModel(ProductClient productClient) throws IOException{
        ProductViewModel productViewModel = new ProductViewModel();

        productViewModel.setId(productClient.getId());
        productViewModel.setName(productClient.getName());
        productViewModel.setDescription(productClient.getDescription());
        productViewModel.setList_price(productClient.getList_price());
        productViewModel.setUnit_cost(productClient.getUnit_cost());

        return productViewModel;
    }

    public CustomerViewModel createCustomerViewModel(CustomerClient customerClient) throws IOException{
        CustomerViewModel customerViewModel = new CustomerViewModel();

        customerViewModel.setId(customerClient.getId());
        customerViewModel.setFirstName(customerClient.getFirstName());
        customerViewModel.setLastName(customerClient.getLastName());
        customerViewModel.setStreet(customerClient.getStreet());
        customerViewModel.setCity(customerClient.getCity());
        customerViewModel.setZip(customerClient.getZip());
        customerViewModel.setEmail(customerClient.getEmail());
        customerViewModel.setPhone(customerClient.getPhone());

        return customerViewModel;
    }

    public InventoryViewModel createInventoryViewModel(InventoryClient inventoryClient) throws IOException{
        InventoryViewModel inventoryViewModel = new InventoryViewModel();

        inventoryViewModel.setInventoryId(inventoryClient.getInventoryId());
        inventoryViewModel.setProductId(inventoryClient.getProductId());
        inventoryViewModel.setQuantity(inventoryClient.getQuantity());

        return inventoryViewModel;
    }

    public LevelUpViewModel createLevelUpViewModel(LevelUpClient levelUpClient) throws IOException{
        LevelUpViewModel levelUpViewModel = new LevelUpViewModel();

        levelUpViewModel.setLevelUpId(levelUpClient.getLevelUpId());
        levelUpViewModel.setCustomerId(levelUpClient.getCustomerId());
        levelUpViewModel.setMemberDate(levelUpClient.getMemberDate());
        levelUpViewModel.setPoints(levelUpClient.getPoints());

        return levelUpViewModel;
    }

    public InvoiceViewModel createInvoiceViewModel(InvoiceClient invoiceClient) throws IOException{
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();

        invoiceViewModel.setInvoiceId(invoiceClient.getInvoiceId());
        invoiceViewModel.setCustomerId(invoiceClient.getCustomerId());
        invoiceViewModel.setPurchaseDate(invoiceClient.getPurchaseDate());
        invoiceViewModel.setInvoiceItemList(invoiceClient.getInvoiceItemList());

        return invoiceViewModel;
    }
}

package com.company.adminservice.service;

import com.company.adminservice.feign.*;
import com.company.adminservice.util.message.*;
import com.company.adminservice.viewModel.*;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
public class AdminServiceLayerTest {

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

    AdminServiceLayer adminServiceLayer;

    @Before
    public void setUp() throws Exception {
        setUpCustomerFeign();
        setUpProductFeign();
        setUpInventoryFeign();
        setUpInvoiceFeign();
        setUpLevelUpFeign();

        adminServiceLayer = new AdminServiceLayer(customerFeign, productFeign, inventoryFeign, invoiceFeign, levelUpFeign);
    }

    public void setUpCustomerFeign(){
        customerFeign = mock(CustomerFeign.class);

        CustomerClient customerClient = new CustomerClient();
        customerClient.setId(1);
        customerClient.setFirstName("Fname");
        customerClient.setLastName("Lname");
        customerClient.setStreet("Street");
        customerClient.setCity("City");
        customerClient.setZip("Zip");
        customerClient.setEmail("Email");
        customerClient.setPhone("Phone");

        CustomerClient customerClient1 = new CustomerClient();
        customerClient1.setFirstName("Fname");
        customerClient1.setLastName("Lname");
        customerClient1.setStreet("Street");
        customerClient1.setCity("City");
        customerClient1.setZip("Zip");
        customerClient1.setEmail("Email");
        customerClient1.setPhone("Phone");

        List<CustomerClient> customerClientList = new ArrayList<>();
        customerClientList.add(customerClient);

        doReturn(customerClient).when(customerFeign).createCustomer(customerClient1);
        doReturn(customerClient).when(customerFeign).getCustomer(1);
        doReturn(customerClientList).when(customerFeign).getAllCustomer();
    }

    public void setUpProductFeign(){
        productFeign = mock(ProductFeign.class);

        ProductClient productClient = new ProductClient();
        productClient.setId(1);
        productClient.setName("Name");
        productClient.setDescription("description");
        productClient.setUnit_cost(BigDecimal.valueOf(1.00));
        productClient.setList_price(BigDecimal.valueOf(1.00));

        ProductClient productClient1 = new ProductClient();
        productClient1.setName("Name");
        productClient1.setDescription("description");
        productClient1.setUnit_cost(BigDecimal.valueOf(1.00));
        productClient1.setList_price(BigDecimal.valueOf(1.00));

        List<ProductClient> productClientList = new ArrayList<>();
        productClientList.add(productClient);

        doReturn(productClient).when(productFeign).createProduct(productClient1);
        doReturn(productClient).when(productFeign).getProduct(1);
        doReturn(productClientList).when(productFeign).getAllProducts();
    }

    public void setUpInventoryFeign(){
        inventoryFeign = mock(InventoryFeign.class);

        InventoryClient inventoryClient = new InventoryClient();
        inventoryClient.setInventoryId(1);
        inventoryClient.setProductId(1);
        inventoryClient.setQuantity(100);

        InventoryClient inventoryClient1 = new InventoryClient();
        inventoryClient1.setProductId(1);
        inventoryClient1.setQuantity(100);

        List<InventoryClient> inventoryClientList = new ArrayList<>();
        inventoryClientList.add(inventoryClient);

        doReturn(inventoryClient).when(inventoryFeign).createInventoryEntry(inventoryClient1);
        doReturn(inventoryClient).when(inventoryFeign).getInventory(1);
        doReturn(inventoryClientList).when(inventoryFeign).getAllInventoryEntries();
    }

    public void setUpInvoiceFeign(){
        invoiceFeign = mock(InvoiceFeign.class);

        InvoiceClient invoiceClient = new InvoiceClient();
        invoiceClient.setInvoiceId(1);
        invoiceClient.setCustomerId(1);
        invoiceClient.setPurchaseDate(LocalDate.of(2018,11,1));

        List<InvoiceItemClient> invoiceItemClientList = new ArrayList<>();
        InvoiceItemClient invoiceItemClient = new InvoiceItemClient();
        invoiceItemClient.setInvoiceItemId(1);
        invoiceItemClient.setInvoiceId(1);
        invoiceItemClient.setInventoryId(1);
        invoiceItemClient.setUnitPrice(BigDecimal.valueOf(1.00));
        invoiceItemClient.setQuantity(100);

        invoiceItemClientList.add(invoiceItemClient);

        invoiceClient.setInvoiceItemList(invoiceItemClientList);

        InvoiceClient invoiceClient1 = new InvoiceClient();
        invoiceClient1.setCustomerId(1);
        invoiceClient1.setPurchaseDate(LocalDate.of(2018,11,1));
        invoiceClient1.setInvoiceItemList(invoiceItemClientList);

        List<InvoiceClient> invoiceClientList = new ArrayList<>();
        invoiceClientList.add(invoiceClient);

        doReturn(invoiceClient).when(invoiceFeign).submitInvoice(invoiceClient1);
        doReturn(invoiceClient).when(invoiceFeign).getInvoiceById(1);
        doReturn(invoiceClientList).when(invoiceFeign).getAllInvoices();
    }

    public void setUpLevelUpFeign(){
        levelUpFeign = mock(LevelUpFeign.class);

        LevelUpClient levelUpClient = new LevelUpClient();
        levelUpClient.setLevelUpId(1);
        levelUpClient.setCustomerId(1);
        levelUpClient.setMemberDate(LocalDate.of(2018,11,1));
        levelUpClient.setPoints(100);

        LevelUpClient levelUpClient1 = new LevelUpClient();
        levelUpClient1.setCustomerId(1);
        levelUpClient1.setMemberDate(LocalDate.of(2018,11,1));
        levelUpClient1.setPoints(100);

        List<LevelUpClient> levelUpClientList = new ArrayList<>();
        levelUpClientList.add(levelUpClient);

        doReturn(levelUpClient).when(levelUpFeign).createLevelUp(levelUpClient1);
        doReturn(levelUpClient).when(levelUpFeign).getLevelUp(1);
        doReturn(levelUpClientList).when(levelUpFeign).getAllLevelUps();
    }

    @Test
    public void shouldCreateGetGetAllCustomers() throws IOException {
        CustomerClient customerClient = new CustomerClient();
        customerClient.setFirstName("Fname");
        customerClient.setLastName("Lname");
        customerClient.setStreet("Street");
        customerClient.setCity("City");
        customerClient.setZip("Zip");
        customerClient.setEmail("Email");
        customerClient.setPhone("Phone");

        CustomerViewModel customerViewModel = adminServiceLayer.createCustomer(customerClient);

        System.out.println(customerViewModel);
        customerClient.setId(customerViewModel.getId());
        System.out.println(customerClient);

        CustomerViewModel service = adminServiceLayer.getCustomer(customerClient.getId());
        System.out.println(service);

        List<CustomerViewModel> customerViewModelList = new ArrayList<>();
        customerViewModelList.add(customerViewModel);

        assertEquals(customerViewModel, service);
        assertEquals(customerViewModelList, adminServiceLayer.getAllCustomers());
    }

    @Test
    public void shouldCreateGetGetAllProducts() throws IOException {
        ProductClient productClient = new ProductClient();
        productClient.setName("Name");
        productClient.setDescription("description");
        productClient.setUnit_cost(BigDecimal.valueOf(1.00));
        productClient.setList_price(BigDecimal.valueOf(1.00));

        ProductViewModel productViewModel = adminServiceLayer.createProduct(productClient);

        System.out.println(productViewModel);
        productClient.setId(productViewModel.getId());

        ProductViewModel service = adminServiceLayer.getProduct(productClient.getId());
        List<ProductViewModel> productViewModelList = new ArrayList<>();
        productViewModelList.add(productViewModel);

        assertEquals(productViewModel, service);
        assertEquals(productViewModelList, adminServiceLayer.getAllProducts());
    }

    @Test
    public void shouldCreateGetGetAllInventory() throws IOException {
        InventoryClient inventoryClient = new InventoryClient();
        inventoryClient.setProductId(1);
        inventoryClient.setQuantity(100);

        InventoryViewModel inventoryViewModel = adminServiceLayer.createInventory(inventoryClient);

        System.out.println(inventoryViewModel);
        inventoryClient.setInventoryId(inventoryViewModel.getInventoryId());

        InventoryViewModel service = adminServiceLayer.getInventory(inventoryClient.getInventoryId());
        List<InventoryViewModel> inventoryViewModelList = new ArrayList<>();
        inventoryViewModelList.add(inventoryViewModel);

        assertEquals(inventoryViewModel, service);
        assertEquals(inventoryViewModelList, adminServiceLayer.getAllInventory());
    }

    @Test
    public void shouldCreateGetGetAllInvoice() throws IOException {
        InvoiceItemClient invoiceItemClient = new InvoiceItemClient();
        invoiceItemClient.setInvoiceItemId(1);
        invoiceItemClient.setInvoiceId(1);
        invoiceItemClient.setInventoryId(1);
        invoiceItemClient.setUnitPrice(BigDecimal.valueOf(1.00));
        invoiceItemClient.setQuantity(100);

        List<InvoiceItemClient> invoiceItemClientList = new ArrayList<>();
        invoiceItemClientList.add(invoiceItemClient);

        InvoiceClient invoiceClient = new InvoiceClient();
        invoiceClient.setCustomerId(1);
        invoiceClient.setPurchaseDate(LocalDate.of(2018,11,1));
        invoiceClient.setInvoiceItemList(invoiceItemClientList);

        InvoiceViewModel invoiceViewModel = adminServiceLayer.createInvoice(invoiceClient);
        System.out.println(invoiceViewModel);

        invoiceClient.setInvoiceId(invoiceViewModel.getInvoiceId());
        System.out.println(invoiceClient);

        InvoiceViewModel service = adminServiceLayer.getInvoice(invoiceClient.getInvoiceId());

        List<InvoiceViewModel> invoiceViewModelList = new ArrayList<>();
        invoiceViewModelList.add(invoiceViewModel);

        assertEquals(invoiceViewModel, service);
        assertEquals(invoiceViewModelList, adminServiceLayer.getAllInvoices());
    }

    @Test
    public void shouldCreateGetGetAllLevels() throws IOException{
        LevelUpClient levelUpClient = new LevelUpClient();
        levelUpClient.setCustomerId(1);
        levelUpClient.setMemberDate(LocalDate.of(2018,11,1));
        levelUpClient.setPoints(100);

        LevelUpViewModel levelUpViewModel = adminServiceLayer.createLevelUp(levelUpClient);

        System.out.println(levelUpViewModel);
        levelUpClient.setLevelUpId(levelUpViewModel.getLevelUpId());

        LevelUpViewModel service = adminServiceLayer.getLevelUp(levelUpClient.getLevelUpId());
        List<LevelUpViewModel> levelUpViewModelList = new ArrayList<>();
        levelUpViewModelList.add(levelUpViewModel);

        assertEquals(levelUpViewModel, service);
        assertEquals(levelUpViewModelList, adminServiceLayer.getAllLevelUps());
    }
}

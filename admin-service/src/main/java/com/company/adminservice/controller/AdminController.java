package com.company.adminservice.controller;

import com.company.adminservice.service.AdminServiceLayer;
import com.company.adminservice.util.message.*;
import com.company.adminservice.viewModel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RefreshScope
@RestController
public class AdminController {

   @Autowired
    AdminServiceLayer adminServiceLayer;

    public AdminController(AdminServiceLayer adminServiceLayer) {
        this.adminServiceLayer = adminServiceLayer;
    }

    @PostMapping("/customer")
    public CustomerViewModel createCustomer(@Valid @RequestBody CustomerClient customerClient) throws IOException {
        return adminServiceLayer.createCustomer(customerClient);
    }

    @GetMapping("/customer/{id}")
    public CustomerViewModel getCustomer(@PathVariable int id) throws IOException {
        return adminServiceLayer.getCustomer(id);
    }

    @GetMapping("/customer")
    public List<CustomerViewModel> getAllCustomers() throws IOException{
        return adminServiceLayer.getAllCustomers();
    }

    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable int id) throws IOException {
        adminServiceLayer.deleteCustomer(id);
    }

    @PutMapping("/customer")
    public void updateCustomer(@Valid @RequestBody CustomerClient customerClient) throws IOException {
        adminServiceLayer.updateCustomer(customerClient);
    }

    @PostMapping("/product")
    public ProductViewModel createProduct(@Valid @RequestBody ProductClient productClient) throws IOException {
        return adminServiceLayer.createProduct(productClient);
    }

    @GetMapping("/product/{id}")
    public ProductViewModel getProduct(@PathVariable int id) throws IOException{
        return adminServiceLayer.getProduct(id);
    }

    @GetMapping("/product")
    public List<ProductViewModel> getAllProducts() throws IOException{
        return adminServiceLayer.getAllProducts();
    }

    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable int id) throws IOException{
        adminServiceLayer.deleteProduct(id);
    }

    @PutMapping("/product")
    public void updateProduct(@Valid @RequestBody ProductClient productClient) throws IOException {
        adminServiceLayer.updateProduct(productClient);
    }

    @PostMapping("/inventory")
    public InventoryViewModel createInventory(@Valid @RequestBody InventoryClient inventoryClient) throws IOException{
        return adminServiceLayer.createInventory(inventoryClient);
    }

    @GetMapping("/inventory/{id}")
    public InventoryViewModel getInventory(@PathVariable int id) throws IOException{
        return adminServiceLayer.getInventory(id);
    }

    @GetMapping("/inventory")
    public List<InventoryViewModel> getAllInventories() throws IOException{
        return adminServiceLayer.getAllInventory();
    }

    @DeleteMapping("/inventory/{id}")
    public void deleteInventory(@PathVariable int id) throws IOException{
        adminServiceLayer.deleteInventory(id);
    }

    @PutMapping("/inventory")
    public void updateInventory(@Valid @RequestBody InventoryClient inventoryClient) throws IOException{
        adminServiceLayer.updateInventory(inventoryClient);
    }

    @PostMapping("/level-up")
    public LevelUpViewModel createLevelUp(@Valid @RequestBody LevelUpClient levelUpClient) throws IOException{
        return adminServiceLayer.createLevelUp(levelUpClient);
    }

    @GetMapping("/level-up/{id}")
    public LevelUpViewModel getLevelUp(@PathVariable int id) throws IOException{
        return adminServiceLayer.getLevelUp(id);
    }

    @GetMapping("/level-up")
    public List<LevelUpViewModel> getAllLevels() throws IOException{
        return adminServiceLayer.getAllLevelUps();
    }

    @DeleteMapping("/level-up/{id}")
    public void deleteLevelUp(@PathVariable int id) throws IOException {
        adminServiceLayer.deleteLevelUp(id);
    }

    @PutMapping("/level-up")
    public void updateLevelUp(@Valid @RequestBody LevelUpClient levelUpClient) throws IOException {
        adminServiceLayer.updateLevelUp(levelUpClient);
    }

    @PostMapping("/invoices")
    public InvoiceViewModel createInvoice(@Valid @RequestBody InvoiceClient invoiceClient) throws IOException{
        return adminServiceLayer.createInvoice(invoiceClient);
    }

    @GetMapping("/invoices/{id}")
    public InvoiceViewModel getInvoice(@PathVariable int id) throws IOException {
        return adminServiceLayer.getInvoice(id);
    }

    @GetMapping("/invoices")
    public List<InvoiceViewModel> getAllInvoices() throws IOException {
        return adminServiceLayer.getAllInvoices();
    }

    @DeleteMapping("/invoices/{id}")
    public void deleteInvoice(@PathVariable int id) throws IOException {
        adminServiceLayer.deleteInvoice(id);
    }

    @PutMapping("/invoices")
    public void updateInvoice(@Valid @RequestBody InvoiceClient invoiceClient) throws IOException {
        adminServiceLayer.updateInvoice(invoiceClient);
    }
}

package com.company.invoiceservice.controller;

import com.company.invoiceservice.service.ServiceLayer;
import com.company.invoiceservice.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/invoices")
@CacheConfig(cacheNames = {"invoices"})
public class InvoiceServiceController {

    @Autowired
    ServiceLayer service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel submitInvoice(@RequestBody @Valid InvoiceViewModel invoiceViewModel) {
        return service.saveInvoiceViewModel(invoiceViewModel);
    }

    @Cacheable
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceViewModel getInvoiceById(@PathVariable("id") int id) {
        return service.getInvoiceViewModel(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getAllInvoices() {
        return service.getAllInvoiceViewModels();
    }

    @GetMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<InvoiceViewModel> getInvoicesByCustomerId(@PathVariable("id") int id) {
        return service.getInvoiceViewModelsByCustomerId(id);
    }

    @CacheEvict(key = "#invoiceViewModel.getInvoiceId()")
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoiceViewModel(@RequestBody @Valid InvoiceViewModel invoiceViewModel) {
        service.updateInvoiceViewModel(invoiceViewModel);
    }

    @CacheEvict
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoiceViewModel(@PathVariable("id") int id) {
        service.deleteInvoiceViewModel(id);
    }
}

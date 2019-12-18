package com.company.retailapiservice.util.feign;

import com.company.retailapiservice.model.InvoiceViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("invoice-service")
public interface InvoiceClient {

    @PostMapping("/invoices")
    public InvoiceViewModel submitInvoice(@RequestBody InvoiceViewModel invoiceViewModel);

    @GetMapping("/invoices/customer/{id}")
    public List<InvoiceViewModel> getInvoicesByCustomerId(@PathVariable("id") int id);

    @GetMapping("/invoices")
    public List<InvoiceViewModel> getAllInvoices();
}

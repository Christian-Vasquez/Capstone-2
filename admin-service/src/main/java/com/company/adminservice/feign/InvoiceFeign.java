package com.company.adminservice.feign;

import com.company.adminservice.util.message.InvoiceClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "invoice-service", path = "/invoices")
public interface InvoiceFeign {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    InvoiceClient submitInvoice(@RequestBody @Valid InvoiceClient invoiceClient);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    InvoiceClient getInvoiceById(@PathVariable("id") int id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<InvoiceClient> getAllInvoices();

    @GetMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    List<InvoiceClient> getInvoicesByCustomerId(@PathVariable("id") int id);

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateInvoiceViewModel(@RequestBody @Valid InvoiceClient invoiceClient);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteInvoiceViewModel(@PathVariable("id") int id);

}

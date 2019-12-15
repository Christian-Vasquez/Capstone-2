package com.company.invoiceservice.dao;

import com.company.invoiceservice.model.Invoice;

import java.util.List;

public interface InvoiceDao {

    public Invoice createInvoice(Invoice invoice);

    public Invoice getInvoice(int id);

    public void updateInvoice(Invoice invoice);

    public void deleteInvoice(int id);

    public List<Invoice> getAllInvoices();

    public List<Invoice> getInvoicesByCustomerId(int customerId);


}

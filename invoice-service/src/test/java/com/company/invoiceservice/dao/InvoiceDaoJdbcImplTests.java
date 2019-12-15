package com.company.invoiceservice.dao;

import com.company.invoiceservice.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoJdbcImplTests {

    @Autowired
    InvoiceDao invoiceDao;

    private Invoice invoice;

    @Before
    public void setUp() {
        clearDatabase();
        setUpTestObject();
    }

    public void clearDatabase() {
        List<Invoice> invoices = invoiceDao.getAllInvoices();
        invoices.stream().forEach(invoice1 -> invoiceDao.deleteInvoice(invoice1.getInvoiceId()));
    }

    public void setUpTestObject() {
        invoice = new Invoice();
        invoice.setCustomerId(12);
        invoice.setPurchaseDate(LocalDate.of(2019, 06, 10));

    }

    @Test
    public void shouldAddGetAndDeleteInvoice() {
        invoice = invoiceDao.createInvoice(invoice);

        Invoice invoiceFromDb = invoiceDao.getInvoice(invoice.getInvoiceId());

        assertEquals(invoice, invoiceFromDb);

        invoiceDao.deleteInvoice(invoice.getInvoiceId());

        assertNull(invoiceDao.getInvoice(invoice.getInvoiceId()));

    }

    @Test
    public void shouldUpdateInvoice() {

        invoice = invoiceDao.createInvoice(invoice);

        invoice.setPurchaseDate(LocalDate.of(2019, 8, 04));

        invoiceDao.updateInvoice(invoice);

        assertEquals(invoice, invoiceDao.getInvoice(invoice.getInvoiceId()));

    }

    @Test
    public void shouldReturnAllInvoices() {

        invoice = invoiceDao.createInvoice(invoice);
        List<Invoice> invoices = new ArrayList<>(Collections.singletonList(invoice));

        assertEquals(invoices, invoiceDao.getAllInvoices());

    }

    @Test
    public void shouldGetInvoicesByCustomerId() {

        invoice = invoiceDao.createInvoice(invoice);
        List<Invoice> invoices = new ArrayList<>(Collections.singletonList(invoice));

        assertEquals(invoices, invoiceDao.getInvoicesByCustomerId(12));

    }

    @Test
    public void shouldReturnNullIfNoInvoicesFoundByCustomerId() {
        assertNull(invoiceDao.getInvoicesByCustomerId(88));
    }

}

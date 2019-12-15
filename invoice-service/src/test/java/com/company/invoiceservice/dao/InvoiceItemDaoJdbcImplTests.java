package com.company.invoiceservice.dao;

import com.company.invoiceservice.model.Invoice;
import com.company.invoiceservice.model.InvoiceItem;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceItemDaoJdbcImplTests {

    @Autowired
    private InvoiceItemDao itemDao;

    @Autowired
    private InvoiceDao invoiceDao;

    private Invoice invoice;

    private InvoiceItem item;

    @Before
    public void setUp() {
        clearDatabase();
//        setUpTestObjects();
    }

    public void clearDatabase() {
        List<InvoiceItem> items = itemDao.getAllInvoiceItems();
        items.stream().forEach(invoiceItem -> itemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId()));

        List<Invoice> invoices = invoiceDao.getAllInvoices();
        invoices.stream().forEach(invoice1 -> invoiceDao.deleteInvoice(invoice1.getInvoiceId()));
    }

//    public void setUpTestObjects() {
//        invoice = new Invoice();
//        invoice.setCustomerId(12);
//        invoice.setPurchaseDate(LocalDate.of(2019,3, 16));
//
//        item = new InvoiceItem();
//        item.setInvoiceId(1);
//
//    }

    @Test
    public void shouldAddGetAndDeleteInvoiceItem() {
        invoice =  new Invoice();
        invoice.setCustomerId(12);
        invoice.setPurchaseDate(LocalDate.of(2019, 3, 16));
        invoice = invoiceDao.createInvoice(invoice);

        item = new InvoiceItem();
        item.setInvoiceId(invoice.getInvoiceId());
        item.setInventoryId(8);
        item.setQuantity(12);
        item.setUnitPrice(new BigDecimal(22.99).setScale(2, RoundingMode.FLOOR));

        item = itemDao.createInvoiceItem(item);

        assertEquals(item, itemDao.getInvoiceItemById(item.getInvoiceItemId()));

        itemDao.deleteInvoiceItem(item.getInvoiceItemId());

        assertNull(itemDao.getInvoiceItemById(item.getInvoiceItemId()));
    }

    @Test
    public void shouldUpdateInvoiceItem() {
        invoice =  new Invoice();
        invoice.setCustomerId(12);
        invoice.setPurchaseDate(LocalDate.of(2019, 3, 16));
        invoice = invoiceDao.createInvoice(invoice);

        item = new InvoiceItem();
        item.setInvoiceId(invoice.getInvoiceId());
        item.setInventoryId(8);
        item.setQuantity(12);
        item.setUnitPrice(new BigDecimal(22.99).setScale(2, RoundingMode.FLOOR));

        item = itemDao.createInvoiceItem(item);

        item.setUnitPrice(new BigDecimal(24.99).setScale(2, RoundingMode.FLOOR));

        itemDao.updateInvoiceItem(item);

        assertEquals(item, itemDao.getInvoiceItemById(item.getInvoiceItemId()));

    }

    @Test
    public void shouldGetAllInvoiceItems() {

        invoice =  new Invoice();
        invoice.setCustomerId(12);
        invoice.setPurchaseDate(LocalDate.of(2019, 3, 16));
        invoice = invoiceDao.createInvoice(invoice);

        item = new InvoiceItem();
        item.setInvoiceId(invoice.getInvoiceId());
        item.setInventoryId(8);
        item.setQuantity(12);
        item.setUnitPrice(new BigDecimal(22.99).setScale(2, RoundingMode.FLOOR));

        itemDao.createInvoiceItem(item);

//        InvoiceItem invoiceItem = new InvoiceItem();
//        invoiceItem.setInvoiceId(invoice.getInvoiceId());
//        invoiceItem.setInventoryId(7);
//        invoiceItem.setQuantity(32);
//        invoiceItem.setUnitPrice(new BigDecimal(22.99));
//
//        itemDao.createInvoiceItem(invoiceItem);

        List<InvoiceItem> invoiceItems = new ArrayList<>();
        invoiceItems.add(item);
//        invoiceItems.add(invoiceItem);

        assertEquals(invoiceItems, itemDao.getAllInvoiceItems());
    }

    @Test
    public void shouldGetInvoiceItemsByInvoiceId() {
        invoice =  new Invoice();
        invoice.setCustomerId(12);
        invoice.setPurchaseDate(LocalDate.of(2019, 3, 16));
        invoice = invoiceDao.createInvoice(invoice);

        item = new InvoiceItem();
        item.setInvoiceId(invoice.getInvoiceId());
        item.setInventoryId(8);
        item.setQuantity(12);
        item.setUnitPrice(new BigDecimal(22.99).setScale(2, RoundingMode.FLOOR));

        itemDao.createInvoiceItem(item);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setInventoryId(7);
        invoiceItem.setQuantity(32);
        invoiceItem.setUnitPrice(new BigDecimal(22.99).setScale(2, RoundingMode.FLOOR));

        itemDao.createInvoiceItem(invoiceItem);

        List<InvoiceItem> invoiceItems = new ArrayList<>();
        invoiceItems.add(item);
        invoiceItems.add(invoiceItem);

        assertEquals(invoiceItems, itemDao.getInvoiceItemsByInvoiceId(invoice.getInvoiceId()));
    }

    @Test
    public void shouldReturnNullIfNoItemsFoundByInvoiceId() {
        assertNull(itemDao.getInvoiceItemsByInvoiceId(55));
    }

}

package com.company.invoiceservice.service;

import com.company.invoiceservice.dao.InvoiceDao;
import com.company.invoiceservice.dao.InvoiceDaoJdbcImpl;
import com.company.invoiceservice.dao.InvoiceItemDao;
import com.company.invoiceservice.dao.InvoiceItemDaoJdbcImpl;
import com.company.invoiceservice.exception.NotFoundException;
import com.company.invoiceservice.model.Invoice;
import com.company.invoiceservice.model.InvoiceItem;
import com.company.invoiceservice.viewmodel.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceLayerTests {

    private InvoiceDao invoiceDao;
    private InvoiceItemDao itemDao;
    private ServiceLayer service;

    @Before
    public void setUp() throws Exception {
        setUpInvoiceDaoMock();
        setUpItemDaoMock();
        service = new ServiceLayer(invoiceDao, itemDao);
    }

    public void setUpInvoiceDaoMock() {
        invoiceDao = mock(InvoiceDaoJdbcImpl.class);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(12);
        invoice.setPurchaseDate(LocalDate.of(2019, 12, 8));

        Invoice invoice1 = new Invoice();
        invoice1.setInvoiceId(1);
        invoice1.setCustomerId(12);
        invoice1.setPurchaseDate(LocalDate.of(2019, 12, 8));

        List<Invoice> invoices = new ArrayList<>(Collections.singletonList(invoice1));

        when(invoiceDao.createInvoice(invoice)).thenReturn(invoice1);
        when(invoiceDao.getInvoice(1)).thenReturn(invoice1);
        when(invoiceDao.getInvoicesByCustomerId(12)).thenReturn(invoices);
        when(invoiceDao.getAllInvoices()).thenReturn(invoices);
        when(invoiceDao.getInvoice(44)).thenReturn(null);
        when(invoiceDao.getInvoicesByCustomerId(88)).thenReturn(null);
        when(invoiceDao.createInvoice(new Invoice())).thenThrow(new IllegalArgumentException("missing fields in invoice"));
    }

    public void setUpItemDaoMock() {
        itemDao = mock(InvoiceItemDaoJdbcImpl.class);

        InvoiceItem item = new InvoiceItem();
        item.setInvoiceId(8);
        item.setInventoryId(8);
        item.setQuantity(12);
        item.setUnitPrice(new BigDecimal(24.99).setScale(2, RoundingMode.FLOOR));

        InvoiceItem item1 = new InvoiceItem();
        item1.setInvoiceItemId(1);
        item1.setInvoiceId(1);
        item1.setInventoryId(8);
        item1.setQuantity(12);
        item1.setUnitPrice(new BigDecimal(24.99).setScale(2, RoundingMode.FLOOR));

        List<InvoiceItem> invoiceItemList = new ArrayList<>(Collections.singletonList(item1));

        when(itemDao.createInvoiceItem(item)).thenReturn(item1);
        when(itemDao.getInvoiceItemById(1)).thenReturn(item1);
        when(itemDao.getAllInvoiceItems()).thenReturn(invoiceItemList);
        when(itemDao.getInvoiceItemsByInvoiceId(1)).thenReturn(invoiceItemList);
        when(itemDao.getInvoiceItemById(55)).thenReturn(null);
        when(itemDao.getInvoiceItemsByInvoiceId(100)).thenReturn(null);

    }

    @Test
    public void shouldSaveAndFindInvoiceViewModel() {
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setCustomerId(12);
        invoiceViewModel.setPurchaseDate(LocalDate.of(2019, 12, 8));

        InvoiceItem item1 = new InvoiceItem();
        item1.setInvoiceItemId(1);
        item1.setInvoiceId(1);
        item1.setInventoryId(8);
        item1.setQuantity(12);
        item1.setUnitPrice(new BigDecimal(24.99).setScale(2, RoundingMode.FLOOR));

        List<InvoiceItem> invoiceItemList = new ArrayList<>(Collections.singletonList(item1));

        invoiceViewModel.setInvoiceItemList(invoiceItemList);

        invoiceViewModel = service.saveInvoiceViewModel(invoiceViewModel);

        InvoiceViewModel fromService = service.getInvoiceViewModel(invoiceViewModel.getInvoiceId());

        assertEquals(invoiceViewModel, fromService);
    }

    @Test
    public void shouldFindInvoiceViewModelsByCustomerId() {
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setCustomerId(12);
        invoiceViewModel.setPurchaseDate(LocalDate.of(2019, 12, 8));

        InvoiceItem item1 = new InvoiceItem();
        item1.setInvoiceItemId(1);
        item1.setInvoiceId(1);
        item1.setInventoryId(8);
        item1.setQuantity(12);
        item1.setUnitPrice(new BigDecimal(24.99).setScale(2, RoundingMode.FLOOR));

        List<InvoiceItem> invoiceItemList = new ArrayList<>(Collections.singletonList(item1));

        invoiceViewModel.setInvoiceItemList(invoiceItemList);

        invoiceViewModel = service.saveInvoiceViewModel(invoiceViewModel);

        List<InvoiceViewModel> customer12Invoices = new ArrayList<>(Collections.singletonList(invoiceViewModel));

        List<InvoiceViewModel> customerInvoicesFromService = service.getInvoiceViewModelsByCustomerId(12);

        assertEquals(customer12Invoices, customerInvoicesFromService);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundIfNoInvoiceFoundWithCustomerId() {
        service.getInvoiceViewModelsByCustomerId(88);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentIfInvoiceDoesNotHaveCustomerId() {
        service.saveInvoiceViewModel(new InvoiceViewModel());
    }
}

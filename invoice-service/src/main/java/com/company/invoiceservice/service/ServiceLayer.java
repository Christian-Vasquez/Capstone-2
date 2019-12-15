package com.company.invoiceservice.service;

import com.company.invoiceservice.dao.InvoiceDao;
import com.company.invoiceservice.dao.InvoiceItemDao;
import com.company.invoiceservice.exception.NotFoundException;
import com.company.invoiceservice.model.Invoice;
import com.company.invoiceservice.model.InvoiceItem;
import com.company.invoiceservice.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceLayer {

    private InvoiceDao invoiceDao;
    private InvoiceItemDao itemDao;

    @Autowired
    public ServiceLayer(InvoiceDao invoiceDao, InvoiceItemDao itemDao) {
        this.invoiceDao = invoiceDao;
        this.itemDao = itemDao;
    }

    @Transactional
    public InvoiceViewModel saveInvoiceViewModel(@Valid InvoiceViewModel invoiceViewModel) {
        Invoice invoice = new Invoice();
        invoice.setCustomerId(invoiceViewModel.getCustomerId());
        invoice.setPurchaseDate(invoiceViewModel.getPurchaseDate());
        invoice = invoiceDao.createInvoice(invoice);

        invoiceViewModel.setInvoiceId(invoice.getInvoiceId());

        List<InvoiceItem> invoiceItems = invoiceViewModel.getInvoiceItemList();

        invoiceItems.stream().forEach(invoiceItem -> {
            invoiceItem.setInvoiceId(invoiceViewModel.getInvoiceId());
            itemDao.createInvoiceItem(invoiceItem);
        });

        invoiceItems = itemDao.getInvoiceItemsByInvoiceId(invoiceViewModel.getInvoiceId());
        invoiceViewModel.setInvoiceItemList(invoiceItems);

        return invoiceViewModel;
    }

    public InvoiceViewModel getInvoiceViewModel(int id) {
        Invoice invoice = invoiceDao.getInvoice(id);
        if (invoice == null) {
            throw new NotFoundException("no invoice in db with that id");
        }
        return buildInvoiceViewModel(invoice);
    }

    public void updateInvoiceViewModel(@Valid InvoiceViewModel invoiceViewModel) {
//        have to delete invoice items first to avoid cascade fail!!
        List<InvoiceItem> items = itemDao.getInvoiceItemsByInvoiceId(invoiceViewModel.getInvoiceId());
        items.stream().forEach(invoiceItem -> itemDao.deleteInvoiceItem(invoiceItem.getInvoiceItemId()));

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceViewModel.getInvoiceId());
        invoice.setCustomerId(invoiceViewModel.getCustomerId());
        invoice.setPurchaseDate(invoiceViewModel.getPurchaseDate());

        invoiceDao.updateInvoice(invoice);

//        save items from the incoming view model
        invoiceViewModel.getInvoiceItemList().stream().forEach(invoiceItem -> itemDao.createInvoiceItem(invoiceItem));
    }

    public void deleteInvoiceViewModel(int id) {
        invoiceDao.deleteInvoice(id);
    }

    public List<InvoiceViewModel> getAllInvoiceViewModels() {
        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        List<InvoiceViewModel> invoiceViewModelList = new ArrayList<>();

        invoiceList.stream().forEach(invoice -> invoiceViewModelList.add(buildInvoiceViewModel(invoice)));
        return invoiceViewModelList;
    }

    public List<InvoiceViewModel> getInvoiceViewModelsByCustomerId(int customerId) {
        List<Invoice> invoiceList = invoiceDao.getInvoicesByCustomerId(customerId);
        if (invoiceList == null) {
            throw new NotFoundException("no invoice in db with that customer id");
        }
        List<InvoiceViewModel> invoiceViewModelList = new ArrayList<>();

        invoiceList.stream().forEach(invoice -> invoiceViewModelList.add(buildInvoiceViewModel(invoice)));
        return invoiceViewModelList;
    }

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setInvoiceId(invoice.getInvoiceId());
        invoiceViewModel.setCustomerId(invoice.getCustomerId());
        invoiceViewModel.setPurchaseDate(invoice.getPurchaseDate());
        invoiceViewModel.setInvoiceItemList(itemDao.getInvoiceItemsByInvoiceId(invoice.getInvoiceId()));

        return invoiceViewModel;
    }

}

package com.company.adminservice.util.message;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class InvoiceClient {

    private int invoiceId;
    private int customerId;
    private LocalDate purchaseDate;
    private List<InvoiceItemClient> invoiceItemList;

    public InvoiceClient() {
    }

    public InvoiceClient(int invoiceId, int customerId, LocalDate purchaseDate, List<InvoiceItemClient> invoiceItemList) {
        this.invoiceId = invoiceId;
        this.customerId = customerId;
        this.purchaseDate = purchaseDate;
        this.invoiceItemList = invoiceItemList;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public List<InvoiceItemClient> getInvoiceItemList() {
        return invoiceItemList;
    }

    public void setInvoiceItemList(List<InvoiceItemClient> invoiceItemList) {
        this.invoiceItemList = invoiceItemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InvoiceClient)) return false;
        InvoiceClient that = (InvoiceClient) o;
        return invoiceId == that.invoiceId &&
                customerId == that.customerId &&
                Objects.equals(purchaseDate, that.purchaseDate) &&
                Objects.equals(invoiceItemList, that.invoiceItemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, customerId, purchaseDate, invoiceItemList);
    }

    @Override
    public String toString() {
        return "InvoiceClient{" +
                "invoiceId=" + invoiceId +
                ", customerId=" + customerId +
                ", purchaseDate=" + purchaseDate +
                ", invoiceItemList=" + invoiceItemList +
                '}';
    }
}

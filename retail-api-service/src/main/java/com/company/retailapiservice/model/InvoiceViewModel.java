package com.company.retailapiservice.model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class InvoiceViewModel {

    private int invoiceId;
//    @NotNull(message = "Please provide a customer id for the invoice")
    private int customerId;
//    @NotNull(message = "Please provide a purchaser date for the invoice")
    private LocalDate purchaseDate;
    private Customer customer;
    private List<InvoiceItem> invoiceItemList;
    private int totalProductsPurchased;
    private BigDecimal totalPriceAmount;
    private int levelUpPointsEarnedWithThisOrder;
    private int totalLevelUpPointsInAccount;

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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<InvoiceItem> getInvoiceItemList() {
        return invoiceItemList;
    }

    public void setInvoiceItemList(List<InvoiceItem> invoiceItemList) {
        this.invoiceItemList = invoiceItemList;
    }

    public int getTotalProductsPurchased() {
        return totalProductsPurchased;
    }

    public void setTotalProductsPurchased(int totalProductsPurchased) {
        this.totalProductsPurchased = totalProductsPurchased;
    }

    public BigDecimal getTotalPriceAmount() {
        return totalPriceAmount;
    }

    public void setTotalPriceAmount(BigDecimal totalPriceAmount) {
        this.totalPriceAmount = totalPriceAmount;
    }

    public int getLevelUpPointsEarnedWithThisOrder() {
        return levelUpPointsEarnedWithThisOrder;
    }

    public void setLevelUpPointsEarnedWithThisOrder(int levelUpPointsEarnedWithThisOrder) {
        this.levelUpPointsEarnedWithThisOrder = levelUpPointsEarnedWithThisOrder;
    }

    public int getTotalLevelUpPointsInAccount() {
        return totalLevelUpPointsInAccount;
    }

    public void setTotalLevelUpPointsInAccount(int totalLevelUpPointsInAccount) {
        this.totalLevelUpPointsInAccount = totalLevelUpPointsInAccount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return getInvoiceId() == that.getInvoiceId() &&
                getCustomerId() == that.getCustomerId() &&
                getTotalProductsPurchased() == that.getTotalProductsPurchased() &&
                getLevelUpPointsEarnedWithThisOrder() == that.getLevelUpPointsEarnedWithThisOrder() &&
                getTotalLevelUpPointsInAccount() == that.getTotalLevelUpPointsInAccount() &&
                Objects.equals(getPurchaseDate(), that.getPurchaseDate()) &&
                Objects.equals(getCustomer(), that.getCustomer()) &&
                Objects.equals(getInvoiceItemList(), that.getInvoiceItemList()) &&
                Objects.equals(getTotalPriceAmount(), that.getTotalPriceAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceId(), getCustomerId(), getPurchaseDate(), getCustomer(), getInvoiceItemList(), getTotalProductsPurchased(), getTotalPriceAmount(), getLevelUpPointsEarnedWithThisOrder(), getTotalLevelUpPointsInAccount());
    }

    @Override
    public String toString() {
        return "InvoiceViewModel{" +
                "invoiceId=" + invoiceId +
                ", customerId=" + customerId +
                ", purchaseDate=" + purchaseDate +
                ", customer=" + customer +
                ", invoiceItemList=" + invoiceItemList +
                ", totalProductsPurchased=" + totalProductsPurchased +
                ", totalPriceAmount=" + totalPriceAmount +
                ", levelUpPointsEarnedWithThisOrder=" + levelUpPointsEarnedWithThisOrder +
                ", totalLevelUpPointsInAccount=" + totalLevelUpPointsInAccount +
                '}';
    }
}

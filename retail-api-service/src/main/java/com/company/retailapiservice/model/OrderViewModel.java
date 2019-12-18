package com.company.retailapiservice.model;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class OrderViewModel {

    private int orderId;
    @NotNull(message = "please provide a registered customerId")
    private int customerId;
//    private Customer customerObj;
    private LocalDate purchaseDate;
    @NotNull(message = "please add products you'd like to purchase")
    private List<Product> products;
//    private int totalProductsPurchased;
//    private BigDecimal totalPriceAmount;
//    private int levelUpPointsEarnedWithThisOrder;
//    private int totalLevelUpPointsInAccount;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderViewModel that = (OrderViewModel) o;
        return getOrderId() == that.getOrderId() &&
                getCustomerId() == that.getCustomerId() &&
                Objects.equals(getPurchaseDate(), that.getPurchaseDate()) &&
                Objects.equals(getProducts(), that.getProducts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderId(), getCustomerId(), getPurchaseDate(), getProducts());
    }

    @Override
    public String toString() {
        return "OrderViewModel{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", purchaseDate=" + purchaseDate +
                ", products=" + products +
                '}';
    }
}

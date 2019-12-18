package com.company.retailapiservice.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    @NotNull(message = "please provide the id of the product")
    private int productId;
    private String name;
    private String description;
    private BigDecimal list_price;
//    private BigDecimal unit_cost;
    @NotNull(message = "please provide a positive quantity to purchase")
    @Min(value = 1)
    private int quantityToPurchase;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getList_price() {
        return list_price;
    }

    public void setList_price(BigDecimal list_price) {
        this.list_price = list_price;
    }

    public int getQuantityToPurchase() {
        return quantityToPurchase;
    }

    public void setQuantityToPurchase(int quantityToPurchase) {
        this.quantityToPurchase = quantityToPurchase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return getProductId() == product.getProductId() &&
                getQuantityToPurchase() == product.getQuantityToPurchase() &&
                Objects.equals(getName(), product.getName()) &&
                Objects.equals(getDescription(), product.getDescription()) &&
                Objects.equals(getList_price(), product.getList_price());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductId(), getName(), getDescription(), getList_price(), getQuantityToPurchase());
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", list_price=" + list_price +
                ", quantityToPurchase=" + quantityToPurchase +
                '}';
    }
}



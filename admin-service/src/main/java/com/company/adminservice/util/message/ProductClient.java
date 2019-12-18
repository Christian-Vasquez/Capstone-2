package com.company.adminservice.util.message;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductClient {

    private int id;
    private String name;
    private String description;
    private BigDecimal list_price;
    private BigDecimal unit_cost;

    public ProductClient() {
    }

    public ProductClient(int id, String name, String description, BigDecimal list_price, BigDecimal unit_cost) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.list_price = list_price;
        this.unit_cost = unit_cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public BigDecimal getUnit_cost() {
        return unit_cost;
    }

    public void setUnit_cost(BigDecimal unit_cost) {
        this.unit_cost = unit_cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductClient)) return false;
        ProductClient productClient = (ProductClient) o;
        return id == productClient.id &&
                Objects.equals(name, productClient.name) &&
                Objects.equals(description, productClient.description) &&
                Objects.equals(list_price, productClient.list_price) &&
                Objects.equals(unit_cost, productClient.unit_cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, list_price, unit_cost);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", list_price=" + list_price +
                ", unit_cost=" + unit_cost +
                '}';
    }
}

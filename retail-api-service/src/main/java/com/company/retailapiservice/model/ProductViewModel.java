package com.company.retailapiservice.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductViewModel {

    private int id;
    private String name;
    private String description;
    private BigDecimal list_price;
    private BigDecimal unit_cost;
//    private int quantityInStock;

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

//    public int getQuantityInStock() {
//        return quantityInStock;
//    }
//
//    public void setQuantityInStock(int quantityInStock) {
//        this.quantityInStock = quantityInStock;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductViewModel that = (ProductViewModel) o;
        return getId() == that.getId() &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getList_price(), that.getList_price()) &&
                Objects.equals(getUnit_cost(), that.getUnit_cost());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getList_price(), getUnit_cost());
    }

    @Override
    public String toString() {
        return "ProductViewModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", list_price=" + list_price +
                ", unit_cost=" + unit_cost +
                '}';
    }
}

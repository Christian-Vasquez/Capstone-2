package com.company.adminservice.viewModel;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

public class ProductViewModel {

    private int id;

    @NotNull(message = "Please insert a name")
    private String name;

    @NotNull(message = "Please insert a description")
    private String description;

    @NotNull(message = "Please insert a list_price")
    private BigDecimal list_price;

    @NotNull(message = "Please insert a unit_cost")
    private BigDecimal unit_cost;

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
        if (!(o instanceof ProductViewModel)) return false;
        ProductViewModel that = (ProductViewModel) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(list_price, that.list_price) &&
                Objects.equals(unit_cost, that.unit_cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, list_price, unit_cost);
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

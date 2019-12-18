package com.company.adminservice.viewModel;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class InventoryViewModel {

    private int inventoryId;

    @NotNull(message = "Please insert a product id")
    private int productId;

    @NotNull(message = "Please insert a quantity")
    private int quantity;

    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryViewModel)) return false;
        InventoryViewModel that = (InventoryViewModel) o;
        return inventoryId == that.inventoryId &&
                productId == that.productId &&
                quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(inventoryId, productId, quantity);
    }

    @Override
    public String toString() {
        return "InventoryViewModel{" +
                "inventoryId=" + inventoryId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}

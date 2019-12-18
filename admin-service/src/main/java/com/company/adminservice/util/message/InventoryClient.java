package com.company.adminservice.util.message;

import java.util.Objects;

public class InventoryClient {

    private int inventoryId;
    private int productId;
    private int quantity;

    public InventoryClient() {
    }

    public InventoryClient(int inventoryId, int productId, int quantity) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.quantity = quantity;
    }

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
        if (!(o instanceof InventoryClient)) return false;
        InventoryClient that = (InventoryClient) o;
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
        return "InventoryClient{" +
                "inventoryId=" + inventoryId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}

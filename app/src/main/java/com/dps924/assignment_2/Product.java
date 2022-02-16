package com.dps924.assignment_2;

public class Product {

    final private double productPrice;
    private int inventoryQuantity;
    final private String productName;

    public Product(double productPrice, int inventoryQuantity, String productName) {
        this.productPrice = productPrice;
        this.inventoryQuantity = inventoryQuantity;
        this.productName = productName;
    }

    public void updateInventoryQuantity(int quantity, boolean add) {
        if (!add && inventoryQuantity > 0) {
            this.inventoryQuantity -= quantity;
        }
        else {
            this.inventoryQuantity += quantity;
        }
    }

    public double getProductPrice(){
        return this.productPrice;
    }

    public String getProductName() {
        return this.productName;
    }

    public int getInventoryQuantity() {
        return this.inventoryQuantity;
    }
}

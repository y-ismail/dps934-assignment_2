package com.dps924.assignment_2;

import java.util.Date;

public class PurchaseHistory {

    private final String productName;
    private final int quantity;
    private final String totalPrice;
    private final Date purchasedDate;

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public Date getPurchasedDate() {
        return purchasedDate;
    }

    public PurchaseHistory(String productName, int quantity, String totalPrice, Date purchasedDate) {
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.purchasedDate = purchasedDate;
    }
}

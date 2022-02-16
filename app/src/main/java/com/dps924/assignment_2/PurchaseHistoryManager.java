package com.dps924.assignment_2;

import java.util.ArrayList;

public class PurchaseHistoryManager {

    public ArrayList<PurchaseHistory> purchaseHistories = new ArrayList<>(0);

    public void addPurchase(PurchaseHistory purchase) {
        this.purchaseHistories.add(purchase);
    }

}

package com.dps924.assignment_2;

import android.app.Application;

public class MyApp extends Application {

    ProductManager productManager = new ProductManager();
    PurchaseHistoryManager purchaseHistoryManager = new PurchaseHistoryManager();

    public MyApp() {
        this.productManager.addProduct(new Product(31.99, 10, "Pants"));
        this.productManager.addProduct(new Product(55.99, 100, "Shoes"));
        this.productManager.addProduct(new Product(5.98, 30, "Hats"));
    }
}

package com.dps924.assignment_2;

import java.util.ArrayList;

public class ProductManager {

    public ArrayList<Product> productList = new ArrayList<>(0);

    public void addProduct(Product product) {
        this.productList.add(product);
    }

}

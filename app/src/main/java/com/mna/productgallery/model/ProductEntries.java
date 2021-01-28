package com.mna.productgallery.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class ProductEntries implements Serializable {

    @SerializedName("entries")
    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}

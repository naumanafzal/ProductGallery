package com.mna.productgallery.model;

import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.lifecycle.MutableLiveData;

import com.mna.productgallery.service.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Products Class to Observe All Service Calls
 */

public class Products extends BaseObservable {

    private int page=1;
    private List<Product> ProductsList = new ArrayList<>();
    private MutableLiveData<List<Product>> Products = new MutableLiveData<>();
    private Product productDetail = new Product();
    private MutableLiveData<Product> Product = new MutableLiveData<>();


    /**
     *
     * @param product
     */
    public void addProduct(Product product) {
        ProductsList.add(product);
    }

    /**
     *
     * @return
     */
    public MutableLiveData<List<Product>> getProducts() {
        return Products;
    }

    /**
     *
     */
    public void fetchList(String clientId) {
        Callback<ProductEntries> callback = new Callback<ProductEntries>() {
            @Override
            public void onResponse(Call<ProductEntries> call, Response<ProductEntries> response) {
                ProductEntries body = response.body();
                if(body == null)
                {
                    Products.setValue(new ArrayList<Product>());
                }
                else
                {
                    page++;
                    Products.setValue(body.getProductList());
                }
            }

            @Override
            public void onFailure(Call<ProductEntries> call, Throwable t) {
                Log.e("Test", t.getMessage(), t);
            }
        };

        Api.getApi().getProducts().enqueue(callback);
    }

    /**
     *
     * @param productDetail
     */
    public void setProductDetail(Product productDetail) {
        this.productDetail = productDetail;
    }

    /**
     *
     * @return
     */
    public MutableLiveData<Product> getProductDetail() {
        return Product;
    }

}

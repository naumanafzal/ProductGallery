package com.mna.productgallery.viewmodel;

import android.view.View;

import androidx.databinding.ObservableArrayMap;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mna.productgallery.R;
import com.mna.productgallery.adapter.ProductsAdapter;
import com.mna.productgallery.model.Product;
import com.mna.productgallery.model.Products;

import java.util.List;

/**
 * ProductsViewModel
 */

public class ProductsViewModel extends ViewModel {

    private Products products;
    private ProductsAdapter adapter;
    public MutableLiveData<Product> selected;
    public ObservableArrayMap<String, String> images;
    public ObservableInt loading;
    public ObservableInt showEmpty;
    private String clientId;
    /**
     * initializer
     */
    public void init() {
        products = new Products();
        selected = new MutableLiveData<>();
        adapter = new ProductsAdapter(R.layout.view_product, this);
        images = new ObservableArrayMap<>();
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);
    }

    /**
     *
     * @param clientId
     */
    public void fetchList(String clientId) {
        clientId = clientId;
        products.fetchList(clientId);
    }

    /**
     *
     * @return
     */
    public MutableLiveData<List<Product>> getProducts() {
        return products.getProducts();
    }

    public ProductsAdapter getAdapter() {
        return adapter;
    }

    /**
     *
     * @param products
     */
    public void setProductsInAdapter(List<Product> products) {
        this.adapter.setProducts(products);
        this.adapter.notifyDataSetChanged();
    }

    /**
     *
     * @return
     */
    public MutableLiveData<Product> getSelected() {
        return selected;
    }

    /**
     *
     * @param index
     */
    public void onItemClick(Integer index) {
        Product product = getProductAt(index);
        selected.setValue(product);
    }

    /**
     *
     * @param index
     * @return
     */

    public Product getProductAt(Integer index) {
        if (products.getProducts().getValue() != null &&
                index != null &&
                products.getProducts().getValue().size() > index) {
            return products.getProducts().getValue().get(index);
        }
        return null;
    }

    public void retry() {
        fetchList(clientId);
    }
}

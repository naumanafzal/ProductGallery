package com.mna.productgallery.viewmodel;

import android.view.View;

import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mna.productgallery.model.Product;
import com.mna.productgallery.model.Products;

/**
 * ProductViewModel
 */

public class ProcutViewModel extends ViewModel {
    Products products;
    public MutableLiveData<Void> onEndLive = new MutableLiveData<>();
    private MutableLiveData<Product> selected;
    public ObservableInt loading;
    public ObservableInt showEmpty;
    public ObservableInt hideEmpty;

    /**
     * initializer
     */
    public void init() {
        products = new Products();
        selected = new MutableLiveData<Product>();
        loading = new ObservableInt(View.GONE);
        showEmpty = new ObservableInt(View.GONE);
        hideEmpty = new ObservableInt(View.GONE);
    }

    public MutableLiveData<Product> getFetchedProduct() {
        return products.getProductDetail();
    }

    public MutableLiveData<Product> getSelected() {
        return selected;
    }

    /**
     *
     * @param product
     */
    public void setProduct(Product product) {
        selected.setValue(product);
    }

    public void onBackClick() {
        onEndLive.setValue(null);
    }
}

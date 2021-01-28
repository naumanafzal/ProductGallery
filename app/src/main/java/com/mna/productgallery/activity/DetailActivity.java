package com.mna.productgallery.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.mna.productgallery.R;
import com.mna.productgallery.databinding.ActivityDetailBinding;
import com.mna.productgallery.model.Product;
import com.mna.productgallery.viewmodel.ProcutViewModel;

/**
 * Detail Activity to show the Details of the Products
 */
public class DetailActivity extends AppCompatActivity {
    private ProcutViewModel viewModel;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupBindings(savedInstanceState);
    }

    /**
     *
     * @param savedInstanceState
     */
    private void setupBindings(Bundle savedInstanceState) {
        ActivityDetailBinding activityBinding = DataBindingUtil.setContentView(DetailActivity.this, R.layout.activity_detail);
        viewModel = new ViewModelProvider(DetailActivity.this).get(ProcutViewModel.class);
        if (savedInstanceState == null) {
            viewModel.init();
        }
        activityBinding.setViewModel(viewModel);
        activityBinding.setLifecycleOwner(this);
        setupListUpdate();
    }

    /**
     * setupListUpdate
     */
    private void setupListUpdate() {
        viewModel.loading.set(View.VISIBLE);
        Intent intent = getIntent();
        Product product = (Product) intent.getSerializableExtra("Product");
        viewModel.setProduct(product);
        viewModel.loading.set(View.GONE);
//        viewModel.getFetchedProduct().observe(DetailActivity.this, new Observer<Product>() {
//            @Override
//            public void onChanged(Product ProductNew) {
//                viewModel.loading.set(View.GONE);
//                if (ProductNew != null) {
//                    viewModel.showEmpty.set(View.GONE);
//                    viewModel.hideEmpty.set(View.VISIBLE);
//                    viewModel.setProduct(ProductNew);
//                } else {
//                    viewModel.showEmpty.set(View.VISIBLE);
//                    viewModel.hideEmpty.set(View.GONE);
//                }
//            }
//        });

        viewModel.onEndLive.observe(DetailActivity.this, new Observer<Void>() {
            @Override
            public void onChanged(Void i) {
                finish();
            }
        });
        //viewModel.fetchProductDetail(getString(R.string.client_id),Product.getCode());

    }

}

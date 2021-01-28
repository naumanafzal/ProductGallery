package com.mna.productgallery.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mna.productgallery.R;
import com.mna.productgallery.databinding.ActivityMainBinding;
import com.mna.productgallery.model.Product;
import com.mna.productgallery.viewmodel.ProductsViewModel;

import java.util.List;

/**
 * Main Activity of the Application
 * Shows the List of Products
 */
public class MainActivity extends AppCompatActivity {
    private ProductsViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupBindings(savedInstanceState);
    }

    private void setupBindings(Bundle savedInstanceState) {
        ActivityMainBinding activityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupViewCustom(activityBinding);
        viewModel = new ViewModelProvider(this).get(ProductsViewModel.class);
        if (savedInstanceState == null) {
            viewModel.init();
        }
        activityBinding.setModel(viewModel);
        activityBinding.setLifecycleOwner(this);
        setupListUpdate();

    }

    /**
     *
     * @param activityBinding
     */
    private void setupViewCustom(ActivityMainBinding activityBinding)
    {
        // Setup Divider
        DividerItemDecoration itemDecorator = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(ContextCompat.getDrawable(this, R.drawable.list_item_divid_space));
        activityBinding.listOfProducts.addItemDecoration(itemDecorator);
    }

    /**
     *
     */
    private void setupListUpdate() {
        viewModel.loading.set(View.VISIBLE);
        viewModel.getProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                viewModel.loading.set(View.GONE);
                if (products.size() == 0) {
                    viewModel.showEmpty.set(View.VISIBLE);
                } else {
                    viewModel.showEmpty.set(View.GONE);
                    viewModel.setProductsInAdapter(products);
                }
            }
        });
        viewModel.fetchList(getString(R.string.client_id));
        setupListClick();
    }

    /**
     *
     */
    private void setupListClick() {
        viewModel.getSelected().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("Product", product);
                MainActivity.this.startActivity(intent);
            }
        });
    }
}

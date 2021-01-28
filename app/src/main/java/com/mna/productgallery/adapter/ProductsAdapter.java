package com.mna.productgallery.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.mna.productgallery.BR;
import com.mna.productgallery.model.Product;
import com.mna.productgallery.viewmodel.ProductsViewModel;

import java.util.List;

/**
 * ProductsAdapter
 */
public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.GenericViewHolder> {

    private int layoutId;
    private List<Product> products;
    private ProductsViewModel viewModel;

    /**
     *
     * @param layoutId
     * @param viewModel
     */
    public ProductsAdapter(@LayoutRes int layoutId, ProductsViewModel viewModel) {
        this.layoutId = layoutId;
        this.viewModel = viewModel;
    }

    private int getLayoutIdForPosition(int position) {
        return layoutId;
    }

    @Override
    public int getItemCount() {
        return products == null ? 0 : products.size();
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return
     */
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false);

        return new GenericViewHolder(binding);
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        holder.bind(viewModel, position);
    }

    /**
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return getLayoutIdForPosition(position);
    }

    /**
     *
     * @param products
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     *
     */
    class GenericViewHolder extends RecyclerView.ViewHolder {
        final ViewDataBinding binding;

        GenericViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ProductsViewModel viewModel, Integer position) {
            binding.setVariable(BR.viewModel, viewModel);
            binding.setVariable(BR.position, position);
            binding.executePendingBindings();
        }

    }
}

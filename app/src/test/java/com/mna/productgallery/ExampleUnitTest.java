package com.mna.productgallery;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.mna.productgallery.model.Product;
import com.mna.productgallery.viewmodel.ProductsViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Mock
    private Observer<Product> observer;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_clickReturnCorrectObject() {
        List<Product> products = generateProducts();
        ProductsViewModel viewModel = new ProductsViewModel();
        viewModel.init();

        viewModel.getSelected().observeForever(observer);
        // When
        viewModel.getProducts().setValue(products);
        viewModel.onItemClick(0);
        // then
        verify(observer).onChanged(products.get(0));
    }

    private List<Product> generateProducts() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Product db = new Product();
            db.setType("Description " + i);
            products.add(db);
        }
        return products;
    }
}
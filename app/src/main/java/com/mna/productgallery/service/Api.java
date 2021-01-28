package com.mna.productgallery.service;

import com.mna.productgallery.model.ProductEntries;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * API CALL of th Backend Server
 * Implemented on the Basis of singleton pattern
 */
public class Api {

    private static ApiInterface api;
    private static final String BASE_URL = "https://gist.githubusercontent.com";

    /**
     *
     * @return
     */
    public static ApiInterface getApi() {
        if (api == null)
        {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();
//            Gson gson = new GsonBuilder()
//                    .registerTypeAdapter(
//                            Product.class)
//                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            api = retrofit.create(ApiInterface.class);
        }
        return api;
    }

    public interface ApiInterface {
        @GET("/r2vq/2ac197145db3f6cdf1a353feb744cf8e/raw/b1e722f608b00ddde138a0eef2261c6ffc8b08d7/cart.json")
        Call<ProductEntries> getProducts();
    }
}
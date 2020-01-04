package com.example.onlineshop.network.interfaces;

import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.Product;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ServiceProduct {

    @GET("products")
    Call<List<Product>> getProductBody(@QueryMap Map<String, String> productQueries);

    @GET("products/608")
    Call<Product> getSlider(@QueryMap Map<String, String> productQueries);

    @GET("products/categories")
    Call<List<CategoriesItem>> getProductCategory(@QueryMap Map<String, String> categoryQueries);

    @GET("products/?")
    Call<List<Product>> getAllProducts(@Query("orderby") String orderType, @QueryMap  Map<String, String> productQueries);

    @GET("products/categories")
    Call<List<CategoriesItem>> getAllCategories(@QueryMap  Map<String, String> productQueries);
}

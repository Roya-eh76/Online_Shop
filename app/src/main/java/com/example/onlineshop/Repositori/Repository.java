package com.example.onlineshop.Repositori;

import android.content.Context;

import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.ProductFetcher;
import com.example.onlineshop.network.interfaces.ServiceProduct;

import java.util.ArrayList;
import java.util.List;

public class Repository implements ProductFetcher.ProductFetcherCallbacks {
    private List<Product> mListBestProduct = new ArrayList<>();
    private List<Product> mListMostVisitedProduct = new ArrayList<>();
    private List<Product> mListLastProduct = new ArrayList<>();
    private List<CategoriesItem> mListCategori = new ArrayList<>();
    private static Repository instance=null;
    private ProductFetcher productFetcher;
    private Context mContext;


    public Repository() {
        productFetcher = new ProductFetcher(this);
        productFetcher.getProduct("rating");
        productFetcher.getProduct("popularity");
        productFetcher.getProduct("date");
        productFetcher.getAllCategori();

    }

    public static Repository getInstance() {
        if (instance == null)
            instance = new Repository();
        return instance;
    }

    public List<Product> getLastProduct(){
        return mListLastProduct;
    }

    public List<Product> getBestProduct(){
        return mListBestProduct;
    }

    public List<Product> getMostVisitedProduct(){
        return mListMostVisitedProduct;
    }

    public List<CategoriesItem> getCategori(){
        return mListCategori;
    }

    @Override
    public void onLastProductResponse(List<Product> items) {
        this.mListLastProduct=items;
    }

    @Override
    public void onBestProductResponse(List<Product> items) {
        this.mListBestProduct=items;
    }

    @Override
    public void onMostVisitedProductResponse(List<Product> items) {
        this.mListMostVisitedProduct=items;
    }

    @Override
    public void onCategoriResponse(List<CategoriesItem> categoriesItems) {
        this.mListCategori=categoriesItems;
    }
}

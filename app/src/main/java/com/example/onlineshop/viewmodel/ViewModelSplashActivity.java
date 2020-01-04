package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.onlineshop.network.ProductRepository;

public class ViewModelSplashActivity extends AndroidViewModel {
    private ProductRepository mProductRepository;

    public ViewModelSplashActivity(@NonNull Application application) {
        super(application);
        mProductRepository = ProductRepository.getInstance();
    }

    public void setItemsListsOfHomePage() {
        mProductRepository.getAllCategories();
        mProductRepository.getSlider();
        mProductRepository.getProduct("date");
        mProductRepository.getProduct("popularity");
        mProductRepository.getProduct("rating");
    }

}

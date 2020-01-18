package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;

import com.example.onlineshop.network.ProductRepository;

public class ViewModelSplashActivity extends ViewModelConnectivity {
    private ProductRepository mProductRepository;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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

package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.onlineshop.network.ProductRepositori;

public class ViewModelSplashActivity extends AndroidViewModel {
    private ProductRepositori mProductRepositori;

    public ViewModelSplashActivity(@NonNull Application application) {
        super(application);
        mProductRepositori = ProductRepositori.getInstance();
    }

    public void setItemsListsOfHomePage() {
        mProductRepositori.getAllCategori();
        mProductRepositori.getProduct("date");
        mProductRepositori.getProduct("popularity");
        mProductRepositori.getProduct("rating");
    }

}

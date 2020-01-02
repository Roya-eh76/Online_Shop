package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.Product;

import java.util.List;

public class ViewModelHomePageActivity extends AndroidViewModel {
    private MutableLiveData<List<Product>> mBestProductLiveData;
    private MutableLiveData<List<Product>> mMostVisitedProductLiveData;
    private MutableLiveData<List<Product>> mLastProductLiveData;
    private MutableLiveData<List<CategoriesItem>> mCategoriLiveData;
    private CategoriesItem mCategoriesItem;
    private Product mProduct;


    public ViewModelHomePageActivity(@NonNull Application application) {
        super(application);

    }

    public MutableLiveData<List<Product>> getmMostVisitedProductLiveData() {

        return mMostVisitedProductLiveData;
    }

    public MutableLiveData<List<Product>> getmLastProductLiveData() {

        return mLastProductLiveData;
    }

    public MutableLiveData<List<CategoriesItem>> getmCategoriLiveData() {

        return mCategoriLiveData;
    }






}

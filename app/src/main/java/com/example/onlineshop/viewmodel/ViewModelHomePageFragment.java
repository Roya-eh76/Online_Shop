package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.ProductRepository;

import java.util.List;

public class ViewModelHomePageFragment extends ViewModelConnectivity {
    private MutableLiveData<List<Product>> mBestProductLiveData;
    private MutableLiveData<List<Product>> mMostVisitedProductLiveData;
    private MutableLiveData<List<Product>> mLastProductLiveData;
    private MutableLiveData<List<CategoriesItem>> mCategoriLiveData;
    private MutableLiveData<Product> mSliderLiveData;
    private CategoriesItem mCategoriesItem;
    private ProductRepository mProductRepository=ProductRepository.getInstance();
    private Product mProduct;


    public ViewModelHomePageFragment(@NonNull Application application) {
        super(application);

    }

    public MutableLiveData<Product> getSliderLiveData() {
        return mProductRepository.getSliderProduct();
    }

    public MutableLiveData<List<Product>> getMostVisitedProductLiveData() {
        return mProductRepository.getMostVisitedProductLiveData();
    }

    public MutableLiveData<List<Product>> getLastProductLiveData() {
        return mProductRepository.getLastProductLiveData();
    }

    public MutableLiveData<List<CategoriesItem>> getCategoriesLiveData() {
        return mProductRepository.getCategoriesLiveData();
    }

    public MutableLiveData<List<Product>> getBestProductLiveData(){
        return mProductRepository.getBestProductLiveData();
    }






}

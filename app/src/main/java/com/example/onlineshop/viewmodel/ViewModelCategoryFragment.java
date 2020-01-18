package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.ProductRepository;

import java.util.List;

public class ViewModelCategoryFragment extends ViewModelConnectivity {
    private ProductRepository mProductRepository=ProductRepository.getInstance();
    private MutableLiveData<List<CategoriesItem>> mListMutableLiveData;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViewModelCategoryFragment(@NonNull Application application) {
        super(application);
    }


    public MutableLiveData<List<CategoriesItem>> getListMutableLiveData() {
        return mProductRepository.getCategoriesLiveData();
    }
}

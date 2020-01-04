package com.example.onlineshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.ProductRepository;

import java.util.List;

public class ViewModelDetailFragment extends AndroidViewModel {

    private ProductRepository mProductRepository=ProductRepository.getInstance();
    private MutableLiveData<List<Product>> mListMutableLiveData;


    public ViewModelDetailFragment(@NonNull Application application) {
        super(application);
    }



    public MutableLiveData<List<Product>> getListMutableLiveData() {
        return mProductRepository.getAllProduct();
    }
}

package com.example.onlineshop.viewmodel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;

import com.example.onlineshop.network.ProductRepository;
import com.example.onlineshop.view.activity.CheckNetworkActivity;

import static androidx.core.content.ContextCompat.getSystemService;

public class ViewModelConnectivity extends AndroidViewModel {
    private ProductRepository mProductRepository;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViewModelConnectivity(@NonNull Application application) {
        super(application);
        if(isNetworkConnected(getApplication().getApplicationContext()))
        {
            Toast.makeText(getApplication(), " Connection", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(getApplication(), "Not Connection", Toast.LENGTH_SHORT).show();
        }


    }

    private boolean isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public void setItemsListsOfHomePage() {
        mProductRepository = ProductRepository.getInstance();
        mProductRepository.getAllCategories();
        mProductRepository.getSlider();
        mProductRepository.getProduct("date");
        mProductRepository.getProduct("popularity");
        mProductRepository.getProduct("rating");
    }

}

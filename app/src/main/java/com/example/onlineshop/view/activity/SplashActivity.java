package com.example.onlineshop.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.onlineshop.R;
import com.example.onlineshop.viewmodel.ViewModelSplashActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ViewModelSplashActivity viewModelSplashActivity = ViewModelProviders.of(this)
                .get(ViewModelSplashActivity.class);
        viewModelSplashActivity.setItemsListsOfHomePage();

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, HomePageActivity.class);
            startActivity(intent);
            finish();
        }, 5000);


    }
}

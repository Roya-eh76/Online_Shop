package com.example.onlineshop.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.onlineshop.R;

public class HomePageActivity extends SingleFragmentActivity {


    public static Intent newIntent(Context context) {
        Intent intent =new Intent(context , HomePageActivity.class);
        return intent;
    }
    @Override
    public Fragment createFragment() {
        return HomePageFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}

package com.example.onlineshop.controller;

import android.content.Context;
import android.content.Intent;

import androidx.fragment.app.Fragment;

public class HomePageActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context ) {
        Intent intent =new Intent(context , HomePageActivity.class);
        return intent;
    }
    @Override
    public Fragment createFragment() {
        return HomePageFragment.newInstance();
    }
}

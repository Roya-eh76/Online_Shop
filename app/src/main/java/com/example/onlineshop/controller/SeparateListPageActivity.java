package com.example.onlineshop.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;
import java.util.PriorityQueue;

import androidx.fragment.app.Fragment;

import com.example.onlineshop.model.EnumSeparateProduct;
import com.example.onlineshop.model.Product;

public class SeparateListPageActivity extends SingleFragmentActivity {
    public static final String PUT_EXTRA_PRODUCT_NAME = "PUT_EXTRA_PRODUCT_NAME";
    private String str="";

    public static Intent newIntent(Context context, String str) {
        Intent intent =new Intent(context , SeparateListPageActivity.class);
        intent.putExtra(PUT_EXTRA_PRODUCT_NAME,str);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        str = getIntent().getStringExtra(PUT_EXTRA_PRODUCT_NAME);
        return SeparateListPageFragment.newInstance(str);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        str = getIntent().getStringExtra(PUT_EXTRA_PRODUCT_NAME);
        super.onCreate(savedInstanceState);

    }
}

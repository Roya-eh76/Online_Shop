package com.example.onlineshop.view.activity;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.onlineshop.view.fragment.ProductDetailFragment;

public class ProductDetailActivity extends SingleFragmentActivity {


    public static final String EXTRA_PRODUCT_ID = "EXTRA_PRODUCT_ID";

    public static Intent newIntent(Context context, int idProduct){
        Intent intent=new Intent(context, ProductDetailActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ID, idProduct);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return ProductDetailFragment.newInstance(getIntent().getIntExtra(EXTRA_PRODUCT_ID, 0));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

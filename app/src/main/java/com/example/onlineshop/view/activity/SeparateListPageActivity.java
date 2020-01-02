package com.example.onlineshop.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.example.onlineshop.view.fragment.SeparateListPageFragment;

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

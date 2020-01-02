package com.example.onlineshop.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.onlineshop.R;

public class ProductDetailActivity extends SingleFragmentActivity {



    public static Intent newIntent(Context context){
        Intent intent=new Intent(context, ProductDetailActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return ProductDetailFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

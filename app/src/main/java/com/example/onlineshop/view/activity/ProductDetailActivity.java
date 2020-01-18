package com.example.onlineshop.view.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import com.example.onlineshop.R;
import com.example.onlineshop.view.fragment.ProductDetailFragment;

public class ProductDetailActivity extends SingleFragmentActivity {
    private Toolbar toolbar;


    public static final String EXTRA_PRODUCT_ID = "EXTRA_PRODUCT_ID";

    public static Intent newIntent(Context context, int idProduct) {
        Intent intent = new Intent(context, ProductDetailActivity.class);
        intent.putExtra(EXTRA_PRODUCT_ID, idProduct);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return ProductDetailFragment.newInstance(getIntent().getIntExtra(EXTRA_PRODUCT_ID, 0));
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);

        toolbar = findViewById(R.id.toolber_activity);
        toolbar.setBackgroundResource(R.color.gray_100_transparent);
        toolbar.inflateMenu(R.menu.menu_detail_product);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_detail_product, menu);

        return true;
    }
}

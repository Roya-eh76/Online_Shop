package com.example.onlineshop.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import androidx.fragment.app.Fragment;

import com.example.onlineshop.R;
import com.example.onlineshop.view.fragment.SeparateListPageFragment;

public class SeparateListPageActivity extends SingleFragmentActivity {
    public static final String PUT_EXTRA_PRODUCT_NAME = "PUT_EXTRA_PRODUCT_NAME";
    private String str="";
    private Toolbar toolbar;

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
        getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        str = getIntent().getStringExtra(PUT_EXTRA_PRODUCT_NAME);
        super.onCreate(savedInstanceState);
        setSupportActionBar(toolbar);
        toolbar = findViewById(R.id.toolber_activity);
        toolbar.inflateMenu(R.menu.menu_home_page);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_home_page, menu);

        return true;
    }
}

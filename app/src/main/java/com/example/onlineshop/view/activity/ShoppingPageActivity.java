package com.example.onlineshop.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.onlineshop.view.fragment.ShoppingPageFragment;

public class ShoppingPageActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context)
    {
        Intent intent=new Intent(context,ShoppingPageActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return ShoppingPageFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

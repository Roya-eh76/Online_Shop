package com.example.onlineshop.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.onlineshop.R;
import com.example.onlineshop.view.fragment.CheckNetworkFragment;

public class CheckNetworkActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context context) {
        Intent intent =new Intent(context , CheckNetworkActivity.class);
        return intent;
    }

    public  void startActivity(){
        Intent intent=CheckNetworkActivity.newIntent(this);
        startActivity(intent);
    }

    @Override
    public Fragment createFragment() {
        return CheckNetworkFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}

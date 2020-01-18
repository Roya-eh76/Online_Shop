package com.example.onlineshop.view.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import com.example.onlineshop.R;
import com.example.onlineshop.view.fragment.CategoriViewPagerFragment;

public class ViewPagerCategoryActivity extends SingleFragmentActivity {
    public static final String PUT_EXTRA_ID_CATEGORY = "PUT_EXTRA_ID_CATEGORY";
    private Toolbar toolbar;
    private int mID;

    public static Intent newIntent(Context context, int mId) {
        Intent intent = new Intent(context, ViewPagerCategoryActivity.class);
        intent.putExtra(PUT_EXTRA_ID_CATEGORY, mId);
        return intent;
    }


    @Override
    public Fragment createFragment() {
        return CategoriViewPagerFragment.newInstance(getIntent().getIntExtra(PUT_EXTRA_ID_CATEGORY, 0));
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        toolbar = findViewById(R.id.toolber_activity);
        toolbar.inflateMenu(R.menu.menu_view_pager_category);
        toolbar.setTitle("دسته بندی محصولات");
        toolbar.setTitleTextColor(getResources().getColor(R.color.digikala_white_pure));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_view_pager_category, menu);

        return true;
    }
}

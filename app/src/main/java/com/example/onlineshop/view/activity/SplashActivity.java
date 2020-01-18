package com.example.onlineshop.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.IpSecManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import com.example.onlineshop.R;
import com.example.onlineshop.viewmodel.ViewModelSplashActivity;

public class SplashActivity extends AppCompatActivity {

    private ViewModelSplashActivity viewModelSplashActivity;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

       viewModelSplashActivity = ViewModelProviders.of(this)
                .get(ViewModelSplashActivity.class);

        /*new Handler().postDelayed(() -> {

            finish();
        }, 5000);
*/

       new PrefetchData().execute();

    }


    private class PrefetchData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            viewModelSplashActivity.setItemsListsOfHomePage();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(SplashActivity.this, HomePageActivity.class);
            startActivity(intent);
            finish();
        }
    }
}

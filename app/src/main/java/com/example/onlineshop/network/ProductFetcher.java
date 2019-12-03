package com.example.onlineshop.network;

import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.interfaces.ServiceProduct;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProductFetcher {
    public static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
    private ServiceProduct mServiceProduct;
    private Retrofit mRetrofit;
    private Map<String, String> mQueries;
    private ProductFetcherCallbacks mProductFetcherCallbacks;


    public ProductFetcher(ProductFetcherCallbacks productFetcherCallbacks) {
        mProductFetcherCallbacks=productFetcherCallbacks;

        mQueries = new HashMap<String, String>() {{
            put("consumer_key","ck_d05c3784194d242f00bb17531891c079fbaab282");
            put("consumer_secret", "cs_eea2f7bb6ee5ac731f2b5d8078a1ae9c83852cb8");

        }};

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mServiceProduct = mRetrofit.create(ServiceProduct.class);
    }

    public void getAllProduct() {
        Call<List<Product>> call = mServiceProduct.getProductBody(mQueries);
        call.enqueue(getRetrofitCallback());
    }

    public void getProduct(String str) {
       /* Call<List<Product>> call = mServiceProduct.getAllProducts();
        call.enqueue(getRetrofitCallback());*/
    }



    private Callback<List<Product>> getRetrofitCallback(){
        return new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.isSuccessful()) {
                    List<Product> product =  response.body();
                    mProductFetcherCallbacks.onLastProductResponse(product);
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        };
    }

    public interface ProductFetcherCallbacks {
        void onLastProductResponse(List<Product> items);
        void onNewProductResponse(List<Product> items);
        void onBestProductResponse(List<Product> items);
    }

}

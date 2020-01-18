package com.example.onlineshop.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.interfaces.ServiceProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProductRepository {
    public static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
    private ServiceProduct mServiceProduct;
    private Retrofit mRetrofit;
    private Map<String, String> mQueries;
    private static ProductRepository sInstance;
    private MutableLiveData<List<Product>> mBestProductLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mMostVisitedProductLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mLastProductLiveData = new MutableLiveData<>();
    private MutableLiveData<List<CategoriesItem>> mCategoriesLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mAllProduct = new MutableLiveData<>();
    private MutableLiveData<Product> mSliderProduct = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mSearchProduct = new MutableLiveData<>();
    private MutableLiveData<List<CategoriesItem>> mSubCategoriesItem =new MutableLiveData<>();
    private  List<CategoriesItem> categoriesItems=new ArrayList<>();

    public static ProductRepository getInstance() {
        if (sInstance == null)
            sInstance = new ProductRepository();

        return sInstance;
    }

    public MutableLiveData<Product> getSliderProduct() {
        return mSliderProduct;
    }

    public MutableLiveData<List<CategoriesItem>> getSubCategoriesItem() {
        return mSubCategoriesItem;
    }

    public MutableLiveData<List<CategoriesItem>> getCategoriesLiveData() {
        return mCategoriesLiveData;
    }

    public MutableLiveData<List<Product>> getBestProductLiveData() {
        return mBestProductLiveData;
    }

    public MutableLiveData<List<Product>> getMostVisitedProductLiveData() {
        return mMostVisitedProductLiveData;
    }

    public MutableLiveData<List<Product>> getLastProductLiveData() {
        return mLastProductLiveData;
    }

    public MutableLiveData<List<Product>> getAllProduct() {
        return mAllProduct;
    }

    public MutableLiveData<List<Product>> getSearchProduct() {
        return mSearchProduct;
    }

    private ProductRepository() {
        mQueries = new HashMap<String, String>() {{
            put("consumer_key", "ck_d05c3784194d242f00bb17531891c079fbaab282");
            put("consumer_secret", "cs_eea2f7bb6ee5ac731f2b5d8078a1ae9c83852cb8");

        }};

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mServiceProduct = mRetrofit.create(ServiceProduct.class);
    }

    public void getAllCategories() {
        Call<List<CategoriesItem>> call = mServiceProduct.getAllCategories(mQueries,"0");
        call.enqueue(getRetrofitCategoriesCallBack());
    }

    public void getSubCategories(String str){
        Call<List<CategoriesItem>> call =mServiceProduct.getSubCategories(mQueries, str);
        mSubCategoriesItem=new MutableLiveData<>();
        call.enqueue(getRetrofitSubCategorisCallBack());

    }

    public void getProduct(String orderType) {
        Call<List<Product>> call = mServiceProduct.getAllProducts(orderType, mQueries);
        call.enqueue(getRetrofitProductCallback(orderType));
    }

    public void getAllProducts() {
        Call<List<Product>> call = mServiceProduct.getProductBody(mQueries);
        call.enqueue(getRetrofitProductsCallback());
    }

    public void getSlider(){
        Call<Product> call = mServiceProduct.getSlider(mQueries);
        call.enqueue(getRetrofitSliderCallback());
    }

    private Callback<List<CategoriesItem>> getRetrofitSubCategorisCallBack(){

        mSubCategoriesItem=new MutableLiveData<>();

        return new Callback<List<CategoriesItem>>() {

            @Override
            public void onResponse(Call<List<CategoriesItem>> call, Response<List<CategoriesItem>> response) {
                List<CategoriesItem> categoriesItems =response.body();
                mSubCategoriesItem.setValue(categoriesItems);
            }

            @Override
            public void onFailure(Call<List<CategoriesItem>> call, Throwable t) {

            }
        };
    }

    private Callback<List<Product>> getRetrofitProductsCallback() {
        return new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> products = response.body();
                mAllProduct.setValue(products);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        };
    }

    private Callback<Product> getRetrofitSliderCallback() {
        return new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response <Product> response) {
                Product product = response.body();
                mSliderProduct.setValue(product);
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Log.d("", "onFailure: ");
            }
        };
    }


    private Callback<List<CategoriesItem>> getRetrofitCategoriesCallBack() {
        return new Callback<List<CategoriesItem>>() {
            @Override
            public void onResponse(Call<List<CategoriesItem>> call, Response<List<CategoriesItem>> response) {
                if (response.isSuccessful()) {
                    List<CategoriesItem> categoriesItems = response.body();
                    mCategoriesLiveData.setValue(categoriesItems);

                }
            }

            @Override
            public void onFailure(Call<List<CategoriesItem>> call, Throwable t) {

            }
        };
    }


    private Callback<List<Product>> getRetrofitProductCallback(final String str) {
        return new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()) {

                    List<Product> product = response.body();
                    if (str == null)
                        return;
                    if (str.equals("date")) {
                        mLastProductLiveData.setValue(product);
                    }
                    if (str.equals("popularity")) {
                        mMostVisitedProductLiveData.setValue(product);
                    }
                    if (str.equals("rating")) {
                        mBestProductLiveData.setValue(product);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        };
    }

    public void getProductSearchList(String querySearch) {
     mQueries.put("search", querySearch);
     Call<List<Product>> call=mServiceProduct.getProductBody(mQueries);
     call.enqueue(new Callback<List<Product>>() {
         @Override
         public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
             if (response.isSuccessful()){
             List<Product> list=response.body();
             mSearchProduct.setValue(list);}
             else {
             }
         }

         @Override
         public void onFailure(Call<List<Product>> call, Throwable t) {

         }
     });

    }


}

package com.example.onlineshop.network;

import androidx.lifecycle.MutableLiveData;

import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.interfaces.ServiceProduct;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProductRepositori {
    public static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";
    private ServiceProduct mServiceProduct;
    private Retrofit mRetrofit;
    private Map<String, String> mQueries;
   // private ProductFetcherCallbacks mProductFetcherCallbacks;
    private static ProductRepositori sInstance;
    private MutableLiveData<List<Product>> mBestProductLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mMostVisitedProductLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>> mLastProductLiveData = new MutableLiveData<>();
    private MutableLiveData<List<CategoriesItem>> mCategoriLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Product>>  mAllProduct=new MutableLiveData<>();


    public static ProductRepositori getInstance(){
        if(sInstance == null )
            sInstance = new ProductRepositori();

        return sInstance;
    }


    public MutableLiveData<List<CategoriesItem>> getmCategoriLiveData() {
        return mCategoriLiveData;
    }

    public MutableLiveData<List<Product>> getmBestProductLiveData() {
        return mBestProductLiveData;
    }

    public MutableLiveData<List<Product>> getmMostVisitedProductLiveData() {
        return mMostVisitedProductLiveData;
    }

    public MutableLiveData<List<Product>> getmLastProductLiveData() {
        return mLastProductLiveData;
    }

    public MutableLiveData<List<Product>> getmAllProduct() {
        return mAllProduct;
    }

    private ProductRepositori() {
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

    public void getAllCategori() {
        Call<List<CategoriesItem>> call = mServiceProduct.getAllCategories(mQueries);
        call.enqueue(getRetrofitCategoriCallBack());
    }

    public void getProduct(String str) {
        Call<List<Product>> call = mServiceProduct.getAllProducts(str,mQueries);
        call.enqueue(getRetrofitProductCallback(str));
    }

    public void getAllProducts(){
        Call<List<Product>> call=mServiceProduct.getProductBody(mQueries);
        call.enqueue(getRetrofitProductsCallback());
    }

    private Callback<List<Product>> getRetrofitProductsCallback(){
        return new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
               List<Product> products=response.body();
               mAllProduct.setValue(products);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        };
    }

    private Callback<List<CategoriesItem>> getRetrofitCategoriCallBack(){
        return new Callback<List<CategoriesItem>>() {
            @Override
            public void onResponse(Call<List<CategoriesItem>> call, Response<List<CategoriesItem>> response) {
                if(response.isSuccessful())
                {
                    List<CategoriesItem> categoriesItems=response.body();
                    mCategoriLiveData.setValue(categoriesItems);

                   // mProductFetcherCallbacks.onCategoriResponse(categoriesItems);
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
                    if(str==null)
                        return;
                    if(str.equals("date") ) {
                        //  mProductFetcherCallbacks.onLastProductResponse(product);
                        mLastProductLiveData.setValue(product);
                    }if(str.equals("popularity")) {
                        mMostVisitedProductLiveData.setValue(product);
                    } //  mProductFetcherCallbacks.onMostVisitedProductResponse(product);
                    if(str.equals("rating")) {
                        mBestProductLiveData.setValue(product);
                    }// mProductFetcherCallbacks.onBestProductResponse(product);
                }

            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

            }
        };
    }

    /*public interface ProductFetcherCallbacks {
        void onLastProductResponse(List<Product> items);

        void onBestProductResponse(List<Product> items);

        void onMostVisitedProductResponse(List<Product> items);

        void onCategoriResponse(List<CategoriesItem> categoriesItems);
    }*/

}

package com.example.onlineshop.view.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentHomePageBinding;
import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.EnumSeparate;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.ProductRepositori;
import com.example.onlineshop.view.adapter.ListAdapter;
import com.example.onlineshop.view.activity.SeparateListPageActivity;
import com.example.onlineshop.viewmodel.ViewModelHomePageActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {

    private List<Product> mListBestProduct = new ArrayList<>();
    private List<Product> mListMostVisitedProduct = new ArrayList<>();
    private List<Product> mListLastProduct = new ArrayList<>();
    private List<CategoriesItem> mListCategori = new ArrayList<>();
    private FragmentHomePageBinding mBinding;
    private ViewModelHomePageActivity mViewModelHomePageActivity;
    private ProductRepositori productRepositori = ProductRepositori.getInstance();
    private ListAdapter bestListAdapter = new ListAdapter(getContext(), EnumSeparate.productListHomePage), categoriListAdapter = new ListAdapter(getContext(), EnumSeparate.categori), lastListAdapter = new ListAdapter(getContext(), EnumSeparate.productListHomePage), mostVisitedListAdapter = new ListAdapter(getContext(), EnumSeparate.productListHomePage);

    /*private ProductRepositori productFetcher;*/


    public static HomePageFragment newInstance() {

        Bundle args = new Bundle();

        HomePageFragment fragment = new HomePageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public HomePageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         mViewModelHomePageActivity = ViewModelProviders.of(this).get(ViewModelHomePageActivity.class);

        productRepositori.getmBestProductLiveData().observe(getActivity(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                mListBestProduct = productList;
                BestProductSetAdapter();
            }
        });

        productRepositori.getmMostVisitedProductLiveData().observe(getActivity(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                mListMostVisitedProduct = productList;
                mostVisitedProductSetAdapter();
            }
        });

        productRepositori.getmLastProductLiveData().observe(getActivity(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                mListLastProduct = productList;
                lastProductSetAdapter();
            }
        });

        productRepositori.getmCategoriLiveData().observe(getActivity(), new Observer<List<CategoriesItem>>() {
            @Override
            public void onChanged(List<CategoriesItem> categoriesItems) {
                mListCategori = categoriesItems;
                categoriListSetAdapter();
            }
        });


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater
                , R.layout.fragment_home_page
                , container
                , false);


        productRepositori.getProduct("rating");
        productRepositori.getProduct("popularity");
        productRepositori.getProduct("date");
        productRepositori.getAllCategori();


        clickable();

        bestListAdapter = new ListAdapter(getContext(), EnumSeparate.productListHomePage);
        categoriListAdapter = new ListAdapter(getContext(), EnumSeparate.categori);
        lastListAdapter = new ListAdapter(getContext(), EnumSeparate.productListHomePage);
        mostVisitedListAdapter = new ListAdapter(getContext(), EnumSeparate.productListHomePage);


        mBinding.recyclerViewNewProduct.setLayoutManager(new LinearLayoutManager(getContext()
                , RecyclerView.HORIZONTAL, false));

        mBinding.recyclerViewMostVisitedProducts.setLayoutManager(new LinearLayoutManager(getContext()
                , RecyclerView.HORIZONTAL, false));

        mBinding.recyclerViewBestProduct.setLayoutManager(new LinearLayoutManager(getContext()
                , RecyclerView.HORIZONTAL, false));

        mBinding.recyclerViewCategori.setLayoutManager(new LinearLayoutManager(getContext()
                , RecyclerView.HORIZONTAL, false));

        lastProductSetAdapter();
        BestProductSetAdapter();
        mostVisitedProductSetAdapter();
        categoriListSetAdapter();


        return mBinding.getRoot();
    }


    private void clickable() {
        mBinding.txtViewfullBestProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SeparateListPageActivity.newIntent(getContext(), "best");
                startActivity(intent);
            }
        });
        mBinding.txtViewfullMostVisitedProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SeparateListPageActivity.newIntent(getContext(), "most");
                startActivity(intent);
            }
        });
        mBinding.txtViewfullNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SeparateListPageActivity.newIntent(getContext(), "last");
                startActivity(intent);
            }
        });
    }


    private void lastProductSetAdapter() {
        lastListAdapter.setmListProduct(mListLastProduct);
        mBinding.recyclerViewNewProduct.setAdapter(lastListAdapter);
        lastListAdapter.notifyDataSetChanged();
    }

    private void mostVisitedProductSetAdapter() {
        mostVisitedListAdapter.setmListProduct(mListMostVisitedProduct);
        mBinding.recyclerViewMostVisitedProducts.setAdapter(mostVisitedListAdapter);
        mostVisitedListAdapter.notifyDataSetChanged();
    }

    private void BestProductSetAdapter() {
        bestListAdapter.setmListProduct(mListBestProduct);
        mBinding.recyclerViewBestProduct.setAdapter(bestListAdapter);
        bestListAdapter.notifyDataSetChanged();

    }

    private void categoriListSetAdapter() {
        categoriListAdapter.setmListCategori(mListCategori);
        mBinding.recyclerViewCategori.setAdapter(categoriListAdapter);
        categoriListAdapter.notifyDataSetChanged();

    }
    private void setupSlider() {

        HashMap<String, String> url_maps = new HashMap<>();
        url_maps.put("لوازم برقی", "https://bucket-15.digicloud-oss.com/digikala-adservice-banners/1000012855.jpg");
        url_maps.put("صوتی و تصویری", "https://bucket-15.digicloud-oss.com/digikala-adservice-banners/1000012860.jpg");
        url_maps.put("مراقبت از پوست", "https://bucket-15.digicloud-oss.com/digikala-adservice-banners/1000012909.jpg");
        url_maps.put("لذت از لحظات", "https://bucket-15.digicloud-oss.com/digikala-adservice-banners/1000013192.jpg");


        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mBinding.slider.addSlider(textSliderView);
        }
        mBinding.slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mBinding.slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mBinding.slider.setDuration(5000);
    }

}

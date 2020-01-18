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


import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentHomePageBinding;
import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.EnumSeparate;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.ProductRepository;
import com.example.onlineshop.view.adapter.ListAdapter;
import com.example.onlineshop.view.activity.SeparateListPageActivity;
import com.example.onlineshop.view.adapter.SliderAdapter;
import com.example.onlineshop.viewmodel.ViewModelHomePageFragment;

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
    private ViewModelHomePageFragment mViewModelHomePageActivity;
    private ListAdapter bestListAdapter = new ListAdapter(getContext(), EnumSeparate.productListHomePage,1),
            categoriListAdapter = new ListAdapter(getContext(), EnumSeparate.category,0),
            lastListAdapter = new ListAdapter(getContext(), EnumSeparate.productListHomePage,1),
            mostVisitedListAdapter = new ListAdapter(getContext(), EnumSeparate.productListHomePage,1);
    private SliderAdapter mSliderAdapter;


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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModelHomePageActivity = ViewModelProviders.of(this).get(ViewModelHomePageFragment.class);

        mViewModelHomePageActivity.getBestProductLiveData().observe(getActivity(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                mListBestProduct = productList;
                BestProductSetAdapter();
            }
        });

        mViewModelHomePageActivity.getMostVisitedProductLiveData().observe(getActivity(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                mListMostVisitedProduct = productList;
                mostVisitedProductSetAdapter();
            }
        });

        mViewModelHomePageActivity.getLastProductLiveData().observe(getActivity(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                mListLastProduct = productList;
                lastProductSetAdapter();
            }
        });

        mViewModelHomePageActivity.getCategoriesLiveData().observe(getActivity(), new Observer<List<CategoriesItem>>() {
            @Override
            public void onChanged(List<CategoriesItem> categoriesItems) {
                mListCategori = categoriesItems;
                categoriListSetAdapter();
            }
        });

        mViewModelHomePageActivity.getSliderLiveData().observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                mSliderAdapter.setImagesItems(product.getImages());
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


        clickable();

        mSliderAdapter=new SliderAdapter(getContext());
        bestListAdapter = new ListAdapter(getContext(), EnumSeparate.productListHomePage,1);
        categoriListAdapter = new ListAdapter(getContext(), EnumSeparate.category,0);
        lastListAdapter = new ListAdapter(getContext(), EnumSeparate.productListHomePage,1);
        mostVisitedListAdapter = new ListAdapter(getContext(), EnumSeparate.productListHomePage,1);


        mBinding.recyclerViewNewProduct.setLayoutManager(new LinearLayoutManager(getContext()
                , RecyclerView.HORIZONTAL, false));

        mBinding.recyclerViewMostVisitedProducts.setLayoutManager(new LinearLayoutManager(getContext()
                , RecyclerView.HORIZONTAL, false));

        mBinding.recyclerViewBestProduct.setLayoutManager(new LinearLayoutManager(getContext()
                , RecyclerView.HORIZONTAL, false));

        mBinding.recyclerViewCategori.setLayoutManager(new LinearLayoutManager(getContext()
                , RecyclerView.HORIZONTAL, false));

        mBinding.imageSlider.setSliderAdapter(mSliderAdapter);


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
        lastListAdapter.setListProduct(mListLastProduct);
        mBinding.recyclerViewNewProduct.setAdapter(lastListAdapter);
        lastListAdapter.notifyDataSetChanged();
    }

    private void mostVisitedProductSetAdapter() {
        mostVisitedListAdapter.setListProduct(mListMostVisitedProduct);
        mBinding.recyclerViewMostVisitedProducts.setAdapter(mostVisitedListAdapter);
        mostVisitedListAdapter.notifyDataSetChanged();
    }

    private void BestProductSetAdapter() {
        bestListAdapter.setListProduct(mListBestProduct);
        mBinding.recyclerViewBestProduct.setAdapter(bestListAdapter);
        bestListAdapter.notifyDataSetChanged();

    }

    private void categoriListSetAdapter() {
        categoriListAdapter.setListCategory(mListCategori);
        mBinding.recyclerViewCategori.setAdapter(categoriListAdapter);
        categoriListAdapter.notifyDataSetChanged();

    }


}

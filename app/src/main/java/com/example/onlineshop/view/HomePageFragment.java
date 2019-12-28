package com.example.onlineshop.view;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
import com.example.onlineshop.network.ProductRepositori;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {

    private List<Product> mListBestProduct = new ArrayList<>();
    private List<Product> mListMostVisitedProduct = new ArrayList<>();
    private List<Product> mListLastProduct = new ArrayList<>();
    private List<CategoriesItem> mListCategori = new ArrayList<>();
    private ListAdapter bestListAdapter, lastListAdapter, mostVisitedListAdapter, categoriListAdapter;
    private FragmentHomePageBinding mBinding;
    private ProductRepositori productFetcher;


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
        productFetcher = ProductRepositori.getInstance();
        productFetcher.getmCategoriLiveData().observe(getActivity(), new Observer<List<CategoriesItem>>() {
            @Override
            public void onChanged(List<CategoriesItem> categoriesItems) {
                categoriListAdapter.setmListCategori(categoriesItems);
            }
        });

    productFetcher.getmBestProductLiveData().observe(getActivity(), new Observer<List<Product>>() {
        @Override
        public void onChanged(List<Product> productList) {
            bestListAdapter.setmListProduct(productList);
        }
    });

    productFetcher.getmLastProductLiveData().observe(getActivity(), new Observer<List<Product>>() {
        @Override
        public void onChanged(List<Product> productList) {
            lastListAdapter.setmListProduct(productList);
        }
    });

        productFetcher.getmMostVisitedProductLiveData().observe(getActivity(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                mostVisitedListAdapter.setmListProduct(productList);
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

        productFetcher.getProduct("rating");
        productFetcher.getProduct("popularity");
        productFetcher.getProduct("date");
        productFetcher.getAllCategori();


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

   /* @Override
    public void onLastProductResponse(List<Product> items) {
        mListLastProduct=items;

    }

    @Override
    public void onBestProductResponse(List<Product> items) {
        mListBestProduct=items;
    }

    @Override
    public void onMostVisitedProductResponse(List<Product> items) {
     mListMostVisitedProduct=items;
    }

    @Override
    public void onCategoriResponse(List<CategoriesItem> categoriesItems) {
        mListCategori=categoriesItems;}*/

}

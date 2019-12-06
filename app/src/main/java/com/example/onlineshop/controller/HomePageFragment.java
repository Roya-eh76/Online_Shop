package com.example.onlineshop.controller;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.onlineshop.R;
import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.EnumSeparate;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.ProductFetcher;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment implements ProductFetcher.ProductFetcherCallbacks {
    private com.daimajia.slider.library.SliderLayout slider;
    private RecyclerView recyclerViewLastProduct, recyclerViewMostVisitedProducts,
            recyclerViewBestProduct, recyclerViewCategori;
    private TextView txtViewfullBestProduct, txtViewfullMostVisitedProduct,
            txtViewfullNewProduct;
    private List<Product> mListBestProduct = new ArrayList<>();
    private List<Product> mListMostVisitedProduct = new ArrayList<>();
    private List<Product> mListLastProduct = new ArrayList<>();
    private List<CategoriesItem> mListCategori = new ArrayList<>();
    private ListAdapter bestListAdapter, lastListAdapter, mostVisitedListAdapter, categoriListAdapter;
    private ProductFetcher productFetcher;


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        init(view);
        clickable();

        lastProductSetAdapter();
        BestProductSetAdapter();
        mostVisitedProductSetAdapter();
        categoriListSetAdapter();


        recyclerViewLastProduct.setLayoutManager(new LinearLayoutManager(getContext()
                , RecyclerView.HORIZONTAL, false));
        recyclerViewMostVisitedProducts.setLayoutManager(new LinearLayoutManager(getContext()
                , RecyclerView.HORIZONTAL, false));

        recyclerViewBestProduct.setLayoutManager(new LinearLayoutManager(getContext()
                , RecyclerView.HORIZONTAL, false));

        recyclerViewCategori.setLayoutManager(new LinearLayoutManager(getContext()
                , RecyclerView.HORIZONTAL, false));

        update();


        return view;
    }


    private void init(View view) {
        recyclerViewLastProduct = view.findViewById(R.id.recyclerViewNewProduct);
        recyclerViewMostVisitedProducts = view.findViewById(R.id.recyclerViewMostVisitedProducts);
        recyclerViewBestProduct = view.findViewById(R.id.recyclerViewBestProduct);
        recyclerViewCategori = view.findViewById(R.id.recyclerViewCategori);
        txtViewfullBestProduct = view.findViewById(R.id.txtViewfullBestProduct);
        txtViewfullMostVisitedProduct = view.findViewById(R.id.txtViewfullMostVisitedProduct);
        txtViewfullNewProduct = view.findViewById(R.id.txtViewfullNewProduct);
        slider = view.findViewById(R.id.slider);

    }

    private void clickable() {
        txtViewfullBestProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        txtViewfullMostVisitedProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        txtViewfullNewProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public void onBestProductResponse(List<Product> items) {

        mListBestProduct = items;
        BestProductSetAdapter();


    }

    @Override
    public void onMostVisitedProductResponse(List<Product> items) {

        mListMostVisitedProduct = items;
        mostVisitedProductSetAdapter();


    }

    @Override
    public void onCategoriResponse(List<CategoriesItem> categoriesItems) {
        mListCategori = categoriesItems;
        categoriListSetAdapter();

    }


    @Override
    public void onLastProductResponse(List<Product> items) {
        mListLastProduct = items;

        lastProductSetAdapter();

    }

    public void update() {
        productFetcher = new ProductFetcher(this);
        productFetcher.getProduct("rating");
        productFetcher.getProduct("popularity");
        productFetcher.getProduct("date");
        productFetcher.getAllCategori();

    }

    private void lastProductSetAdapter() {
        lastListAdapter = new ListAdapter(getContext(), EnumSeparate.productListHomePage);
        lastListAdapter.setmListProduct(mListLastProduct);
        recyclerViewLastProduct.setAdapter(lastListAdapter);

    }

    private void mostVisitedProductSetAdapter() {
        mostVisitedListAdapter = new ListAdapter(getContext(), EnumSeparate.productListHomePage);
        mostVisitedListAdapter.setmListProduct(mListMostVisitedProduct);
        recyclerViewMostVisitedProducts.setAdapter(mostVisitedListAdapter);
    }

    private void BestProductSetAdapter() {
        bestListAdapter = new ListAdapter(getContext(), EnumSeparate.productListHomePage);
        bestListAdapter.setmListProduct(mListBestProduct);
        recyclerViewBestProduct.setAdapter(bestListAdapter);
    }

    private void categoriListSetAdapter() {
        categoriListAdapter = new ListAdapter(getContext(), EnumSeparate.categori);
        categoriListAdapter.setmListCategori(mListCategori);
        recyclerViewCategori.setAdapter(categoriListAdapter);
    }
}

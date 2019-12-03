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
import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.ProductFetcher;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment implements ProductFetcher.ProductFetcherCallbacks {
    private com.daimajia.slider.library.SliderLayout slider;
    private RecyclerView recyclerViewNewProduct, recyclerViewMostVisitedProducts,
            recyclerViewBestProduct;
    private TextView txtViewfullBestProduct, txtViewfullMostVisitedProduct,
            txtViewfullNewProduct;
    private List<Product> mListBestProduct = new ArrayList<>();
    private List<Product> mListfullMostVisitedProduct = new ArrayList<>();
    private List<Product> mListfullNewProduct = new ArrayList<>();
    private ProductAdapter productAdapter;
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
         //productFetcher=new ProductFetcher(this);

        productAdapter = new ProductAdapter(getContext());
        productAdapter.setmListProduct(mListBestProduct);
        recyclerViewBestProduct.setAdapter(productAdapter);
        recyclerViewBestProduct.setLayoutManager(new LinearLayoutManager(getContext()
                , RecyclerView.HORIZONTAL, false));
        update();


        return view;
    }


    private void init(View view) {
        recyclerViewNewProduct = view.findViewById(R.id.recyclerViewNewProduct);
        recyclerViewMostVisitedProducts = view.findViewById(R.id.recyclerViewMostVisitedProducts);
        recyclerViewBestProduct = view.findViewById(R.id.recyclerViewBestProduct);
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
    public void onLastProductResponse(List<Product> items) {

        mListBestProduct=items;

        productAdapter = new ProductAdapter(getContext());
        productAdapter.setmListProduct(mListBestProduct);
        recyclerViewBestProduct.setAdapter(productAdapter);

    }

    @Override
    public void onNewProductResponse(List<Product> items) {
        mListfullNewProduct=items;

        productAdapter=new ProductAdapter(getContext());
        productAdapter.setmListProduct(mListfullNewProduct);
        recyclerViewNewProduct.setAdapter(productAdapter);

    }

    @Override
    public void onBestProductResponse(List<Product> items) {

    }

    public void update(){
        productFetcher=new ProductFetcher(this);
        productFetcher.getAllProduct();
    }
}

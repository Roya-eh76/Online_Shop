package com.example.onlineshop.controller;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.onlineshop.R;
import com.example.onlineshop.Repositori.Repository;
import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.EnumSeparate;
import com.example.onlineshop.model.EnumSeparateProduct;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.ProductFetcher;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomePageFragment extends Fragment {
    private com.daimajia.slider.library.SliderLayout slider;
    private RecyclerView recyclerViewLastProduct, recyclerViewMostVisitedProducts,
            recyclerViewBestProduct, recyclerViewCategori;
    private TextView txtViewFullBestProduct, txtViewFullMostVisitedProduct,
            txtViewFullLastProduct;
    private List<Product> mListBestProduct = new ArrayList<>();
    private List<Product> mListMostVisitedProduct = new ArrayList<>();
    private List<Product> mListLastProduct = new ArrayList<>();
    private List<CategoriesItem> mListCategori = new ArrayList<>();
    private ListAdapter bestListAdapter, lastListAdapter, mostVisitedListAdapter, categoriListAdapter;
    private Repository repository;


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


        repository=Repository.getInstance();
        mListBestProduct=repository.getBestProduct();
        mListLastProduct=repository.getLastProduct();
        mListMostVisitedProduct=repository.getMostVisitedProduct();
        mListCategori=repository.getCategori();

        bestListAdapter = new ListAdapter(getContext(), EnumSeparate.productListHomePage);
        categoriListAdapter = new ListAdapter(getContext(), EnumSeparate.categori);
        lastListAdapter = new ListAdapter(getContext(), EnumSeparate.productListHomePage);
        mostVisitedListAdapter = new ListAdapter(getContext(), EnumSeparate.productListHomePage);


        recyclerViewLastProduct.setLayoutManager(new LinearLayoutManager(getContext()
                , RecyclerView.HORIZONTAL, false));
        recyclerViewMostVisitedProducts.setLayoutManager(new LinearLayoutManager(getContext()
                , RecyclerView.HORIZONTAL, false));

        recyclerViewBestProduct.setLayoutManager(new LinearLayoutManager(getContext()
                , RecyclerView.HORIZONTAL, false));

        recyclerViewCategori.setLayoutManager(new LinearLayoutManager(getContext()
                , RecyclerView.HORIZONTAL, false));

        lastProductSetAdapter();
        BestProductSetAdapter();
        mostVisitedProductSetAdapter();
        categoriListSetAdapter();







        return view;
    }


    private void init(View view) {
        recyclerViewLastProduct = view.findViewById(R.id.recyclerViewNewProduct);
        recyclerViewMostVisitedProducts = view.findViewById(R.id.recyclerViewMostVisitedProducts);
        recyclerViewBestProduct = view.findViewById(R.id.recyclerViewBestProduct);
        recyclerViewCategori = view.findViewById(R.id.recyclerViewCategori);
        txtViewFullBestProduct = view.findViewById(R.id.txtViewfullBestProduct);
        txtViewFullMostVisitedProduct = view.findViewById(R.id.txtViewfullMostVisitedProduct);
        txtViewFullLastProduct = view.findViewById(R.id.txtViewfullNewProduct);
        slider = view.findViewById(R.id.slider);

    }

    private void clickable() {
        txtViewFullBestProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=SeparateListPageActivity.newIntent(getContext(),"best");
               startActivity(intent);
            }
        });
        txtViewFullMostVisitedProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=SeparateListPageActivity.newIntent(getContext(),"most");
                startActivity(intent);
            }
        });
        txtViewFullLastProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=SeparateListPageActivity.newIntent(getContext(),"last");
                startActivity(intent);
            }
        });
    }




    private void lastProductSetAdapter() {
        lastListAdapter.setmListProduct(mListLastProduct);
        recyclerViewLastProduct.setAdapter(lastListAdapter);
        lastListAdapter.notifyDataSetChanged();

    }

    private void mostVisitedProductSetAdapter() {
        mostVisitedListAdapter.setmListProduct(mListMostVisitedProduct);
        recyclerViewMostVisitedProducts.setAdapter(mostVisitedListAdapter);
        mostVisitedListAdapter.notifyDataSetChanged();
    }

    private void BestProductSetAdapter() {
        bestListAdapter.setmListProduct(mListBestProduct);
        recyclerViewBestProduct.setAdapter(bestListAdapter);
        bestListAdapter.notifyDataSetChanged();

    }

    private void categoriListSetAdapter() {
        categoriListAdapter.setmListCategori(mListCategori);
        recyclerViewCategori.setAdapter(categoriListAdapter);
        categoriListAdapter.notifyDataSetChanged();

    }
}

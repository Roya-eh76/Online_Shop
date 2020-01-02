package com.example.onlineshop.view;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentProductDetailBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailFragment extends Fragment {
    private FragmentProductDetailBinding mBinding;


    public static ProductDetailFragment newInstance() {

        Bundle args = new Bundle();

        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public ProductDetailFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mBinding = DataBindingUtil.inflate(inflater
                , R.layout.fragment_product_detail
                , container
                , false);

        return mBinding.getRoot();
    }

}

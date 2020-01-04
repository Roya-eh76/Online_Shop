package com.example.onlineshop.view.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingPageFragment extends Fragment {


    public ShoppingPageFragment() {
        // Required empty public constructor
    }

    public static ShoppingPageFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ShoppingPageFragment fragment = new ShoppingPageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_page, container, false);
    }

}

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
public class CheckNetworkFragment extends Fragment {

    public static CheckNetworkFragment newInstance() {

        Bundle args = new Bundle();

        CheckNetworkFragment fragment = new CheckNetworkFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public CheckNetworkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_check_network, container, false);
    }

}

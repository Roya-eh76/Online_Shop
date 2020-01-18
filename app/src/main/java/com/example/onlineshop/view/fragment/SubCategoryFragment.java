package com.example.onlineshop.view.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.EnumSeparate;
import com.example.onlineshop.network.ProductRepository;
import com.example.onlineshop.view.adapter.ListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubCategoryFragment extends Fragment {
    public static final String ARGS_MID_CATEGORY = "args_mId_category";
    private int mID;
    private RecyclerView mRecyclerView;
    private ProductRepository mProductRepository=ProductRepository.getInstance();
    private List<CategoriesItem> mCategoriesItems;
    private ListAdapter mListAdapter;


    public static SubCategoryFragment newInstance(int mId) {

        Bundle args = new Bundle();
        args.putInt(ARGS_MID_CATEGORY, mId);
        SubCategoryFragment fragment = new SubCategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public SubCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mID = getArguments().getInt(ARGS_MID_CATEGORY);

        mProductRepository.getSubCategories(mID + "");

        mProductRepository.getSubCategoriesItem().observe(this, new Observer<List<CategoriesItem>>() {
            @Override
            public void onChanged(List<CategoriesItem> categoriesItems) {
                mCategoriesItems=new ArrayList<>();
                mCategoriesItems = categoriesItems;

                mListAdapter.setCategoriesItemList(mCategoriesItems);
                mRecyclerView.setAdapter(mListAdapter);
                mListAdapter.notifyDataSetChanged();



            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub_category_fragment, container, false);

        mProductRepository = ProductRepository.getInstance();
        mCategoriesItems = new ArrayList<>();
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mListAdapter = new ListAdapter(getContext(), EnumSeparate.subCategories, 0);

        mListAdapter.setCategoriesItemList(mCategoriesItems);
        mRecyclerView.setAdapter(mListAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()
                , RecyclerView.VERTICAL, false));


        return view;
    }

}

package com.example.onlineshop.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;

import com.example.onlineshop.databinding.FragmentListSeparateBinding;
import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.EnumSeparate;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.ProductRepositori;

import java.util.ArrayList;
import java.util.List;

public class SeparateListPageFragment extends Fragment {

    public static final String PUT_ARGUMENTS_STRING = "PUT_ARGUMENTS_STRING";
    private RecyclerView recyclerViewSeparateListProduct;
    private List<Product> mListBestProduct = new ArrayList<>();
    private List<Product> mListMostVisitedProduct = new ArrayList<>();
    private List<Product> mListLastProduct = new ArrayList<>();
    private List<CategoriesItem> mListCategori = new ArrayList<>();
    private String str="";
    private ListAdapter mAdapter;
    private FragmentListSeparateBinding mBinding;

    public static SeparateListPageFragment newInstance(String str) {

        Bundle args = new Bundle();
        args.putString(PUT_ARGUMENTS_STRING,str);

        SeparateListPageFragment fragment = new SeparateListPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        str =getArguments().getString(PUT_ARGUMENTS_STRING);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //View view = inflater.inflate(R.layout.fragment_list_separate, container, false);
        mBinding= DataBindingUtil.inflate(inflater,
                R.layout.fragment_list_separate,
                container,
                false);
       ProductRepositori repository = ProductRepositori.getInstance();

        mAdapter=new ListAdapter(getContext(), EnumSeparate.productListHomePage);
        mBinding.recyclerViewSeparateListProduct.setLayoutManager(new GridLayoutManager(getContext(), 2));

       /* if (str.equals("best")) {
            mListBestProduct = repository.getBestProduct();
            mAdapter.setmListProduct(mListBestProduct);
        } else if (str.equals("last")) {
            mListLastProduct = repository.getLastProduct();
            mAdapter.setmListProduct(mListLastProduct);
        }
        else if (str.equals("most")) {
            mListMostVisitedProduct = repository.getMostVisitedProduct();
            mAdapter.setmListProduct(mListMostVisitedProduct);
       }*/
        recyclerViewSeparateListProduct.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


        return mBinding.getRoot();
    }
}

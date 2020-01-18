package com.example.onlineshop.view.fragment;


import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentProductDetailBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.ProductRepository;
import com.example.onlineshop.view.adapter.SliderAdapter;
import com.example.onlineshop.viewmodel.ViewModelDetailFragment;
import com.example.onlineshop.viewmodel.ViewModelHomePageFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductDetailFragment extends Fragment {
    public static final String ARGS_ID_PRODUCT = "ARGS_ID_PRODUCT";
    private FragmentProductDetailBinding mBinding;
    private List<Product> mAllProduct = new ArrayList<>();
    private ProductRepository mProductRepository = ProductRepository.getInstance();
    private int mId = 0;
    private Product mProduct;
    private SliderAdapter mSliderAdapter;
    private ViewModelDetailFragment mViewModelDetailFragment;


    public static ProductDetailFragment newInstance(int idProduct) {

        Bundle args = new Bundle();
        args.putSerializable(ARGS_ID_PRODUCT, idProduct);
        ProductDetailFragment fragment = new ProductDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public ProductDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mId = (int) getArguments().get(ARGS_ID_PRODUCT);

        mViewModelDetailFragment = ViewModelProviders.of(this).get(ViewModelDetailFragment.class);

        mViewModelDetailFragment.getListMutableLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> productList) {
                mAllProduct = productList;

                for (Product product : mAllProduct) {
                    if (product.getId() == mId)
                        mProduct = product;
                }

                mBinding.tvProductDetailsMainTitle.setText(mProduct.getName());

                if (mProduct.getPrice() != null)
                    mBinding.tvProductDetailsPayablePrice.setText(mProduct.getPrice());
                if (mProduct.getRegularPrice() != null)
                    mBinding.tvProductDetailsRealPrice.setText(mProduct.getRegularPrice());
                if(mProduct.getAverageRating()!=null)
                    mBinding.ratingBarProductDetail.setRating(mProduct.getRatingCount());
                if(mProduct.getDescription()!=null)
                    mBinding.tvDescriptorDetailProduct.setText(mProduct.getDescription());
                mBinding.tvProductDetailsRealPrice.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);

                /*setupSlider();*/
                mSliderAdapter.setImagesItems(mProduct.getImages());


            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mBinding = DataBindingUtil.inflate(inflater
                , R.layout.fragment_product_detail
                , container
                , false);

        mSliderAdapter=new SliderAdapter(getContext());

        mBinding.imageSlider.setSliderAdapter(mSliderAdapter);

        mProductRepository.getAllProducts();


        return mBinding.getRoot();
    }

}

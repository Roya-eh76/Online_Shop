package com.example.onlineshop.view.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.onlineshop.R;
import com.example.onlineshop.databinding.FragmentProductDetailBinding;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.network.ProductRepositori;

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
    private ProductRepositori mProductRepositori = ProductRepositori.getInstance();
    private int mId = 0;
    private Product mProduct;


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


        mProductRepositori.getmAllProduct().observe(getActivity(), new Observer<List<Product>>() {
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
        mProductRepositori.getAllProducts();


        return mBinding.getRoot();
    }

    private void setupSlider() {

        HashMap<String, String> url_maps = new HashMap<>();

        for (int i = 0; i < mProduct.getImages().size(); i++) {
            url_maps.put("", mProduct.getImages().get(i).getSrc());
        }

        for (String name : url_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mBinding.sliderDetailProduct.addSlider(textSliderView);
        }
        /*mBinding.sliderDetailProduct.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mBinding.sliderDetailProduct.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mBinding.sliderDetailProduct.setDuration(5000);*/
    }
}

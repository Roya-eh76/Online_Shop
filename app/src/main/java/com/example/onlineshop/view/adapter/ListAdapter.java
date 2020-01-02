package com.example.onlineshop.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.databinding.ItemCategoriBinding;
import com.example.onlineshop.databinding.ItemListProductBinding;
import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.EnumSeparate;
import com.example.onlineshop.model.Product;
import com.example.onlineshop.view.activity.ProductDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Product> mListProduct;
    protected List<CategoriesItem> mListCategori;
    private Context mContext;
    private EnumSeparate e;


    public void setmListProduct(List<Product> mListProduct) {
        this.mListProduct = mListProduct;
    }

    public void setmListCategori(List<CategoriesItem> mListCategori) {
        this.mListCategori = mListCategori;
    }

    public ListAdapter(Context mContext, EnumSeparate e) {
        this.mContext = mContext;
        this.e = e;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        if (e.equals(EnumSeparate.productListHomePage)) {
            ItemListProductBinding binding = DataBindingUtil.
                    inflate(layoutInflater, R.layout.item_list_product, parent, false);
            ProductHolder productHolder = new ProductHolder(binding);
            return productHolder;
        } else if (e.equals(EnumSeparate.categori)) {
            ItemCategoriBinding binding = DataBindingUtil.
                    inflate(layoutInflater, R.layout.item_categori, parent, false);
            CategoriHolder categoriHolder = new CategoriHolder(binding);
            return categoriHolder;
        } else
            return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (e.equals(EnumSeparate.productListHomePage)) {
            ((ProductHolder) holder).bindProduct(mListProduct.get(position));

        } else if (e.equals(EnumSeparate.categori)) {
            ((CategoriHolder) holder).bindCategori(mListCategori.get(position));
        }

    }


    @Override
    public int getItemCount() {
        if (e.equals(EnumSeparate.productListHomePage)) {
            return mListProduct.size();
        } else
            return mListCategori.size();

    }


    public class ProductHolder extends RecyclerView.ViewHolder {
        private ItemListProductBinding mBinding;
        private Product mProduct;



        public ProductHolder(ItemListProductBinding binding) {
            super(binding.getRoot());
            mBinding = binding;


            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent= ProductDetailActivity.newIntent(mContext,mProduct.getId());
                    mContext.startActivity(intent);
                }
            });

        }

        public void bindProduct(Product product) {

            mProduct = product;
            Picasso.get().load(mProduct.getImages().get(0).getSrc()).into(mBinding.imageViewProduct);
            mBinding.textViewNameProduct.setText(mProduct.getName());
            mBinding.textViewPriceProduct.setText(mProduct.getPrice());
        }
    }


    public class CategoriHolder extends RecyclerView.ViewHolder {
        private ItemCategoriBinding mBinding;

        public CategoriHolder(ItemCategoriBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bindCategori(CategoriesItem mCategori) {
            mBinding.setCategories(mCategori);

        }
    }
}

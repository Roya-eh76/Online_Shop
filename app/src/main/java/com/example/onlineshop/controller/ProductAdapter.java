package com.example.onlineshop.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshop.R;
import com.example.onlineshop.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private List<Product> mListProduct;
    private Context mContext;


    public void setmListProduct(List<Product> mListProduct) {
        this.mListProduct = mListProduct;
    }

    public ProductAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_product, parent, false);
        ProductHolder productHolder = new ProductHolder(view);
        return productHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        holder.bind(mListProduct.get(position));
    }

    @Override
    public int getItemCount() {
        return mListProduct.size();
    }


    public class ProductHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewProduct;
        private TextView textViewNameProduct, textViewPriceProduct;
        private Product mProduct;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            textViewNameProduct = itemView.findViewById(R.id.textViewNameProduct);
            textViewPriceProduct = itemView.findViewById(R.id.textViewPriceProduct);
        }

        public void bind(Product mProduct) {
/*
            imageViewProduct.setImageURI();

*/
//Picasso.get().load(mProduct.)
            Picasso.get().load(mProduct.getImages().get(0).getSrc()).into(imageViewProduct);
            textViewNameProduct.setText(mProduct.getName());
            textViewPriceProduct.setText(mProduct.getPrice());



        }
    }
}

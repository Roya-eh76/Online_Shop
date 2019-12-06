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
import com.example.onlineshop.model.CategoriesItem;
import com.example.onlineshop.model.EnumSeparate;
import com.example.onlineshop.model.Product;
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
            View view = layoutInflater.inflate(R.layout.item_list_product, parent, false);
            ProductHolder productHolder = new ProductHolder(view);
            return productHolder;
        } else if (e.equals(EnumSeparate.categori)) {
            View view = layoutInflater.inflate(R.layout.item_categori, parent, false);
            CategoriHolder categoriHolder = new CategoriHolder(view);
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
        private ImageView imageViewProduct;
        private TextView textViewNameProduct, textViewPriceProduct;
        private Product mProduct;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProduct = itemView.findViewById(R.id.imageViewProduct);
            textViewNameProduct = itemView.findViewById(R.id.textViewNameProduct);
            textViewPriceProduct = itemView.findViewById(R.id.textViewPriceProduct);
        }

        public void bindProduct(Product mProduct) {
            Picasso.get().load(mProduct.getImages().get(0).getSrc()).into(imageViewProduct);
            textViewNameProduct.setText(mProduct.getName());
            textViewPriceProduct.setText(mProduct.getPrice());
        }
    }


    public class CategoriHolder extends RecyclerView.ViewHolder {
        private TextView textViewNameCategori;

        public CategoriHolder(@NonNull View itemView) {
            super(itemView);
            textViewNameCategori = itemView.findViewById(R.id.textViewNameCategori);
        }

        public void bindCategori(CategoriesItem mCategori) {

            textViewNameCategori.setText(mCategori.getName());

        }
    }
}

package com.example.onlineshop.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.onlineshop.model.ImagesItem;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderHolder> {
    private Context mContext;
    private List<ImagesItem> mImagesItems = new ArrayList<>();

    public void setImagesItems(List<ImagesItem> imagesItems) {
        mImagesItems = imagesItems;
        notifyDataSetChanged();
    }

    public SliderAdapter(Context context) {
        mContext = context;
    }

    @Override
    public SliderHolder onCreateViewHolder(ViewGroup parent) {
        return new SliderHolder(new ImageView(mContext));
    }

    @Override
    public void onBindViewHolder(SliderHolder viewHolder, int position) {
        viewHolder.bind(mImagesItems.get(position));

    }

    @Override
    public int getCount() {
        return mImagesItems.size();
    }

    class SliderHolder extends SliderViewAdapter.ViewHolder {
        private ImageView mImageView;

        public SliderHolder(View itemView) {
            super(itemView);
            mImageView= (ImageView) itemView;
        }

        public void bind(ImagesItem imagesItem){
            Picasso.get().load(imagesItem.getSrc()).into(mImageView);
        }
    }
}


package com.gmail.berezin.serg.imagesearch.adapters;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.gmail.berezin.serg.imagesearch.R;
import com.gmail.berezin.serg.imagesearch.models.SearchImage;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.MyViewHolder> {

    List<SearchImage> imgList;


    public SearchResultsAdapter(List<SearchImage> imgList) {
        this.imgList = imgList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SearchImage searchImage = imgList.get(position);
        Picasso.with(holder.vImg.getContext()).load(searchImage.getUrl()).into(holder.vImg);
        holder.vTitle.setText(searchImage.getName());
        holder.vSelect.setEnabled(searchImage.isSelected());

    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView vImg;
        TextView vTitle;
        CheckBox vSelect;

        public MyViewHolder(View itemView) {
            super(itemView);
            vImg = (ImageView) itemView.findViewById(R.id.iv_image_item);
            vTitle = (TextView) itemView.findViewById(R.id.tv_name_item);
            vSelect = (CheckBox) itemView.findViewById(R.id.chb_select_item);
        }
    }
}

package com.netforce.ray.profile.news;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforce.ray.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<RowData> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<RowData> itemList) {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (itemList.get(position).imageProduct.equalsIgnoreCase("simple")) {
            return SIMPLE_TYPE;
        } else {
            return IMAGE_TYPE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == SIMPLE_TYPE) {
            View view = inflater.inflate(R.layout.row_news_simple, parent, false);
            NewsHolderSimple viewHolder = new NewsHolderSimple(view);
            return viewHolder;
        } else {
            View view = inflater.inflate(R.layout.row_news_image, parent, false);
            NewsHolderImage viewHolder = new NewsHolderImage(view);
            return viewHolder;
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case SIMPLE_TYPE:
                NewsHolderSimple newsHolderSimple = (NewsHolderSimple) holder;
                newsHolderSimple.materialRippleLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage("Yet to implememt");
                    }
                });
                break;
            case IMAGE_TYPE:
                NewsHolderImage newsHolderImage = (NewsHolderImage) holder;
                newsHolderImage.materialRippleLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage("yet to implement");
                    }
                });
                break;
        }
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return itemList.size();
//        return itemList.size();
    }


}

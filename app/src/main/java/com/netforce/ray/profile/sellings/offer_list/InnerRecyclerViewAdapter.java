package com.netforce.ray.profile.sellings.offer_list;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforce.ray.R;

import java.util.ArrayList;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class InnerRecyclerViewAdapter extends RecyclerView.Adapter<InnerRecyclerViewHolder> {

    private ArrayList<InnerRowData> itemList;
    private Context context;

    public InnerRecyclerViewAdapter(Context context, ArrayList<InnerRowData> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public InnerRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_offer, parent, false);
        InnerRecyclerViewHolder viewHolder = new InnerRecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(InnerRecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
//        Log.i("count_", "" + itemList.size());
        return itemList.size();
    }
}

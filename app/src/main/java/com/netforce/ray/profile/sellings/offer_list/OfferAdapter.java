package com.netforce.ray.profile.sellings.offer_list;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforce.ray.R;

import java.util.ArrayList;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class OfferAdapter extends RecyclerView.Adapter<OfferHolder> {

    private ArrayList<OfferData> itemList;
    private Context context;

    public OfferAdapter(Context context, ArrayList<OfferData> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public OfferHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_offer, parent, false);
        OfferHolder viewHolder = new OfferHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OfferHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
//        Log.i("count_", "" + itemList.size());
        return itemList.size();
    }
}

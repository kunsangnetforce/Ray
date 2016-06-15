package com.netforce.ray.profile.sellings;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforce.ray.R;
import com.netforce.ray.profile.sellings.offer_list.InnerRecyclerViewAdapter;

import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SOLD = 0;
    private static final int AVAILABLE = 1;
    private final LayoutInflater inflater;
    private List<RowData> itemList;
    private Context context;
    private InnerRecyclerViewAdapter adapter;

    public RecyclerViewAdapter(Context context, List<RowData> itemList) {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        if (itemList.get(position).status.equalsIgnoreCase("sold")) {
            return SOLD;
        } else {
            return AVAILABLE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == AVAILABLE) {
            View view = inflater.inflate(R.layout.row_available, parent, false);
            SellHolderAvailable viewHolder = new SellHolderAvailable(view);
            return viewHolder;
        } else {
            View view = inflater.inflate(R.layout.row_sold, parent, false);
            SellHolderSold viewHolder = new SellHolderSold(view);
            return viewHolder;
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case AVAILABLE:
                SellHolderAvailable sellHolderAvailable = (SellHolderAvailable) holder;
                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                sellHolderAvailable.recyclerView.setLayoutManager(layoutManager);
                adapter = new InnerRecyclerViewAdapter(context, itemList.get(position).innerRowDatas);
                sellHolderAvailable.recyclerView.setAdapter(adapter);
                break;
            case SOLD:
                SellHolderSold sellHolderSold = (SellHolderSold) holder;

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

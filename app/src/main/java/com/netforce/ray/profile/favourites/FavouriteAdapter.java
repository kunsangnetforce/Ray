package com.netforce.ray.profile.favourites;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.netforce.ray.R;
import com.netforce.ray.home.product_detail.ProductDetailsActivity;
import com.netforce.ray.profile.favourites.offer_list.OfferAdapter;

import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class FavouriteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private static final int SOLD = 0;
    private static final int AVAILABLE = 1;
    private final LayoutInflater inflater;
    private List<FavouriteData> itemList;
    private Context context;
    private OfferAdapter adapter;

    public FavouriteAdapter(Context context, List<FavouriteData> itemList)
    {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
            return AVAILABLE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
            View view = inflater.inflate(R.layout.row_new_favourite, parent, false);
            FavouriteHolder viewHolder = new FavouriteHolder(view);
            return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {
                FavouriteHolder favouriteHolder = (FavouriteHolder) holder;
                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                favouriteHolder.recyclerView.setLayoutManager(layoutManager);
                adapter = new OfferAdapter(context, itemList.get(position).offerDatas);
                favouriteHolder.recyclerView.setAdapter(adapter);


        favouriteHolder.relativeimage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                Intent i = new Intent(context,ProductDetailsActivity.class);
                context.startActivity(i);


            }
        });

    }

    private void showMessage(String s)
    {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return itemList.size();
//        return itemList.size();
    }


}

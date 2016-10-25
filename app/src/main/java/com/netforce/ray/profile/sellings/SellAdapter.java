package com.netforce.ray.profile.sellings;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;
import com.netforce.ray.R;
import com.netforce.ray.home.offer_Adapter_Ouestion_Adapter.SellerProductActivity;
import com.netforce.ray.profile.sellings.offer_list.OfferAdapter;
import com.netforce.ray.sell.sellproductdetail.Sell_ProductDeatailAcrtivity;

import java.util.List;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class SellAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SOLD = 0;
    private static final int AVAILABLE = 1;
    private final LayoutInflater inflater;
    private List<SellData> itemList;
    private Context context;
    private OfferAdapter adapter;

    public SellAdapter(Context context, List<SellData> itemList) {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        /*if (itemList.get(position).status.equalsIgnoreCase("sold")) {
            return SOLD;
        } else {
            return AVAILABLE;
        }*/
        return position;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = inflater.inflate(R.layout.row_new_sold, parent, false);
        SellHolderSold viewHolder = new SellHolderSold(view);

        return viewHolder;
        /*if (viewType == AVAILABLE) {
            View view = inflater.inflate(R.layout.row_available, parent, false);
            SellHolderAvailable viewHolder = new SellHolderAvailable(view);
            return viewHolder;
        } else {
            View view = inflater.inflate(R.layout.row_new_sold, parent, false);
            SellHolderSold viewHolder = new SellHolderSold(view);
            return viewHolder;
        }
*/

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {

        SellHolderSold viewHolder = (SellHolderSold) holder;;

      /*  switch (holder.getItemViewType())
        {
            case AVAILABLE:
                SellHolderAvailable sellHolderAvailable = (SellHolderAvailable) holder;
                LinearLayoutManager layoutManager = new LinearLayoutManager(context);
                sellHolderAvailable.recyclerView.setLayoutManager(layoutManager);
                adapter = new OfferAdapter(context, itemList.get(position).offerDatas);
                sellHolderAvailable.recyclerView.setAdapter(adapter);
                break;
            case SOLD:
                SellHolderSold sellHolderSold = (SellHolderSold) holder;

                break;
        }*/
viewHolder.like_count.setText(itemList.get(position).like);
        viewHolder.price.setText(itemList.get(position).price);
        viewHolder.productname.setText(itemList.get(position).name);
        viewHolder.timeDuration.setText(itemList.get(position).created);
        Ion.with(viewHolder.relative_image).load(itemList.get(position).product_image);


                viewHolder.relative_image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(context, SellerProductActivity.class);
                        context.startActivity(i);
                    }
                });
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

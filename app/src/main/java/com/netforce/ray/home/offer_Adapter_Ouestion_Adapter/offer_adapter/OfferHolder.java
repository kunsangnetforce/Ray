package com.netforce.ray.home.offer_Adapter_Ouestion_Adapter.offer_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforce.ray.R;

/**
 * Created by John on 10/6/2016.
 */
public class OfferHolder extends RecyclerView.ViewHolder
{


    View view;
    TextView user_details;
    ImageView user_image;
    RelativeLayout relativelayout_offer;

    public OfferHolder(View itemView)
    {
        super(itemView);

        relativelayout_offer = (RelativeLayout) itemView.findViewById(R.id.relativelayout_offer);

        user_details = (TextView) itemView.findViewById(R.id.user_deatails);

        user_image = (ImageView) itemView.findViewById(R.id.product_image);

        view = itemView;
    }
}

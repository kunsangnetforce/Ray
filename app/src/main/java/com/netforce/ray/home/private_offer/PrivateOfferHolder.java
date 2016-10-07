package com.netforce.ray.home.private_offer;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netforce.ray.R;

/**
 * Created by John on 10/7/2016.
 */
public class PrivateOfferHolder extends RecyclerView.ViewHolder{

    View view;
    TextView offer_text;
    ImageView offer_icon;


    public PrivateOfferHolder(View itemView)
    {
        super(itemView);



        offer_text = (TextView) itemView.findViewById(R.id.offer_text);

        offer_icon = (ImageView) itemView.findViewById(R.id.offer_icon);

        view = itemView;
    }
}

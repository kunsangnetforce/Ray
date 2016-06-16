package com.netforce.ray.profile.favourites.offer_list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class OfferHolder extends RecyclerView.ViewHolder {

    public TextView textViewPos, textViewTeamName, textViewGP, textViewW, textViewD, textViewL, textViewGD, textViewPts;
    public ImageView imageViewTeamLogo;
    public View view;


    public OfferHolder(View itemView) {
        super(itemView);
        view=itemView;

    }

}
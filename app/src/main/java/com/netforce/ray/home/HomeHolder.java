package com.netforce.ray.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforce.ray.R;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class HomeHolder extends RecyclerView.ViewHolder {

    TextView textViewTitle;
    ImageView imageView, imageViewIcon;
    View view;
    MaterialRippleLayout materialRippleLayout;

    public HomeHolder(View itemView) {
        super(itemView);
        //implementing onClickListener
        textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);
        imageViewIcon = (ImageView) itemView.findViewById(R.id.icon);
        materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);
        view = itemView;
    }
}
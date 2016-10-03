package com.netforce.ray.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforce.ray.R;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class HomeHolder extends RecyclerView.ViewHolder
{

    View view;
    TextView product_name,product_price;
    ImageView product_image;
    MaterialRippleLayout materialRippleLayout;

    public HomeHolder(View itemView)
    {
        super(itemView);

        materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);

        product_name = (TextView) itemView.findViewById(R.id.product_name_txt);

        product_price = (TextView) itemView.findViewById(R.id.product_price);

        product_image = (ImageView) itemView.findViewById(R.id.product_image);

        view = itemView;
    }
}
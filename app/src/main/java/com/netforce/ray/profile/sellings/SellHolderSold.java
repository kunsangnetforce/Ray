package com.netforce.ray.profile.sellings;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforce.ray.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class SellHolderSold extends RecyclerView.ViewHolder {

    TextView textViewContent, textViewDate;
    ImageView imageViewProduct;
    CircleImageView imageViewProfile;
    View view;
    MaterialRippleLayout materialRippleLayout;

    public SellHolderSold(View itemView) {
        super(itemView);
        //implementing onClickListener
        /*textViewContent = (TextView) itemView.findViewById(R.id.textViewContent);
        textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);
        imageViewProduct = (ImageView) itemView.findViewById(R.id.imageView);
        imageViewProfile = (CircleImageView) itemView.findViewById(R.id.profile_image);
        materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);*/
        view = itemView;
    }
}
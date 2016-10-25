package com.netforce.ray.profile.sellings;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforce.ray.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class SellHolderSold extends RecyclerView.ViewHolder {

    TextView textViewContent, textViewDate;

    CircleImageView imageViewProfile;
    View view;
    MaterialRippleLayout materialRippleLayout;
    ImageView relative_image;
TextView productname,like_count,price,timeDuration;

    public SellHolderSold(View itemView) {
        super(itemView);
        //implementing onClickListener
        /*textViewContent = (TextView) itemView.findViewById(R.id.textViewContent);
        textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);
        imageViewProduct = (ImageView) itemView.findViewById(R.id.imageView);
        imageViewProfile = (CircleImageView) itemView.findViewById(R.id.profile_image);
        materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);*/


        relative_image = (ImageView) itemView.findViewById(R.id.img_product);
        productname=(TextView)itemView.findViewById(R.id.product_name);
        like_count=(TextView)itemView.findViewById(R.id.tv_like_count);
        price=(TextView)itemView.findViewById(R.id.tv_price);
        timeDuration=(TextView)itemView.findViewById(R.id.tv_time_duration);

        view = itemView;
    }
}
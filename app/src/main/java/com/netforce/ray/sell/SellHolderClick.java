package com.netforce.ray.sell;

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
public class SellHolderClick extends RecyclerView.ViewHolder {


    ImageView imageView;
    public SellHolderClick(View itemView) {
        super(itemView);
        //implementing onClickListener
       imageView= (ImageView) itemView.findViewById(R.id.imageView);
    }
}
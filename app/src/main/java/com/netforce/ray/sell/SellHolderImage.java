package com.netforce.ray.sell;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.netforce.ray.R;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class SellHolderImage extends RecyclerView.ViewHolder {
    ImageView imageView, imageViewClose;

    public SellHolderImage(View itemView) {
        super(itemView);
        //implementing onClickListener
        imageView= (ImageView) itemView.findViewById(R.id.imageView);
        imageViewClose = (ImageView) itemView.findViewById(R.id.imageViewClose);
    }
}
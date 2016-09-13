package com.netforce.ray.dashboard.navigation.wanted.publishitems_fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforce.ray.R;

/**
 * Created by John on 9/7/2016.
 */
public class PublishHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        ImageView imageViewIcon;
        RelativeLayout relativelayout1;
        View view;
        MaterialRippleLayout materialRippleLayout;

       public PublishHolder(View itemView) {
        super(itemView);
        //implementing onClickListener

        relativelayout1 = (RelativeLayout) itemView.findViewById(R.id.relativelayout1);

        materialRippleLayout = (MaterialRippleLayout) itemView.findViewById(R.id.ripple);
        view = itemView;
        }
        }

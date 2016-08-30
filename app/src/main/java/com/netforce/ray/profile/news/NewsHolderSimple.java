package com.netforce.ray.profile.news;

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
public class NewsHolderSimple extends RecyclerView.ViewHolder {

    TextView textViewContent, textViewDate;
    CircleImageView circleImageViewProPic;
    View view;
    MaterialRippleLayout materialRippleLayout;

    public NewsHolderSimple(View itemView) {
        super(itemView);
        //implementing onClickListener

        view = itemView;
    }
}
package com.netforce.ray.profile.sellings;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforce.ray.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class SellHolderAvailable extends RecyclerView.ViewHolder {

    TextView textViewContent, textViewDate;
    CircleImageView circleImageViewProPic;
    View view;
    public RecyclerView recyclerView;

    public SellHolderAvailable(View itemView) {
        super(itemView);
        //implementing onClickListener
        textViewContent = (TextView) itemView.findViewById(R.id.textViewContent);
        textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);
        circleImageViewProPic = (CircleImageView) itemView.findViewById(R.id.profile_image);
        recyclerView= (RecyclerView) itemView.findViewById(R.id.recycler);
        view = itemView;
    }
}
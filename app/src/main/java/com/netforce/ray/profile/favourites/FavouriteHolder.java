package com.netforce.ray.profile.favourites;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netforce.ray.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Gowtham Chandrasekar on 31-07-2015.
 */
public class FavouriteHolder extends RecyclerView.ViewHolder {

    TextView textViewContent, textViewDate;
    CircleImageView circleImageViewProPic;
    View view;
    public RecyclerView recyclerView;
    RelativeLayout relativeimage;

    public FavouriteHolder(View itemView)
    {
        super(itemView);
        //implementing onClickListener
        textViewContent = (TextView) itemView.findViewById(R.id.textViewContent);
        textViewDate = (TextView) itemView.findViewById(R.id.textViewDate);
        circleImageViewProPic = (CircleImageView) itemView.findViewById(R.id.profile_image);
        recyclerView= (RecyclerView) itemView.findViewById(R.id.recycler);

        relativeimage = (RelativeLayout) itemView.findViewById(R.id.relativeimage);

        view = itemView;
    }
}
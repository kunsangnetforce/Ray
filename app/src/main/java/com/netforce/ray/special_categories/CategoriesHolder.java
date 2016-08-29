package com.netforce.ray.special_categories;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforce.ray.R;

/**
 * Created by John on 8/29/2016.
 */
public class CategoriesHolder extends RecyclerView.ViewHolder {


    TextView textViewTitle, textViewCategory, textViewPros;

    MaterialRippleLayout materialRippleLayout;
    View view;


    public CategoriesHolder(View itemView) {
        super(itemView);
        //implementing onClickListener

        textViewTitle = (TextView) itemView.findViewById(R.id.categories_item);


    }
}

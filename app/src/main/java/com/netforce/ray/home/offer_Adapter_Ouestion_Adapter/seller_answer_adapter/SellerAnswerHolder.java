package com.netforce.ray.home.offer_Adapter_Ouestion_Adapter.seller_answer_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netforce.ray.R;

/**
 * Created by John on 10/7/2016.
 */
public class SellerAnswerHolder  extends RecyclerView.ViewHolder
{


    View view;
    TextView message_txt;
    ImageView seller_image;
    RelativeLayout relativeQuestion;

    public SellerAnswerHolder(View itemView)
    {
        super(itemView);

        relativeQuestion = (RelativeLayout) itemView.findViewById(R.id.relativeLayoutQuestion);

        message_txt = (TextView) itemView.findViewById(R.id.message_txt);

        seller_image = (ImageView) itemView.findViewById(R.id.seller_image);

        view = itemView;
    }

}

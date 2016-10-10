package com.netforce.ray.home.offer_Adapter_Ouestion_Adapter.seller_question_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netforce.ray.R;
import com.netforce.ray.home.offer_Adapter_Ouestion_Adapter.seller_answer_adapter.SellerAnswerData;

import java.util.ArrayList;

/**
 * Created by John on 10/7/2016.
 */
public class SellerQuestionData
{

    String title;
    ArrayList<SellerAnswerData>  sellerAnswerDatas;

    public SellerQuestionData(ArrayList<SellerAnswerData> sellerAnswerData, String status)
    {
        this.sellerAnswerDatas = sellerAnswerData;
        this.title=status;
    }
}


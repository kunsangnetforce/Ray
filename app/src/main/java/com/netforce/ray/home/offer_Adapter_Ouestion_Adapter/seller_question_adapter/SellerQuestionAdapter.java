package com.netforce.ray.home.offer_Adapter_Ouestion_Adapter.seller_question_adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.netforce.ray.R;
import com.netforce.ray.home.offer_Adapter_Ouestion_Adapter.seller_answer_adapter.SellerAnswerAdapter;
import com.netforce.ray.home.offer_Adapter_Ouestion_Adapter.seller_answer_adapter.SellerAnswerData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by John on 10/7/2016.
 */
public class SellerQuestionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<SellerQuestionData> itemList;
    public ArrayList<SellerAnswerData> sellerAnswerList;
    private Context context;
    SellerAnswerAdapter sellerAnswerAdapter;
    public RecyclerView recyclerView;

    public SellerQuestionAdapter(Context context, List<SellerQuestionData> itemList)
    {

        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.row_seller__product_question, parent, false);
        SellerQuestionHolder viewHolder = new SellerQuestionHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {
        SellerQuestionHolder homeHolder = (SellerQuestionHolder) holder;

        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        homeHolder.seller_answer_recyclerview.setLayoutManager(layoutManager);
        sellerAnswerAdapter = new SellerAnswerAdapter(context, itemList.get(position).sellerAnswerDatas);
        homeHolder.seller_answer_recyclerview.setAdapter(sellerAnswerAdapter);


        homeHolder.relativeQuestion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


            }
        });

    }



    private void showMessage(String s) {

        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount()
    {
        return itemList.size();
        //return itemList.size();
    }

    public String getCurrentTimeStamp() {
        return new SimpleDateFormat("dd MMM yyyy HH:mm").format(new Date());
    }
}



package com.netforce.ray.home.offer_Adapter_Ouestion_Adapter.seller_answer_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.netforce.ray.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by John on 10/7/2016.
 */
public class SellerAnswerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<SellerAnswerData> itemList;
    private Context context;

    


    public SellerAnswerAdapter(Context context, List<SellerAnswerData> itemList)
    {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = inflater.inflate(R.layout.row_seller_answer, parent, false);
        SellerAnswerHolder viewHolder = new SellerAnswerHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {
        SellerAnswerHolder homeHolder = (SellerAnswerHolder) holder;

        homeHolder.relativeQuestion.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {



            }
        });

    }

    private void showMessage(String s)
    {

        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount()
    {
        return itemList.size();
        //return itemList.size();
    }

    public String getCurrentTimeStamp()
    {
        return new SimpleDateFormat("dd MMM yyyy HH:mm").format(new Date());
    }
}




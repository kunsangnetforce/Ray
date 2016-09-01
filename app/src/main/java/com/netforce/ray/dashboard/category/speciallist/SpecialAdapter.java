package com.netforce.ray.dashboard.category.speciallist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.netforce.ray.R;
import com.netforce.ray.profile.news.NewsData;
import com.netforce.ray.profile.news.NewsHolderSimple;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by John on 9/1/2016.
 */
public class SpecialAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<SpecialData> itemList;
    private Context context;
    SpecialHolderSimple viewHolder;


    public SpecialAdapter(Context context, List<SpecialData> itemList)
    {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View view = inflater.inflate(R.layout.row_special, parent, false);
         viewHolder = new SpecialHolderSimple(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {

        viewHolder.textViewTitle.setText(itemList.get(position).title);
    }

    private void showMessage(String s)
    {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount()
    {
        return itemList.size();
        // return itemList.size();
    }


    public class SpecialHolderSimple extends RecyclerView.ViewHolder
    {

        TextView textViewContent, textViewTitle;
        CircleImageView circleImageViewProPic;
        ImageView  imageViewProduct;

        View view;
        MaterialRippleLayout materialRippleLayout;

        public SpecialHolderSimple(View itemView)
        {
            super(itemView);
            //implementing onClickListener

            view = itemView;

            textViewTitle = (TextView) itemView.findViewById(R.id.text1);
            imageViewProduct = (ImageView) itemView.findViewById(R.id.image1);
        }
    }


}

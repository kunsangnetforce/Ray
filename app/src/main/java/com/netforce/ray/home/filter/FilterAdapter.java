package com.netforce.ray.home.filter;

/**
 * Created by John on 10/4/2016.
 */
import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.netforce.ray.R;
import com.netforce.ray.home.HomeFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class FilterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<FilterData> itemList;
    private Context context;

    public FilterAdapter(Context context, List<FilterData> itemList)
    {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View view = inflater.inflate(R.layout.row_filter, parent, false);
        FilterHolder viewHolder = new FilterHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position)
    {
        FilterHolder filterHolder = (FilterHolder) holder;

        filterHolder.product_name.setText(itemList.get(position).product_name);

        filterHolder.relativelayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                HomeFragment.filterData.remove(position);
                notifyDataSetChanged();

            }
        });

    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        return itemList.size();
//        return itemList.size();
    }

    public String getCurrentTimeStamp() {
        return new SimpleDateFormat("dd MMM yyyy HH:mm").format(new Date());
    }



    class FilterHolder extends RecyclerView.ViewHolder
        {
            View view;
            TextView product_name;
            ImageView product_image;
            RelativeLayout relativelayout1 ;

            public FilterHolder(View itemView)
            {
                super(itemView);
                product_name = (TextView) itemView.findViewById(R.id.product_name);

                relativelayout1 = (RelativeLayout) itemView.findViewById(R.id.relativelayout1);

                view = itemView;
            }
        }
}

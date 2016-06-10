package com.netforce.ray.dashboard.navigation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.netforce.ray.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by ashok on 3/11/15.
 */
public class RecyclerAdapterDrawer extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ALERT_DRAWER = 4;
    public static int selected_item = 0;
    private final LayoutInflater inflater;
    List<RowDataDrawer> data = Collections.emptyList();
    Context context;
    private clickListner click = null;
    private final int NORMAL_DRAWER = 2;
    private Intent intent;

    RecyclerAdapterDrawer(Context context, List<RowDataDrawer> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == NORMAL_DRAWER) {
            View view = inflater.inflate(R.layout.row_navigation_drawer, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        } else {
            View view = inflater.inflate(R.layout.row_navigation_drawer1, parent, false);
            MyViewHolder1 viewHolder1 = new MyViewHolder1(view);
            return viewHolder1;

        }
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return ALERT_DRAWER;
        } else {
            return NORMAL_DRAWER;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {

            case NORMAL_DRAWER:
                MyViewHolder myViewHolder = (MyViewHolder) holder;
                myViewHolder.textView.setText(data.get(position).text);
                myViewHolder.imageView.setImageResource(data.get(position).id);
                break;
            case ALERT_DRAWER:
                MyViewHolder1 myViewHolder1 = (MyViewHolder1) holder;
                myViewHolder1.info.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage("show pop up");
                    }
                });
                myViewHolder1.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showMessage("show added message");
                    }
                });
                break;

        }
    }

    private void showMessage(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        Log.i("TAG2 count", data.size() + "");
        return data.size();
    }

    public void setClickListner(clickListner click) {
        this.click = click;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;

        public MyViewHolder(final View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView1);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.header);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.itemClicked(itemView, getAdapterPosition());

                }
            });
        }

    }

    class MyViewHolder1 extends RecyclerView.ViewHolder {
        ImageView info, add;
        EditText editTextSearch;

        public MyViewHolder1(final View itemView) {
            super(itemView);
            info = (ImageView) itemView.findViewById(R.id.info);
            add = (ImageView) itemView.findViewById(R.id.addAlert);
            editTextSearch = (EditText) itemView.findViewById(R.id.editTextSearch);

        }

    }


    public interface clickListner {
        void itemClicked(View view, int position);
    }


}

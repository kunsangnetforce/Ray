package com.netforce.ray.special_categories;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.netforce.ray.R;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 8/29/2016.
 */
public class CategoriesSortAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<Categories_Data> itemList;
    private Context context;
    ArrayList<Boolean> booleanGames = new ArrayList<>();

    CategoriesHolder viewHolder;

    public CategoriesSortAdapter(Context context, List<Categories_Data> itemList)
    {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View view = inflater.inflate(R.layout.row_categories, parent, false);
        viewHolder = new CategoriesHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
    {

        viewHolder.textViewTitle.setText(itemList.get(position).title);

        viewHolder.categories_item.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                viewHolder.categories_item.setFocusableInTouchMode(false);
                viewHolder.categories_item.setFocusable(false);
            }
        });

        viewHolder.categories_layout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {



            }
        });

    }

    private void showMessage(String s)
    {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
      //  return 3;
       return itemList.size();
    }


}
package com.netforce.ray.special_categories;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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
        for(int i=0;i<itemList.size();i++)
        {
            booleanGames.add(false);
        }
        viewHolder = new CategoriesHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position)
    {

        final CategoriesHolder categoriesHolder= (CategoriesHolder) holder;

        categoriesHolder.textViewTitle.setText(itemList.get(position).title);

        categoriesHolder.categories_item.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {

                Toast.makeText(context,"Hi ",Toast.LENGTH_SHORT).show();

                    if(booleanGames.get(position))
                    {

                        categoriesHolder.categories_item.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_circle_outline, 0, 0, 0);
                        categoriesHolder.categories_item.setTextColor(ContextCompat.getColor(context, R.color.black));
                        booleanGames.set(position,!booleanGames.get(position));

                    }
                    else
                    {
                        categoriesHolder.categories_item.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_circle_filled, 0, 0, 0);
                        categoriesHolder.categories_item.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                        booleanGames.set(position, !booleanGames.get(position));

                    }
            notifyDataSetChanged();


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
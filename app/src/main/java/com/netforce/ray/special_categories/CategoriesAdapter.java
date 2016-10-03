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
public class CategoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int SIMPLE_TYPE = 0;
    private static final int IMAGE_TYPE = 1;
    private final LayoutInflater inflater;
    private List<Categories_Data> itemList;
    private Context context;
    ArrayList<Boolean> booleanGames = new ArrayList<>();

    CategoriesHolder viewHolder;

    public CategoriesAdapter(Context context, List<Categories_Data> itemList) {
        this.itemList = itemList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_categories, parent, false);

        for (int i = 0; i < itemList.size(); i++)
        {
            if (i== 0){
                booleanGames.add(true);
            }
            else {
                booleanGames.add(false);
            }
        }
        viewHolder = new CategoriesHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final CategoriesHolder categoriesHolder = (CategoriesHolder) holder;
        categoriesHolder.textViewTitle.setText(itemList.get(position).title);
        if (booleanGames.get(position)) {
            //booleanGames.set(position,!booleanGames.get(position));
            categoriesHolder.categories_item.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_circle_filled, 0, 0, 0);
            categoriesHolder.categories_item.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
        } else {

            categoriesHolder.categories_item.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_circle_outline, 0, 0, 0);
            categoriesHolder.categories_item.setTextColor(ContextCompat.getColor(context, R.color.black));
        }

        categoriesHolder.categories_item.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


                System.out.println("position=============" + position);

                if (position == 0) {
                    if (!booleanGames.get(position)) {
                        for (int i = 0; i < itemList.size(); i++) {
                            booleanGames.set(i, false);
                        }
                        booleanGames.set(0, true);
                    }

                } else {
                    booleanGames.set(0, false);
                    if (booleanGames.get(position)) {
                        categoriesHolder.categories_item.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_circle_outline, 0, 0, 0);
                        categoriesHolder.categories_item.setTextColor(ContextCompat.getColor(context, R.color.black));
                        booleanGames.set(position, !booleanGames.get(position));

                    } else {
                        categoriesHolder.categories_item.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_circle_filled, 0, 0, 0);
                        categoriesHolder.categories_item.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                        booleanGames.set(position, !booleanGames.get(position));

                    }

                }
                notifyDataSetChanged();
            }


        });


    }

    private void showMessage(String s) {

        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


    @Override
    public int getItemCount() {
        //  return 11;
        return itemList.size();
    }


}
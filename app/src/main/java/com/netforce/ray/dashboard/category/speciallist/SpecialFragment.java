package com.netforce.ray.dashboard.category.speciallist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforce.ray.R;
import com.netforce.ray.profile.news.NewsAdapter;
import com.netforce.ray.profile.news.NewsData;

import java.util.ArrayList;

/**
 * Created by John on 9/1/2016.
 */
public class SpecialFragment extends Fragment
{


    private Context context;
    private RecyclerView recyclerView;
    private SpecialAdapter adapter;
    String category_id;
    ArrayList show_visible_fragment=new ArrayList();
    private ArrayList<SpecialData> newsDatas = new ArrayList<>();
    private LinearLayoutManager layoutManager;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_special, container, false);



        context = getActivity();
        setupRecyclerView(view);


        return view;
    }

    private void setupRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        adapter = new SpecialAdapter(context, newsDatas);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void setupData(ArrayList show_visible_fragment ) {


        try {
            String url="http://netforce.biz/seeksell/products/product_list/cat_id/2.json";
            Log.e("url",url);
            Ion.with(getActivity())
                    .load(url)
                    .asString().setCallback(new FutureCallback<String>() {
                @Override
                public void onCompleted(Exception e, String result) {
                    try {


                        if (result != null) {
                            Log.e("result", result.toString());


                        } else {
                            Log.e("result null", "result is null");
                        }
                    } catch (Exception e1) {
                        Log.e("Exception", e1.toString());
                    }


                }
            });








            newsDatas.clear();

        newsDatas.add(new SpecialData("", "New in Area"));
        newsDatas.add(new SpecialData("", "Giveaways"));
        newsDatas.add(new SpecialData("", "Friends & Following"));
        newsDatas.add(new SpecialData("", "Young Designer"));
        newsDatas.add(new SpecialData("", "Summer & Fashion"));
        newsDatas.add(new SpecialData("", "Summer Festival Season"));
        newsDatas.add(new SpecialData("", "Seek & Sell Selection"));
        } catch (Exception ex) {
        }


    }

    @Override
    public void setUserVisibleHint(boolean visible)
    {
        super.setUserVisibleHint(visible);
        if (visible)
        {Bundle bundle = this.getArguments();
            if (bundle != null) {

                category_id=bundle.getString("Category_id");
                show_visible_fragment.add(category_id);
                setupData(show_visible_fragment);
            }
            else{
                Log.e("bundle null","bundle null");
            }

            //Only manually call onResume if fragment is already visible
            //Otherwise allow natural fragment lifecycle to call onResume

        }
    }
}

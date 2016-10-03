package com.netforce.ray.dashboard.category.electronics;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.github.clans.fab.FloatingActionButton;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforce.ray.R;
import com.netforce.ray.home.HomeAdapter;
import com.netforce.ray.home.HomeData;
import com.netforce.ray.search.SearchActivity;
import com.netforce.ray.sell.SellActivity;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;

import it.carlom.stikkyheader.core.StikkyHeaderBuilder;
import it.carlom.stikkyheader.core.animator.AnimatorBuilder;
import it.carlom.stikkyheader.core.animator.HeaderStikkyAnimator;


public class ElectronicsFragment extends Fragment implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener
{

    Context context;
    private RecyclerView recyclerView;
    ElectronicsAdapter adapter;
    ArrayList<ElectronicsData> electronicsDatas = new ArrayList<>();
    StaggeredGridLayoutManager layoutManager;
    SwipyRefreshLayout mSwipyRefreshLayout;
    FloatingActionButton floatingActionButtonSell;
    SwipeRefreshLayout swiperefreshlayout;
    StikkyHeaderBuilder stikkyHeader;
    LinearLayout linearLayoutSearch;


    public ElectronicsFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_electronics, container, false);

        context = getActivity();
        swiperefreshlayout = (SwipeRefreshLayout) view.findViewById(R.id.swipyrefreshlayout);


        setupLayout(view);

        setupData();

        swiperefreshlayout.setRefreshing(true);

        System.out.println("This is call==========");


        return view;
    }

    private void setupLayout(View  view )
    {

        linearLayoutSearch = (LinearLayout)view.findViewById(R.id.linearlayoutemail);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        floatingActionButtonSell = (FloatingActionButton) view.findViewById(R.id.fabSell);


        floatingActionButtonSell.setOnClickListener(this);

        linearLayoutSearch.setOnClickListener(this);

        swiperefreshlayout.setOnRefreshListener(this);

    }

    private void setupData()
    {


        load();
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        adapter = new ElectronicsAdapter(context, electronicsDatas);

        recyclerView.setAdapter(adapter);

        recyclerView.setNestedScrollingEnabled(false);



    }

    private void refreshItem()
    {
        try
        {
            Thread.sleep(2000);
            mSwipyRefreshLayout.setRefreshing(false);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }



    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.fabSell:
                Intent intent = new Intent(context, SellActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
                break;

            case R.id.linearLayoutSearch:
                Intent search = new Intent(context, SearchActivity.class);
                startActivity(search);
                getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
                break;

        }
    }


    void load()
    {

        electronicsDatas.clear();
        //swipyrefreshlayout.setRefreshing(true);

        Ion.with(this)
                .load("http://odishatv.in/otv-app/write/nation.php?counter=10")

                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {

                        if (result != null)
                        {

                            for (int i = 0; i < result.size(); i++)
                            {
                                JsonObject jsonObject = (JsonObject) result.get(i);


                                String name = jsonObject.get("post_title").toString();

                                electronicsDatas.add(new ElectronicsData("url", name, "price"));

                                System.out.println("imageurl ======================"  + name);

                            }
                            adapter.notifyDataSetChanged();
                            swiperefreshlayout.setRefreshing(false);

                        } else {
                            Log.e("error", e.toString());
                        }
                    }
                });


      /*  Ion.with(this)
                .load("http://www.androidbegin.com/tutorial/jsonparsetutorial.txt")

                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        JsonArray jsonArray = (JsonArray) result.get("worldpopulation");

                        System.out.println("json Array=======" + jsonArray.toString());

                        if (result != null)
                        {

                            for (int i = 0; i < jsonArray.size(); i++)
                            {
                                JsonObject jsonObject = (JsonObject) jsonArray.get(i);

                                String image_url = jsonObject.get("flag").toString();

                                String name = jsonObject.get("population").toString();

                                electronicsDatas.add(new ElectronicsData(image_url, name, "price"));

                                System.out.println("imageurl ======================" + image_url + name);

                            }
                            adapter.notifyDataSetChanged();
                            swiperefreshlayout.setRefreshing(false);

                        } else {
                            Log.e("error", e.toString());
                        }
                    }
                });*/
    }


    public void onRefresh()
    {

        System.out.println("hdgadndjd==========dnb");
        load();

    }


}


package com.netforce.ray.dashboard.navigation.wanted.publishitems_fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.github.clans.fab.FloatingActionButton;
import com.netforce.ray.R;
import com.netforce.ray.search.SearchActivity;
import com.netforce.ray.sell.SellActivity;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;

import java.util.ArrayList;


public class WantedPublishFragment extends Fragment  implements View.OnClickListener
{

        Context context;
        private RecyclerView recyclerView;
        WantedPublishAdapter adapter;
        ArrayList<WantedPublishData> publishDatas = new ArrayList<>();
        StaggeredGridLayoutManager layoutManager;
        SwipyRefreshLayout mSwipyRefreshLayout;
        FloatingActionButton floatingActionButtonSell;


        RelativeLayout relativlayoutSearch;


          public WantedPublishFragment() {
        // Required empty public constructor
        }


         @Override
         public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
        {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wanted_publish, container, false);
        context = getActivity();
        setupRecyclerView(view);



        return view;
        }

        private void setupRecyclerView(View view) {


        relativlayoutSearch = (RelativeLayout)view.findViewById(R.id.relativeLayoutSearch);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        floatingActionButtonSell = (FloatingActionButton) view.findViewById(R.id.fabSell);
        floatingActionButtonSell.setOnClickListener(this);
        adapter = new WantedPublishAdapter(context, publishDatas);
        setupData();

        relativlayoutSearch.setOnClickListener(this);


        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        recyclerView.setNestedScrollingEnabled(false);

        }

        private void refreshItem() {
        try {
        Thread.sleep(2000);
        mSwipyRefreshLayout.setRefreshing(false);
        } catch (InterruptedException e) {
        e.printStackTrace();
        }
        }

       private void setupData() {
        try {
        publishDatas.clear();
        } catch (Exception ex) {

        }
        publishDatas.add(new WantedPublishData("imageurl", "title", "price"));
        publishDatas.add(new WantedPublishData("imageurl", "title", "price"));
        publishDatas.add(new WantedPublishData("imageurl", "title", "price"));
        publishDatas.add(new WantedPublishData("imageurl", "title", "price"));
        publishDatas.add(new WantedPublishData("imageurl", "title", "price"));
        publishDatas.add(new WantedPublishData("imageurl", "title", "price"));
        publishDatas.add(new WantedPublishData("imageurl", "title", "price"));
        publishDatas.add(new WantedPublishData("imageurl", "title", "price"));
        publishDatas.add(new WantedPublishData("imageurl", "title", "price"));
        publishDatas.add(new WantedPublishData("imageurl", "title", "price"));
        publishDatas.add(new WantedPublishData("imageurl", "title", "price"));
        publishDatas.add(new WantedPublishData("imageurl", "title", "price"));
        publishDatas.add(new WantedPublishData("imageurl", "title", "price"));
        publishDatas.add(new WantedPublishData("imageurl", "title", "price"));

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

        case R.id.relativeLayoutSearch:
        Intent search = new Intent(context, SearchActivity.class);
        startActivity(search);
        getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
        break;

        }
        }



        }


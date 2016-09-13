package com.netforce.ray.dashboard.category.electronics;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.github.clans.fab.FloatingActionButton;
import com.netforce.ray.R;
import com.netforce.ray.home.HomeAdapter;
import com.netforce.ray.home.HomeData;
import com.netforce.ray.search.SearchActivity;
import com.netforce.ray.sell.SellActivity;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;

import java.util.ArrayList;

import it.carlom.stikkyheader.core.StikkyHeaderBuilder;
import it.carlom.stikkyheader.core.animator.AnimatorBuilder;
import it.carlom.stikkyheader.core.animator.HeaderStikkyAnimator;


public class ElectronicsFragment extends Fragment implements View.OnClickListener {

    Context context;
    private RecyclerView recyclerView;
    ElectronicsAdapter adapter;
    ArrayList<ElectronicsData> electronicsDatas = new ArrayList<>();
    StaggeredGridLayoutManager layoutManager;
    SwipyRefreshLayout mSwipyRefreshLayout;
    FloatingActionButton floatingActionButtonSell;

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
        setupRecyclerView(view);



        return view;
    }

    private void setupRecyclerView(View view) {


        linearLayoutSearch = (LinearLayout)view.findViewById(R.id.linearlayoutemail);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        floatingActionButtonSell = (FloatingActionButton) view.findViewById(R.id.fabSell);
        floatingActionButtonSell.setOnClickListener(this);
        adapter = new ElectronicsAdapter(context, electronicsDatas);
        setupData();

        linearLayoutSearch.setOnClickListener(this);


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
            electronicsDatas.clear();
        } catch (Exception ex) {

        }
        electronicsDatas.add(new ElectronicsData("imageurl", "title", "price"));
        electronicsDatas.add(new ElectronicsData("imageurl", "title", "price"));
        electronicsDatas.add(new ElectronicsData("imageurl", "title", "price"));
        electronicsDatas.add(new ElectronicsData("imageurl", "title", "price"));
        electronicsDatas.add(new ElectronicsData("imageurl", "title", "price"));
        electronicsDatas.add(new ElectronicsData("imageurl", "title", "price"));
        electronicsDatas.add(new ElectronicsData("imageurl", "title", "price"));
        electronicsDatas.add(new ElectronicsData("imageurl", "title", "price"));
        electronicsDatas.add(new ElectronicsData("imageurl", "title", "price"));
        electronicsDatas.add(new ElectronicsData("imageurl", "title", "price"));
        electronicsDatas.add(new ElectronicsData("imageurl", "title", "price"));
        electronicsDatas.add(new ElectronicsData("imageurl", "title", "price"));
        electronicsDatas.add(new ElectronicsData("imageurl", "title", "price"));
        electronicsDatas.add(new ElectronicsData("imageurl", "title", "price"));


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


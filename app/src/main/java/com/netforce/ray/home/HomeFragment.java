package com.netforce.ray.home;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforce.ray.R;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    Context context;
    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private ArrayList<HomeData> homeDatas = new ArrayList<>();
    private StaggeredGridLayoutManager layoutManager;
    private SwipyRefreshLayout mSwipyRefreshLayout;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        context = getActivity();
        setupRecyclerView(view);
        return view;
    }

    private void setupRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        adapter = new HomeAdapter(context, homeDatas);
        setupData();
        mSwipyRefreshLayout = (SwipyRefreshLayout) view.findViewById(R.id.swipyrefreshlayout);
        mSwipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
              refreshItem();
            }
        });

        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

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
            homeDatas.clear();
        } catch (Exception ex) {

        }
        homeDatas.add(new HomeData("imageurl", "title", "price"));
        homeDatas.add(new HomeData("imageurl", "title", "price"));
        homeDatas.add(new HomeData("imageurl", "title", "price"));
        homeDatas.add(new HomeData("imageurl", "title", "price"));
        homeDatas.add(new HomeData("imageurl", "title", "price"));
        homeDatas.add(new HomeData("imageurl", "title", "price"));
        homeDatas.add(new HomeData("imageurl", "title", "price"));
        homeDatas.add(new HomeData("imageurl", "title", "price"));
        homeDatas.add(new HomeData("imageurl", "title", "price"));
        homeDatas.add(new HomeData("imageurl", "title", "price"));
        homeDatas.add(new HomeData("imageurl", "title", "price"));
        homeDatas.add(new HomeData("imageurl", "title", "price"));
        homeDatas.add(new HomeData("imageurl", "title", "price"));
        homeDatas.add(new HomeData("imageurl", "title", "price"));


    }

}

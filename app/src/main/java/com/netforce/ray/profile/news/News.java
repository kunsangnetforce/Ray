package com.netforce.ray.profile.news;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforce.ray.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class News extends Fragment {


    private Context context;
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private ArrayList<NewsData> newsDatas = new ArrayList<>();
    private LinearLayoutManager layoutManager;

    public News() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        context = getActivity();
        setupRecyclerView(view);
        return view;
    }

    private void setupRecyclerView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        setupData();
        adapter = new NewsAdapter(context, newsDatas);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void setupData() {
        try {
            newsDatas.clear();
        } catch (Exception ex) {
        }
        newsDatas.add(new NewsData("", "", "", "simple"));
        newsDatas.add(new NewsData("", "", "", "simple"));
        newsDatas.add(new NewsData("", "", "", "image"));
        newsDatas.add(new NewsData("", "", "", "simple"));
        newsDatas.add(new NewsData("", "", "", "image"));
        newsDatas.add(new NewsData("", "", "", "image"));
        newsDatas.add(new NewsData("", "", "", "simple"));
        newsDatas.add(new NewsData("", "", "", "simple"));
        newsDatas.add(new NewsData("", "", "", "simple"));
        newsDatas.add(new NewsData("", "", "", "image"));
        newsDatas.add(new NewsData("", "", "", "simple"));
        newsDatas.add(new NewsData("", "", "", "image"));
        newsDatas.add(new NewsData("", "", "", "image"));
        newsDatas.add(new NewsData("", "", "", "image"));

    }

}

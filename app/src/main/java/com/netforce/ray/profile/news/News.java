package com.netforce.ray.profile.news;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforce.ray.R;
import com.netforce.ray.home.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class News extends Fragment {


    private Context context;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private ArrayList<RowData> rowDatas = new ArrayList<>();
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
        adapter = new RecyclerViewAdapter(context, rowDatas);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void setupData() {
        try {
            rowDatas.clear();
        } catch (Exception ex) {
        }
        rowDatas.add(new RowData("", "", "", "simple"));
        rowDatas.add(new RowData("", "", "", "simple"));
        rowDatas.add(new RowData("", "", "", "image"));
        rowDatas.add(new RowData("", "", "", "simple"));
        rowDatas.add(new RowData("", "", "", "image"));
        rowDatas.add(new RowData("", "", "", "image"));
        rowDatas.add(new RowData("", "", "", "simple"));
        rowDatas.add(new RowData("", "", "", "simple"));
        rowDatas.add(new RowData("", "", "", "simple"));
        rowDatas.add(new RowData("", "", "", "image"));
        rowDatas.add(new RowData("", "", "", "simple"));
        rowDatas.add(new RowData("", "", "", "image"));
        rowDatas.add(new RowData("", "", "", "image"));
        rowDatas.add(new RowData("", "", "", "image"));

    }

}

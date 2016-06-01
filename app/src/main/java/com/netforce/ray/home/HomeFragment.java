package com.netforce.ray.home;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforce.ray.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    Context context;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private ArrayList<RowData> rowDatas = new ArrayList<>();
    private GridLayoutManager layoutManager;

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
        adapter = new RecyclerViewAdapter(context, rowDatas);
        setupData();
        layoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void setupData() {
        try {
            rowDatas.clear();
        } catch (Exception ex) {

        }
        rowDatas.add(new RowData("imageurl", "title", "price"));
        rowDatas.add(new RowData("imageurl", "title", "price"));
        rowDatas.add(new RowData("imageurl", "title", "price"));
        rowDatas.add(new RowData("imageurl", "title", "price"));
        rowDatas.add(new RowData("imageurl", "title", "price"));
        rowDatas.add(new RowData("imageurl", "title", "price"));
        rowDatas.add(new RowData("imageurl", "title", "price"));
        rowDatas.add(new RowData("imageurl", "title", "price"));
        rowDatas.add(new RowData("imageurl", "title", "price"));
        rowDatas.add(new RowData("imageurl", "title", "price"));
        rowDatas.add(new RowData("imageurl", "title", "price"));
        rowDatas.add(new RowData("imageurl", "title", "price"));
        rowDatas.add(new RowData("imageurl", "title", "price"));
        rowDatas.add(new RowData("imageurl", "title", "price"));


    }

}

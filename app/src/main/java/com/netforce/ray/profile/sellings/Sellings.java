package com.netforce.ray.profile.sellings;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforce.ray.R;
import com.netforce.ray.profile.sellings.offer_list.InnerRowData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sellings extends Fragment {


    private Context context;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<RowData> rowDatas = new ArrayList<>();

    public Sellings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sellings, container, false);
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
        ArrayList<InnerRowData> innerRowDatas = new ArrayList<>();
        innerRowDatas.add(new InnerRowData());
        rowDatas.add(new RowData(innerRowDatas, "sold"));
        rowDatas.add(new RowData(innerRowDatas, "sold"));
        rowDatas.add(new RowData(innerRowDatas, "available"));
        rowDatas.add(new RowData(innerRowDatas, "available"));
        rowDatas.add(new RowData(innerRowDatas, "sold"));
        rowDatas.add(new RowData(innerRowDatas, "sold"));
        rowDatas.add(new RowData(innerRowDatas, "sold"));
        rowDatas.add(new RowData(innerRowDatas, "available"));


    }
}

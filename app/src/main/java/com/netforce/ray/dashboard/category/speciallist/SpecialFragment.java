package com.netforce.ray.dashboard.category.speciallist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private ArrayList<SpecialData> newsDatas = new ArrayList<>();
    private LinearLayoutManager layoutManager;

    public SpecialFragment() {
        // Required empty public constructor
    }



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
        setupData();
        adapter = new SpecialAdapter(context, newsDatas);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void setupData() {
        try {
            newsDatas.clear();
        } catch (Exception ex) {
        }
        newsDatas.add(new SpecialData("", "New in Area"));
        newsDatas.add(new SpecialData("", "Giveaways"));
        newsDatas.add(new SpecialData("", "Friends & Following"));
        newsDatas.add(new SpecialData("", "Young Designer"));
        newsDatas.add(new SpecialData("", "Summer & Fashion"));
        newsDatas.add(new SpecialData("", "Summer Festival Season"));
        newsDatas.add(new SpecialData("", "Seek & Sell Selection"));


    }

}

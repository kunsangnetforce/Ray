package com.netforce.ray.profile.favourites;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforce.ray.R;
import com.netforce.ray.profile.favourites.offer_list.OfferData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Favourite extends Fragment {


    private Context context;
    private RecyclerView recyclerView;
    private FavouriteAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ArrayList<FavouriteData> favouriteDatas = new ArrayList<>();

    public Favourite() {
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

    private void setupRecyclerView(View view)
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        setupData();
        adapter = new FavouriteAdapter(context, favouriteDatas);
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void setupData() {
        try {
            favouriteDatas.clear();
        } catch (Exception ex) {

        }
        ArrayList<OfferData> offerDatas = new ArrayList<>();
        offerDatas.add(new OfferData());
        offerDatas.add(new OfferData());
        offerDatas.add(new OfferData());
        favouriteDatas.add(new FavouriteData(offerDatas, ""));
        favouriteDatas.add(new FavouriteData(offerDatas, ""));
        offerDatas = new ArrayList<>();
        offerDatas.add(new OfferData());
        offerDatas.add(new OfferData());
        favouriteDatas.add(new FavouriteData(offerDatas, ""));
        favouriteDatas.add(new FavouriteData(offerDatas, "available"));
        offerDatas = new ArrayList<>();
        favouriteDatas.add(new FavouriteData(offerDatas, "sold"));
        favouriteDatas.add(new FavouriteData(offerDatas, "sold"));
        offerDatas = new ArrayList<>();
        offerDatas.add(new OfferData());
        favouriteDatas.add(new FavouriteData(offerDatas, "sold"));
        offerDatas = new ArrayList<>();
        offerDatas.add(new OfferData());
        offerDatas.add(new OfferData());
        offerDatas.add(new OfferData());
        favouriteDatas.add(new FavouriteData(offerDatas, "available"));


    }
}

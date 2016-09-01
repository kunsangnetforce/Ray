package com.netforce.ray.special_categories;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.netforce.ray.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialAndCategory extends AppCompatActivity
{


    ScrollView scrollView;
    int type = 0;
    Categories_Data searchdata;
    RecyclerView recyclerView,recyclerView_sort;
    GridLayoutManager layoutManager,layoutManager_sort;
    CategoriesAdapter adapter;
    CategoriesSortAdapter sortAdapter;
    Button sort_button;
    ArrayList<Categories_Data> highestDatas = new ArrayList<Categories_Data>();
    Toolbar toolbar;
    ArrayList<Categories_Data> highestDatas_sort = new ArrayList<Categories_Data>();
    TextView date_txt;
    Context context;


    protected void onCreate(Bundle savedInstanceState)
    {

       super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_special_and_category);
        setupToolBar();
        setupRecyclerView();
        setupRecyclerView_sort();

    }

    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "Filter";
        getSupportActionBar().setTitle(teams);

    }


    private void setupRecyclerView()
    {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CategoriesAdapter(getApplicationContext(), highestDatas);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        setupFinsihedDatas();

        adapter.notifyDataSetChanged();

    }

    private void setupRecyclerView_sort()
    {

        recyclerView_sort = (RecyclerView) findViewById(R.id.recycler_sortby);
        layoutManager_sort = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView_sort.setLayoutManager(layoutManager_sort);
        sortAdapter = new CategoriesSortAdapter(getApplicationContext(), highestDatas_sort);
        recyclerView_sort.setAdapter(sortAdapter);
        recyclerView_sort.setNestedScrollingEnabled(false);
        setupFinsihedDatas_sort();

        sortAdapter.notifyDataSetChanged();
    }

    private void setupFinsihedDatas_sort() {

        try
        {
            highestDatas_sort.clear();
        }
        catch (Exception ex)
        {

        }
        highestDatas_sort.add(new Categories_Data("Everything", "imageurl"));
        highestDatas_sort.add(new Categories_Data("New in your area", "imageurl"));
        highestDatas_sort.add(new Categories_Data("Electronics", "imageurl"));



    }


    private void setupFinsihedDatas()
    {
        try
        {
            highestDatas.clear();
        }
        catch (Exception ex)
        {

        }
        highestDatas.add(new Categories_Data("Everything", "imageurl"));
        highestDatas.add(new Categories_Data("New in your area", "imageurl"));
        highestDatas.add(new Categories_Data("Electronics", "imageurl"));
        highestDatas.add(new Categories_Data("Fashion & Accessories", "imageurl"));
        highestDatas.add(new Categories_Data("Home & Garden", "imageurl"));
        highestDatas.add(new Categories_Data("Movie,Books & Music", "imageurl"));
        highestDatas.add(new Categories_Data("Baby & Child", "imageurl"));
        highestDatas.add(new Categories_Data("Sports,Leisure & Games", "imageurl"));
        highestDatas.add(new Categories_Data("Car & Motors", "imageurl"));
        highestDatas.add(new Categories_Data("Services", "imageurl"));
        highestDatas.add(new Categories_Data("Others", "imageurl"));

    }


}

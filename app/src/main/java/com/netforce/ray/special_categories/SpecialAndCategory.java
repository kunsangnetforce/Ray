package com.netforce.ray.special_categories;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.netforce.ray.R;
import com.netforce.ray.dashboard.Dashboard;
import com.netforce.ray.search.SearchActivity;
import com.netforce.ray.sell.SellActivity;

import java.util.ArrayList;

import app.minimize.com.seek_bar_compat.SeekBarCompat;

/**
 * A simple {@link Fragment} subclass.
 */
public class SpecialAndCategory extends AppCompatActivity implements View.OnClickListener
{

    RecyclerView recyclerView,recyclerView_sort;
    GridLayoutManager layoutManager,layoutManager_sort;
    CategoriesAdapter adapter;
    CategoriesSortAdapter sortAdapter;
    ArrayList<Categories_Data> highestDatas = new ArrayList<Categories_Data>();
    Toolbar toolbar;
    ArrayList<Categories_Data> highestDatas_sort = new ArrayList<Categories_Data>();
    TextView viewRangetxt,viewRangetxt2;
    Button all_button,applyButton ,clearButton;



    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_special_and_category);
        setupToolBar();
        setupLayout();
        setupRecyclerView();

       setupRecyclerView_sort();

    }

    private void setupLayout() {

        SeekBarCompat radiusSeekbar = (SeekBarCompat) findViewById(R.id.distanceSeekbar);

        SeekBarCompat materialSeekBar2 = (SeekBarCompat) findViewById(R.id.materialSeekBar2);

        viewRangetxt = (TextView) findViewById(R.id.textviewrange);

        viewRangetxt2 = (TextView) findViewById(R.id.textviewrange2);

        applyButton = (Button) findViewById(R.id.applyButton);

        clearButton = (Button) findViewById(R.id.clearButton);

        applyButton.setOnClickListener(this);

        clearButton.setOnClickListener(this);

        radiusSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                if (i == 100)
                {

                    viewRangetxt.setText("Everywhere");

                }
                else
                {
                    viewRangetxt.setText(String.valueOf(i));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        materialSeekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                if (i == 100) {
                    viewRangetxt2.setText("forever");

                } else {
                    viewRangetxt2.setText(String.valueOf(i));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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
        all_button = (Button) findViewById(R.id.allcategoryButton);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CategoriesAdapter(getApplicationContext(), highestDatas);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        setupFinsihedDatas();

        adapter.notifyDataSetChanged();

        all_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.isSelected()) {
                    view.setSelected(false);
                    all_button.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_circle_outline, 0);
                    all_button.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));

                } else {
                    view.setSelected(true);
                    all_button.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_circle_filled, 0);

                    all_button.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
                }
            }
        });

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
        highestDatas_sort.add(new Categories_Data("Distance", "imageurl"));
        highestDatas_sort.add(new Categories_Data("Date", "imageurl"));
        highestDatas_sort.add(new Categories_Data("Low to High Price", "imageurl"));
        highestDatas_sort.add(new Categories_Data("High to Low Price", "imageurl"));

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.more:
                return true;
            case android.R.id.home:
                finish();
                overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }


    public  void onClick(View view)
    {

        switch (view.getId())
        {
            case R.id.applyButton:
                Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                finish();
                break;

            case R.id.clearButton:
                Intent search = new Intent(getApplicationContext(), SearchActivity.class);
                search.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(search);
               overridePendingTransition(R.anim.enter, R.anim.exit);
                break;

        }


    }

}

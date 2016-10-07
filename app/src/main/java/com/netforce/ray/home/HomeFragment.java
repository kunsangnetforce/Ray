package com.netforce.ray.home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforce.ray.R;
import com.netforce.ray.home.filter.FilterAdapter;
import com.netforce.ray.home.filter.FilterData;
import com.netforce.ray.search.SearchActivity;
import com.netforce.ray.sell.SellActivity;

import java.util.ArrayList;

import it.carlom.stikkyheader.core.StikkyHeaderBuilder;
import it.carlom.stikkyheader.core.animator.AnimatorBuilder;
import it.carlom.stikkyheader.core.animator.HeaderStikkyAnimator;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements  View.OnClickListener,OnRefreshListener
{

    Context context;
    RecyclerView recyclerView,filterRecyclerView;
    HomeAdapter adapter;
    ArrayList<HomeData> homeDatas = new ArrayList<>();
    public static  ArrayList<FilterData> filterData = new ArrayList<>();
    GridLayoutManager layoutManager;
    SwipeRefreshLayout mSwipyRefreshLayout;
    FloatingActionButton floatingActionButtonSell;
    StikkyHeaderBuilder stikkyHeader;
    RelativeLayout relativlayoutSearch;
    LinearLayoutManager filterlinearlayoutManager;
    public FilterAdapter filterAdapter;



    public HomeFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        context = getActivity();

        mSwipyRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipyrefreshlayout);

        mSwipyRefreshLayout.setRefreshing(true);

        mSwipyRefreshLayout.setOnRefreshListener(this);

        setupRecyclerView(view);

        setupFilterRecyclerView(view);

        return view;
    }

    private void setupFilterRecyclerView(View v)
    {


        setFilterData();

        filterRecyclerView = (RecyclerView) v.findViewById(R.id.filterRecycler);

         filterlinearlayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        filterRecyclerView.setLayoutManager(filterlinearlayoutManager);


        filterAdapter = new FilterAdapter(context,filterData);

        filterRecyclerView.setAdapter(filterAdapter);

    }

    private void setFilterData()
    {

            try {
                filterData.clear();
            } catch (Exception ex) {
            }
        filterData.add(new FilterData("Electronics"));
        filterData.add(new FilterData("Home & Gorden"));
        filterData.add(new FilterData("Movie Books & Music"));
        filterData.add(new FilterData("Services"));
        filterData.add(new FilterData("Car & Motors"));
        filterData.add(new FilterData("Baby & Child"));
        filterData.add(new FilterData("Others"));

    }


    @Override
    public void onRefresh()
    {
        recyclerView.setVisibility(View.GONE);
        Toast.makeText(getActivity(), "Refresh", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipyRefreshLayout.setRefreshing(false);

                load_refresh(0);
            }
        }, 2000);
    }

    private void load_refresh(int n)
    {
        recyclerView.setVisibility(View.GONE);

        homeDatas.clear();

        Ion.with(this)
                .load("http://odishatv.in/otv-app/write/nation.php?counter="+n)





                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {

                        if (result != null)
                        {

                            for (int i = 0; i < result.size(); i++)
                            {
                                JsonObject jsonObject = (JsonObject) result.get(i);


                                String name = jsonObject.get("post_title").toString();

                                homeDatas.add(new HomeData("url", name, "price"));

                                System.out.println("imageurl ======================"  + name);

                            }
                            mSwipyRefreshLayout.setRefreshing(false);
                            adapter.notifyDataSetChanged();
                            recyclerView.setVisibility(View.VISIBLE);


                        } else {
                            Log.e("error", e.toString());
                        }
                    }
                });

    }

    private void setupRecyclerView(View view)
    {

        relativlayoutSearch = (RelativeLayout)view.findViewById(R.id.relativeLayoutSearch);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        floatingActionButtonSell = (FloatingActionButton) view.findViewById(R.id.fabSell);

        floatingActionButtonSell.setOnClickListener(this);

        load_refresh(0);

        adapter = new HomeAdapter(context, homeDatas);

        relativlayoutSearch.setOnClickListener(this);

        layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener()
        {

            public void onScrollStateChanged(RecyclerView view, int scrollState)
            {

                super.onScrollStateChanged(recyclerView, scrollState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);

                int totalItemCount = layoutManager.getItemCount();

                int firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

                int lastVisibleItemCount = layoutManager.findLastVisibleItemPosition();

                    if (totalItemCount > 0)
                    {
                        if ((totalItemCount - 1) == lastVisibleItemCount)
                        {
                            //loadMore();//your HTTP stuff goes in this method
                            //loadingProgress.setVisibility(View.VISIBLE);

                            load(20);
                        }
                        else
                        {
                            //loadingProgress.setVisibility(View.GONE);
                        }

                    }

            }

            /* public void onScrolled(RecyclerView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {
                    if (flag_loading == false) {
                        flag_loading = true;
                        additems();
                    }
                }
            }*/
        });



    }

    private void refreshItem() {
        try {
            Thread.sleep(2000);
            mSwipyRefreshLayout.setRefreshing(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    void load(int b)
    {



        Ion.with(this)
                .load("http://odishatv.in/otv-app/write/nation.php?counter="+b)

                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {

                        if (result != null)
                        {

                            for (int i = 0; i < result.size(); i++)
                            {

                                JsonObject jsonObject = (JsonObject) result.get(i);

                                String name = jsonObject.get("post_title").toString();

                                homeDatas.add(new HomeData("url", name, "price"));

                                System.out.println("imageurl ======================"  + name);

                            }
                            mSwipyRefreshLayout.setRefreshing(false);
                            adapter.notifyDataSetChanged();


                        } else {
                            Log.e("error", e.toString());
                        }
                    }
                });

     /*   Ion.with(this)
                .load("http://www.androidbegin.com/tutorial/jsonparsetutorial.txt")

                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>()
                {
                    @Override
                    public void onCompleted(Exception e, JsonObject result)
                    {

                        JsonArray jsonArray = (JsonArray) result.get("worldpopulation");

                        System.out.println("json Array======="+ jsonArray.toString());

                       if (result != null)
                        {


                            for(int i=0; i<jsonArray.size(); i++)
                            {


                                JsonObject jsonObject = (JsonObject) jsonArray.get(i);
                                String image_url = jsonObject.get("flag").toString();
                                String name = jsonObject.get("population").toString();

                                homeDatas.add(new HomeData(image_url, name, "price"));

                                System.out.println("imageurl ======================"+ image_url+ name);

                            }


                            adapter.notifyDataSetChanged();
                        }
                        else {
                            Log.e("error", e.toString());
                        }
                    }
                });*/


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


     class ParallaxStikkyAnimator extends HeaderStikkyAnimator
     {
        @Override
        public AnimatorBuilder getAnimatorBuilder()
        {
            View mHeader_image = getHeader().findViewById(R.id.relativeLayout);
            return AnimatorBuilder.create().applyVerticalParallax(mHeader_image);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        stikkyHeader = StikkyHeaderBuilder.stickTo(recyclerView);
        stikkyHeader.setHeader(R.id.header, (ViewGroup) getView())
                .minHeightHeaderDim(R.dimen.min_height_header)
                .animator(new ParallaxStikkyAnimator())
                .build();
    }




}

package com.netforce.ray.home;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.github.clans.fab.FloatingActionButton;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforce.ray.R;
import com.netforce.ray.search.SearchActivity;
import com.netforce.ray.sell.SellActivity;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;

import it.carlom.stikkyheader.core.StikkyHeaderBuilder;
import it.carlom.stikkyheader.core.animator.AnimatorBuilder;
import it.carlom.stikkyheader.core.animator.HeaderStikkyAnimator;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements View.OnClickListener
{

    Context context;
    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private ArrayList<HomeData> homeDatas = new ArrayList<>();
    private StaggeredGridLayoutManager layoutManager;
    SwipyRefreshLayout mSwipyRefreshLayout;
    FloatingActionButton floatingActionButtonSell;

    StikkyHeaderBuilder stikkyHeader;
    RelativeLayout relativlayoutSearch;


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
        setupRecyclerView(view);


        return view;
    }

    private void setupRecyclerView(View view)
    {


        relativlayoutSearch = (RelativeLayout)view.findViewById(R.id.relativeLayoutSearch);


        mSwipyRefreshLayout = (SwipyRefreshLayout) view.findViewById(R.id.swipyrefreshlayout);

        mSwipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {

                mSwipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.TOP);

            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        load();

        adapter = new HomeAdapter(context, homeDatas);


        relativlayoutSearch.setOnClickListener(this);



        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        recyclerView.setNestedScrollingEnabled(false);


    }

    private void refreshItem() {
        try {
            Thread.sleep(2000);
            mSwipyRefreshLayout.setRefreshing(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    void load(){

        Ion.with(this)
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
                });
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {

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

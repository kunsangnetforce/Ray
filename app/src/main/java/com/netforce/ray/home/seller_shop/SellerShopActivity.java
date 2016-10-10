package com.netforce.ray.home.seller_shop;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforce.ray.R;
import com.netforce.ray.home.HomeAdapter;
import com.netforce.ray.home.HomeData;
import com.netforce.ray.search.SearchActivity;
import com.netforce.ray.sell.SellActivity;

import java.util.ArrayList;



public class SellerShopActivity extends AppCompatActivity implements  View.OnClickListener,SwipeRefreshLayout.OnRefreshListener
{


    Context context;
    RecyclerView recyclerView;
    HomeAdapter adapter;
    ArrayList<SellerShopData> sellerShopDatas = new ArrayList<>();

    GridLayoutManager layoutManager;
    SwipeRefreshLayout mSwipyRefreshLayout;

    RelativeLayout relativlayoutSearch;







    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_shop);

        mSwipyRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipyrefreshlayout);

        mSwipyRefreshLayout.setRefreshing(true);

        mSwipyRefreshLayout.setOnRefreshListener(this);

        setupRecyclerView();

    }





    @Override
    public void onRefresh()
    {
        recyclerView.setVisibility(View.GONE);
        Toast.makeText(getApplicationContext(), "Refresh", Toast.LENGTH_SHORT).show();
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

        sellerShopDatas.clear();

        Ion.with(this)
                .load("http://odishatv.in/otv-app/write/nation.php?counter="+n)

                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {

                        if (result != null) {

                            for (int i = 0; i < result.size(); i++) {
                                JsonObject jsonObject = (JsonObject) result.get(i);


                                String name = jsonObject.get("post_title").toString();

                                sellerShopDatas.add(new HomeData("url", name, "price"));

                                System.out.println("imageurl ======================" + name);

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

    private void setupRecyclerView()
    {

        relativlayoutSearch = (RelativeLayout)findViewById(R.id.relativeLayoutSearch);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        load_refresh(0);

        adapter = new HomeAdapter(context, sellerShopDatas);

        relativlayoutSearch.setOnClickListener(this);

        layoutManager = new GridLayoutManager(getApplicationContext(), 2);
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

                                sellerShopDatas.add(new HomeData("url", name, "price"));

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
                overridePendingTransition(R.anim.enter, R.anim.exit);
                break;

            case R.id.relativeLayoutSearch:
                Intent search = new Intent(context, SearchActivity.class);
                startActivity(search);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                break;


        }
    }


}
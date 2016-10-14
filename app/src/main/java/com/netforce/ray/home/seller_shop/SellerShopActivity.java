package com.netforce.ray.home.seller_shop;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforce.ray.R;
import com.netforce.ray.sell.SellActivity;
import java.util.ArrayList;



public class SellerShopActivity extends AppCompatActivity implements  View.OnClickListener,SwipeRefreshLayout.OnRefreshListener,AppBarLayout.OnOffsetChangedListener
{


    RecyclerView recyclerView;
    SellerShopAdapter adapter;
    ArrayList<SellerShopData> sellerShopDatas = new ArrayList<>();
    GridLayoutManager layoutManager;
    SwipeRefreshLayout mSwipyRefreshLayout;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;
    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;
   Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_shop2);

        mSwipyRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipyrefreshlayout);

        collapsingToolbarLayout = (CollapsingToolbarLayout)  findViewById(R.id.collapsing_toolbar);

        appBarLayout  = (AppBarLayout) findViewById(R.id.app_bar_layout);

        mSwipyRefreshLayout.setRefreshing(true);

        mSwipyRefreshLayout.setOnRefreshListener(this);


        collapsingToolbarLayout.setTitle("Kunsang W..");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.transparent)); // transperent color = #00000000
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));

        setupToolBar();

        setupRecyclerView();





    }

    private void setupToolBar()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }



    /*private void handleToolbarTitleVisibility(float percentage)
    {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR)
        {

            if(!mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(mTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if(mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(mTitleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }
*/



    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i)
    {


        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(i) / (float) maxScroll;

     /*   handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        appBarLayout.addOnOffsetChangedListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        appBarLayout.removeOnOffsetChangedListener(this);
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

                        if (result != null)
                        {

                            for (int i = 0; i < result.size(); i++)
                            {
                                JsonObject jsonObject = (JsonObject) result.get(i);


                                String name = jsonObject.get("post_title").toString();

                                sellerShopDatas.add(new SellerShopData("url", name, "price"));

                                System.out.println("imageurl ======================" + name);

                            }
                            mSwipyRefreshLayout.setRefreshing(false);
                            adapter.notifyDataSetChanged();
                            recyclerView.setVisibility(View.VISIBLE);


                        }
                        else {
                            Log.e("error", e.toString());
                        }
                    }
                });

    }

    private void setupRecyclerView()
    {


        recyclerView = (RecyclerView) findViewById(R.id.seller_shop_recyclerview);

        load_refresh(0);

        adapter = new SellerShopAdapter(getApplicationContext(), sellerShopDatas);


        layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);




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

                                sellerShopDatas.add(new SellerShopData("url", name, "price"));

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

                Intent intent = new Intent(getApplicationContext(), SellActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter, R.anim.exit);
                break;

        }
    }


}
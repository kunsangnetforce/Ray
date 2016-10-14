package com.netforce.ray.home.private_offer;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.netforce.ray.R;
import com.netforce.ray.home.ViewPagerAdapter;


import java.util.ArrayList;

public class PrivateOffer extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener
{


    Toolbar toolbar;
    protected View view;
    MaterialDialog dailog;
    private ViewPager intro_images;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private ViewPagerAdapter mAdapter;
    CollapsingToolbarLayout collapsingToolbarLayout;
    EditText offerEdittxt,private_offer_msg;
    RecyclerView private_offer_list;
    PrivateOfferAdapter privateofferadapter;
    ArrayList<PrivateOfferData> privateOfferDatas = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;

    private int[] mImageResources = {
                    R.drawable.motorcycle,
                    R.drawable.motorcycle,
                    R.drawable.motorcycle,
                    R.drawable.motorcycle,
                    R.drawable.motorcycle
            };



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_offer);

        setupToolBar();

        setupviewpager();
        
        setupoffer_adapter();
    }

    private void setupoffer_adapter()
    {

        setprivate_data();

        private_offer_list = (RecyclerView) findViewById(R.id.private_offer_list);

        privateofferadapter = new PrivateOfferAdapter(getApplicationContext(),privateOfferDatas);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());

        private_offer_list.setLayoutManager(linearLayoutManager);

        private_offer_list.setAdapter(privateofferadapter);


    }

    private void setprivate_data() {

        try{
            privateOfferDatas.clear();

        }
        catch (Exception e){}

        privateOfferDatas.add(new PrivateOfferData("HI"));
        privateOfferDatas.add(new PrivateOfferData("HI"));
        privateOfferDatas.add(new PrivateOfferData("HI"));
    }

    private void setupviewpager()
    {

        offerEdittxt = (EditText) findViewById(R.id.offerEdittxt);

        private_offer_msg = (EditText) findViewById(R.id.private_offer_msg);

        intro_images = (ViewPager) findViewById(R.id.pager_introduction);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);


        collapsingToolbarLayout.setTitle("Motorcycle");
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.transparent)); // transperent color = #00000000
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));



        mAdapter = new ViewPagerAdapter(PrivateOffer.this, mImageResources);



        intro_images.setAdapter(mAdapter);
        intro_images.setCurrentItem(0);
        intro_images.setOnPageChangeListener(PrivateOffer.this);
        setUiPageViewController();

        offerEdittxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                offerEdittxt.setFocusableInTouchMode(true);
                offerEdittxt.setFocusable(true);
            }
        });


        private_offer_msg.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                private_offer_msg.setFocusableInTouchMode(true);
                private_offer_msg.setFocusable(true);
            }
        });




    }

    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);



        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "Motorcycle";
        getSupportActionBar().setTitle(teams);

    }


    private void setUiPageViewController()
    {

        dotsCount = mAdapter.getCount();
        dots = new ImageView[dotsCount];

        for (int i = 0; i < dotsCount; i++)
        {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselected_item));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(4, 0, 4, 0);

            pager_indicator.addView(dots[i], params);
        }

        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
    }





    public void onClick(View v)
    {
        switch (v.getId())
        {

        }
    }



    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    public void onPageSelected(int position)
    {
        for (int i = 0; i < dotsCount; i++)
        {
            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselected_item));
        }

        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));


    }


    public void onPageScrollStateChanged(int state)
    {

    }


    private void showPopUp()
    {

        dailog = new MaterialDialog.Builder(PrivateOffer.this)
                .title("Kunsang Wangyal")
                .customView(R.layout.custom_write_comment, true).build();

        Button b = (Button) dailog.findViewById(R.id.submit);
        // TextView textView = (TextView) dailog.findViewById(R.id.textView1);

        //   mExplosionField.explode(icon,null,0,5000);
        //addListener(dailog.findViewById(R.id.root));

        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                dailog.dismiss();
            }
        });
        dailog.show();




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
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
}

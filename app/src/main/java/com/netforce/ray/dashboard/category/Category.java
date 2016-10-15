package com.netforce.ray.dashboard.category;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforce.ray.R;
import com.netforce.ray.dashboard.category.electronics.ElectronicsData;
import com.netforce.ray.profile.PagerAdapter;
import com.netforce.ray.special_categories.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by John on 9/1/2016.
 */
public class Category extends AppCompatActivity
{

    private Toolbar toolbar;

    HashMap<String, String> tabArray  =  new HashMap<String, String>();



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        setupToolBar();
        getDynamicTab();

    }

    private void getDynamicTab()
    {

        tabArray.clear();
        Ion.with(this)
                .load("http://netforce.biz/seeksell/categories/appcat")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>()
                {
                    @Override
                    public void onCompleted(Exception e, JsonObject result)
                    {

                        if (result != null)
                        {

                            JsonArray re = result.getAsJsonArray("data");
                            for (int i = 0; i < re.size(); i++)
                            {

                                JsonObject jsonObject = (JsonObject) re.get(i);
                                JsonObject vo = jsonObject.getAsJsonObject("Category");
                                String id = vo.get("id").toString();
                                String cat_name = vo.get("name").toString();
                                tabArray.put(id,cat_name);

                                System.out.println("imageurl ======================"  + id + cat_name);

                            }
                            setupTab();


                        } else {
                            Log.e("error", e.toString());
                        }
                    }
                });
    }

    private void setupTab()
    {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        for (HashMap.Entry<String,String> array : tabArray.entrySet())
        {

            System.out.println("Array list ================="+ array);

            System.out.printf("%s -> %s%n", array.getKey(), array.getValue());

            tabLayout.addTab(tabLayout.newTab().setText(array.getValue()));
        }

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final CategoriesAdapter  adapter= new CategoriesAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab)
            {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab)
            {

            }
        });
    }

    private void setupToolBar()
    {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "Categories";
        getSupportActionBar().setTitle(teams);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

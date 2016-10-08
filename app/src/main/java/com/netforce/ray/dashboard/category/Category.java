package com.netforce.ray.dashboard.category;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.netforce.ray.R;
import com.netforce.ray.profile.PagerAdapter;
import com.netforce.ray.special_categories.*;

/**
 * Created by John on 9/1/2016.
 */
public class Category extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        setupToolBar();
        setupTab();
    }

    private void setupTab()
    {
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("SPECIAL"));
        tabLayout.addTab(tabLayout.newTab().setText("ELECTRONICS"));
        tabLayout.addTab(tabLayout.newTab().setText("FASHION & ACCESORIES"));
        tabLayout.addTab(tabLayout.newTab().setText("Home & Garden"));
        tabLayout.addTab(tabLayout.newTab().setText("Movies,Books amd Music"));
        tabLayout.addTab(tabLayout.newTab().setText("Baby and child"));
        tabLayout.addTab(tabLayout.newTab().setText("Sport,Leisure and Games"));
        tabLayout.addTab(tabLayout.newTab().setText("Car and Motors"));
        tabLayout.addTab(tabLayout.newTab().setText("Services"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final CategoriesAdapter  adapter= new CategoriesAdapter(getSupportFragmentManager(), tabLayout.getTabCount());


        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

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

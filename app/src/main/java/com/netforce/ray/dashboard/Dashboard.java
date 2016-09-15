package com.netforce.ray.dashboard;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.netforce.ray.R;
import com.netforce.ray.dashboard.navigation.NavigationFragment;
import com.netforce.ray.general.UserSessionManager;
import com.netforce.ray.home.HomeFragment;
import com.netforce.ray.login.LoginActivity;
import com.netforce.ray.profile.UserProfile;
import com.netforce.ray.search.SearchActivity;
import com.netforce.ray.special_categories.SpecialAndCategory;

public class Dashboard extends AppCompatActivity
{

    private Toolbar toolbar;
    private AccountHeader headerResult;
    private HomeFragment homeFragment;
    private Menu menu;
    private NavigationFragment drawer;
    private UserSessionManager userSessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setupToolBar();
        //setupNavigation();
        setupNavigationCustom();
        setupHomeFragment();

    }

    private void setupNavigationCustom()
    {
        drawer = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        drawer.setup(R.id.fragment, (DrawerLayout) findViewById(R.id.drawer), toolbar);
    }


    @Override
    protected void onResume() {
        super.onResume();
        hideKeyboard();
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void setupToolBar()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "Home";
        getSupportActionBar().setTitle(teams);

    }

    private void replaceFragment(Fragment newFragment, String tag)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.drawer_layout, newFragment, tag);
        transaction.commit();
    }

    private void setupHomeFragment() {
        String teams = "Home";
        getSupportActionBar().setTitle(teams);
        if (homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        String tagName = homeFragment.getClass().getName();
        replaceFragment(homeFragment, tagName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId())
        {



          /*  case R.id.search:
                startActivity(new Intent(this, SearchActivity.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);
                return true;*/

            case R.id.filter:
                startActivity(new Intent(this, SpecialAndCategory.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);
                return true;
            case R.id.profile:
              /*  userSessionManager = new UserSessionManager(getApplicationContext());
                if (userSessionManager.getToken().length() < 1)
                {
                    startActivity(new Intent(this, LoginActivity.class));
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    return true;

                }
                else
                {
                    startActivity(new Intent(this, UserProfile.class));
                    overridePendingTransition(R.anim.enter, R.anim.exit);
                    return true;
                }
             */

                startActivity(new Intent(this, UserProfile.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

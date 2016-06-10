package com.netforce.ray.dashboard;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.netforce.ray.R;
import com.netforce.ray.dashboard.navigation.NavigationFragment;
import com.netforce.ray.home.HomeFragment;
import com.netforce.ray.home.RecyclerViewAdapter;
import com.netforce.ray.home.RowData;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    private Toolbar toolbar;
    private AccountHeader headerResult;
    private HomeFragment homeFragment;
    private Menu menu;
    private NavigationFragment drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setupToolBar();
        //setupNavigation();
        setupNavigationCustom();
        setupHomeFragment();
    }

    private void setupNavigationCustom() {
        drawer = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        drawer.setup(R.id.fragment, (DrawerLayout) findViewById(R.id.drawer), toolbar);
    }

    private void setupNavigation() {
        setupHeader();
        PrimaryDrawerItem home = new PrimaryDrawerItem().withName(R.string.home).withIcon(R.drawable.ic_home);
        PrimaryDrawerItem special = new PrimaryDrawerItem().withName(R.string.special).withIcon(R.drawable.ic_star);
        PrimaryDrawerItem category = new PrimaryDrawerItem().withName(R.string.category).withIcon(R.drawable.ic_tag);
        PrimaryDrawerItem sell = new PrimaryDrawerItem().withName(R.string.sell).withIcon(R.drawable.ic_camera);
        PrimaryDrawerItem invite = new PrimaryDrawerItem().withName(R.string.invite).withIcon(R.drawable.ic_invite);
        PrimaryDrawerItem help = new PrimaryDrawerItem().withName(R.string.help).withIcon(R.drawable.ic_help);


//create the drawer and remember the `Drawer` result object
        final Drawer result = new DrawerBuilder()
                .withActivity(this)
                .withStickyFooter(R.layout.footer).withFooterClickable(true)
                .withAccountHeader(headerResult)
                .withToolbar(toolbar)
                .addDrawerItems(
                        home, special, category, sell, invite, help
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D

                        return false;
                    }
                })

                .build();
        result.getStickyFooter().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMessage("clicked");
                result.closeDrawer();
            }
        });

    }

    private void showMessage(String clicked) {
        Toast.makeText(Dashboard.this, clicked, Toast.LENGTH_SHORT).show();
    }

    private void setupHeader() {
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withSelectionListEnabledForSingleProfile(false)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_invite))).withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
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

    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "Home";
        getSupportActionBar().setTitle(teams);

    }

    private void replaceFragment(Fragment newFragment, String tag) {

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
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.search:
                startActivity(new Intent(this, SearchActivity.class));
                overridePendingTransition(R.anim.enter, R.anim.exit);
                return true;
            case R.id.profile:
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

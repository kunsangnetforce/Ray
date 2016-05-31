package com.netforce.ray.dashboard;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
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

public class Dashboard extends AppCompatActivity {

    private Toolbar toolbar;
    private AccountHeader headerResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        setupToolBar();
        setupNavigation();
    }

    private void setupNavigation() {
        setupHeader();
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
                        special, category, sell, invite, help
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

    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String teams = "Home";
        getSupportActionBar().setTitle(teams);
    }
}

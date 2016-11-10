package com.netforce.ray.dashboard.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.netforce.ray.R;
import com.netforce.ray.Splash;
import com.netforce.ray.dashboard.category.Category;
import com.netforce.ray.dashboard.navigation.Special.SpecialActivity;
import com.netforce.ray.dashboard.navigation.wanted.WantedActivity;
import com.netforce.ray.general.UserSessionManager;
import com.netforce.ray.home.HomeFragment;
import com.netforce.ray.sell.SellActivity;
import com.netforce.ray.special_categories.SpecialAndCategory;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends Fragment implements RecyclerAdapterDrawer.clickListner, View.OnClickListener {

    public static final String preFile = "textFile";
    public static final String userKey = "key";
    private static final String TAG = "gcm_tag";
    private static int POSITION = 0;
    public static ActionBarDrawerToggle mDrawerToggle;
    public static DrawerLayout mDrawerLayout;
    boolean mUserLearnedDrawer;
    boolean mFromSavedInstance;
    View view;
    RecyclerView recyclerView;
    public static RecyclerAdapterDrawer adapter;
    public static final String PREFS_NAME = "call_recorder";
    private SharedPreferences loginPreferences;
    public static SharedPreferences.Editor loginPrefsEditor;
    public static List<RowDataDrawer> list = new LinkedList<>();
    private Context context;
    TextView footer;
    RelativeLayout header;
    CircleImageView circleImageViewProfilePic;
    TextView textViewName;
    private UserSessionManager userSessionManager;

    private HomeFragment homeFragment;
    private SpecialAndCategory specialAndCategoryFragment;

    public NavigationFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_navigation, container, false);
        initView();
        return view;
    }

    private void initView()
    {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        footer = (TextView) view.findViewById(R.id.textviewFooter);
        list = setDrawer();
        header = (RelativeLayout) view.findViewById(R.id.header);
        circleImageViewProfilePic = (CircleImageView) view.findViewById(R.id.imageViewProfilePic);

        textViewName = (TextView) view.findViewById(R.id.textviewName);
        adapter = new RecyclerAdapterDrawer(context, list);


        adapter.setClickListner(this);
        footer.setOnClickListener(this);
        header.setOnClickListener(this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        //sharedprefrance
        loginPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        userSessionManager = new UserSessionManager(context);


        if (userSessionManager.getName().length() > 0) {
            textViewName.setText(userSessionManager.getName());
            String imageURL = "https://graph.facebook.com/" + userSessionManager.getFBID() + "/picture?type=large";
            Picasso.with(context)
                    .load(imageURL)
                    .placeholder(R.drawable.pro_pic)
                    .error(R.drawable.pro_pic)
                    .into(circleImageViewProfilePic);
            Picasso.with(context)
                    .load(imageURL)
                    .placeholder(R.drawable.pro_pic)
                    .error(R.drawable.pro_pic)
                 ;

        }

    }


    private List<RowDataDrawer> setDrawer()
    {
        List<RowDataDrawer> list = new ArrayList<>();
        String title[] = {"HOME", "CATEGORY", "SPECIAL", "SELL","WANTED", "INVITE FRIENDS", "HELP", "ACCOUNT", "SETTINGS"};

        int drawableId[];
        drawableId = new int[]{
              R.drawable.ic_home, R.drawable.categories_icon, R.drawable.icon_special, R.drawable.icon_sell, R.drawable.icon_sell, R.drawable.ic_invite_frnd, R.drawable.ic_help, R.drawable.ic_account, R.drawable.ic_setting
        };




        for (int i = 0; i < title.length; i++)
        {
            RowDataDrawer current = new RowDataDrawer();
            current.text = title[i];
            current.id = drawableId[i];
            list.add(current);

        }
        Log.i("TAG count", list.size() + "");
        return list;
    }


    public static void openDrawer() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), userKey, "false"));
        if (savedInstanceState != null) {
            mFromSavedInstance = true;
        }
    }

    public void setup(int id, final DrawerLayout drawer, Toolbar toolbar) {
        view = getActivity().findViewById(id);

        mDrawerLayout = drawer;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawer, toolbar, R.string.drawer_open, R
                .string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                hideSoftKeyboard(getActivity());

                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    savedInstances(getActivity(), userKey, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();

            }

        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDrawerLayout.closeDrawers();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public static void savedInstances(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharePreference = context.getSharedPreferences(preFile, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePreference.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharePreference = context.getSharedPreferences(preFile, Context.MODE_PRIVATE);
        return sharePreference.getString(preferenceName, defaultValue);
    }

    @Override
    public void itemClicked(View view, int position) {
        try
        {

            RecyclerAdapterDrawer.selected_item = position;
            adapter.notifyDataSetChanged();
            mDrawerLayout.closeDrawers();
            switch (RecyclerAdapterDrawer.selected_item)
            {
                case 0:
                    setupHomeFragment();
                    break;
                case 1:
                    Intent i = new Intent(getActivity(), Category.class);
                    startActivity(i);
                    break;
                case 2:
                    Intent i2 = new Intent(getActivity(), SpecialActivity.class);
                    startActivity(i2);
                   // setupSpecial_Category(0);
                    break;
                case 3:
                   // setupSpecial_Category(1);
                    Intent sell = new Intent(getActivity(), SellActivity.class);
                    startActivity(sell);
                    break;
                case 4:
                    Intent i3 = new Intent(getActivity(), WantedActivity.class);
                    startActivity(i3);
                    break;
                case 5:
                    break;
                case 6:
                    break;

            }

        } catch (Exception e) {

        }


    }

    public static void hideSoftKeyboard(Activity activity)
    {


        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.textviewFooter:
                userlogout();

                //showMessage("Clicked");
                break;
            case R.id.header:
                showMessage("header");
                break;
        }
    }

    private void showMessage(String clicked) {
        Toast.makeText(context, clicked, Toast.LENGTH_SHORT).show();
    }

    private void replaceFragment(Fragment newFragment, String tag) {

        FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.drawer_layout, newFragment, tag);
        transaction.commit();
    }

    private void setupHomeFragment() {
        String teams = "Home";
        ((AppCompatActivity) context).getSupportActionBar().setTitle(teams);
        homeFragment = new HomeFragment();
        String tagName = homeFragment.getClass().getName();
        replaceFragment(homeFragment, tagName);
    }

   /* private void setupSpecial_Category(int type) {
        String teams = context.getString(R.string.hot_in_your_area);
        ((AppCompatActivity) context).getSupportActionBar().setTitle(teams);
        specialAndCategoryFragment = new SpecialAndCategoryFragment();
        String tagName = specialAndCategoryFragment.getClass().getName();
        Bundle args = new Bundle();
        args.putInt("type", type);
        specialAndCategoryFragment.setArguments(args);
        replaceFragment(specialAndCategoryFragment, tagName);
    }*/


    private void userlogout() {
        String checked_userid= Splash.sharedpreferences.getString("user_id",null);
        if(checked_userid!=null)
        {

            Splash.sharedpreferences.edit().remove("user_id").apply();
            Toast.makeText(getActivity(), "sharedpref delete", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }
        else{
            Toast.makeText(getActivity(),"null sharedpref",Toast.LENGTH_SHORT).show();
        }



    }
}


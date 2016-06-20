package com.netforce.ray.dashboard.navigation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.netforce.ray.R;
import com.netforce.ray.general.UserSessionManager;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

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
    private ImageView imageViewGB;

    public NavigationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_navigation, container, false);
        initView();
        return view;
    }

    private void initView() {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        footer = (TextView) view.findViewById(R.id.textviewFooter);
        list = setDrawer();
        header = (RelativeLayout) view.findViewById(R.id.header);
        circleImageViewProfilePic = (CircleImageView) view.findViewById(R.id.imageViewProfilePic);
        imageViewGB= (ImageView) view.findViewById(R.id.imageViewBG);
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
                    .into(imageViewGB);

        }

    }



    private List<RowDataDrawer> setDrawer() {
        List<RowDataDrawer> list = new ArrayList<>();
        String title[] = {"Alert", "Home", "Special", "Category", "Sell", "Invite friends", "Help"};
        int drawableId[];
        drawableId = new int[]{R.drawable.ic_home, R.drawable.ic_home, R.drawable.ic_star, R.drawable.ic_tag, R.drawable.ic_camera, R.drawable.ic_invite, R.drawable.ic_help};
        for (int i = 0; i < title.length; i++) {
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
        try {


            RecyclerAdapterDrawer.selected_item = position;
            adapter.notifyDataSetChanged();
            mDrawerLayout.closeDrawers();
            switch (RecyclerAdapterDrawer.selected_item) {
                case 0:
                    break;
            }

        } catch (Exception e) {

        }


    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textviewFooter:
                showMessage("Clicked");
                break;
            case R.id.header:
                showMessage("header");
                break;
        }
    }

    private void showMessage(String clicked) {
        Toast.makeText(context, clicked, Toast.LENGTH_SHORT).show();
    }

}


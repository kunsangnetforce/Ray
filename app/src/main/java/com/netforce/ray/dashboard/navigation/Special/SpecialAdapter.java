package com.netforce.ray.dashboard.navigation.Special;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforce.ray.R;
import com.netforce.ray.dashboard.category.speciallist.SpecialFragment;
import com.netforce.ray.home.HomeFragment;
import com.netforce.ray.profile.favourites.Favourite;
import com.netforce.ray.profile.sellings.Sellings;
import com.netforce.ray.profile.userprofile.Profile;

/**
 * Created by John on 9/1/2016.
 */
public class SpecialAdapter extends FragmentStatePagerAdapter
{
    int mNumOfTabs;

    public SpecialAdapter(FragmentManager fm, int NumOfTabs)
    {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position)
    {

        switch (position)
        {
            case 0:
                HomeFragment special = new HomeFragment();
                return special;
            case 1:
                HomeFragment sellings = new HomeFragment();
                return sellings;
            case 2:
                HomeFragment favourite = new HomeFragment();
                return favourite;
            case 3:
                HomeFragment profile = new HomeFragment();
                return profile;
            case 4:
                HomeFragment favourite2 = new HomeFragment();
                return favourite2;
            case 5:
                HomeFragment favourite3 = new HomeFragment();
                return favourite3;
            case 6:
                HomeFragment favourite4 = new HomeFragment();
                return favourite4;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
package com.netforce.ray.dashboard.navigation.Special;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforce.ray.R;
import com.netforce.ray.dashboard.category.electronics.ElectronicsFragment;
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
                ElectronicsFragment special = new ElectronicsFragment();
                return special;
            case 1:
                ElectronicsFragment sellings = new ElectronicsFragment();
                return sellings;
            case 2:
                ElectronicsFragment favourite = new ElectronicsFragment();
                return favourite;
            case 3:
                ElectronicsFragment profile = new ElectronicsFragment();
                return profile;
            case 4:
                ElectronicsFragment favourite2 = new ElectronicsFragment();
                return favourite2;
            case 5:
                ElectronicsFragment favourite3 = new ElectronicsFragment();
                return favourite3;
            case 6:
                ElectronicsFragment favourite4 = new ElectronicsFragment();
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
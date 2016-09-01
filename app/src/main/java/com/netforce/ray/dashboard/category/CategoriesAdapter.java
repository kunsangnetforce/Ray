package com.netforce.ray.dashboard.category;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforce.ray.dashboard.category.speciallist.SpecialFragment;
import com.netforce.ray.profile.favourites.Favourite;
import com.netforce.ray.profile.news.News;
import com.netforce.ray.profile.sellings.Sellings;
import com.netforce.ray.profile.userprofile.Profile;

/**
 * Created by John on 9/1/2016.
 */
public class CategoriesAdapter extends FragmentStatePagerAdapter
{
    int mNumOfTabs;

    public CategoriesAdapter(FragmentManager fm, int NumOfTabs)
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
                SpecialFragment special = new SpecialFragment();
                return special;
            case 1:
                Sellings sellings = new Sellings();
                return sellings;
            case 2:
                Favourite favourite = new Favourite();
                return favourite;
            case 3:
                Profile profile = new Profile();
                return profile;
            case 4:
                Favourite favourite2 = new Favourite();
                return favourite2;
            case 5:
                Favourite favourite3 = new Favourite();
                return favourite3;
            case 6:
                Favourite favourite4 = new Favourite();
                return favourite4;
            case 7:
                Favourite favourite5 = new Favourite();
                return favourite5;
            case 8:
                Favourite favourite6 = new Favourite();
                return favourite6;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
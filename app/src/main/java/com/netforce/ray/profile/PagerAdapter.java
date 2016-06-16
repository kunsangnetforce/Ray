package com.netforce.ray.profile;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforce.ray.profile.favourites.Favourite;
import com.netforce.ray.profile.news.News;
import com.netforce.ray.profile.sellings.Sellings;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                News news = new News();
                return news;
            case 1:
                Sellings sellings = new Sellings();
                return sellings;
            case 2:
                Favourite favourite = new Favourite();
                return favourite;
            case 3:
                Profile profile = new Profile();
                return profile;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
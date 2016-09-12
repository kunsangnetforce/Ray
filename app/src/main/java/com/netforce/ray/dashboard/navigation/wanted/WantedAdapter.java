package com.netforce.ray.dashboard.navigation.wanted;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.netforce.ray.dashboard.navigation.wanted.publishitems_fragment.WantedPublishFragment;

/**
 * Created by John on 9/3/2016.
 */
public class WantedAdapter extends FragmentStatePagerAdapter
{
    int mNumOfTabs;

    public WantedAdapter(FragmentManager fm, int NumOfTabs)
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
                WantedPublishFragment wanted = new WantedPublishFragment();
                return wanted;
            case 1:
                PublishFragment publish = new PublishFragment();
                return publish;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}

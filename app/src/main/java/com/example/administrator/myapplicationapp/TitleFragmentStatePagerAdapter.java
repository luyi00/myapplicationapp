package com.example.administrator.myapplicationapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class TitleFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    /**
     * The m fragment list.
     */
    private List<Fragment> mFragmentList = null;
    private String[] titles;

    /**
     * Instantiates a new ab fragment pager adapter.
     */
    public TitleFragmentStatePagerAdapter(FragmentManager mFragmentManager, List<Fragment> fragmentList) {
        super(mFragmentManager);
        mFragmentList = fragmentList;
    }

    /**
     * titles是给TabLayout设置title用的
     */
    public TitleFragmentStatePagerAdapter(FragmentManager mFragmentManager,List<Fragment> fragmentList, String[] titles) {
        super(mFragmentManager);
        mFragmentList = fragmentList;
        this.titles = titles;
    }

    /**
     * 描述：获取数量.
     *
     * @return the count
     * @see android.support.v4.view.PagerAdapter#getCount()
     */
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position < mFragmentList.size()) {
            fragment = mFragmentList.get(position);
        } else {
            fragment = mFragmentList.get(0);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && titles.length > 0)
            return titles[position];
        return null;
    }
}

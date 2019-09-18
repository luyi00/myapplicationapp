package com.example.administrator.myapplicationapp;
//第二个fragment
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {
    private TabLayout tableLayoutsf;
    private ViewPager viewPagersf;
    private List<Fragment> fragmentListsf;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_second,null);
        tableLayoutsf = v.findViewById(R.id.tab_fsec);
        viewPagersf = v.findViewById(R.id.viewpager_fsec);
        fragmentListsf = new ArrayList<Fragment>();
        fragmentListsf.add(new TalentCommunity());
        fragmentListsf.add(new CampusCommunity());
        TitleFragmentStatePagerAdapter adapter = new TitleFragmentStatePagerAdapter(getFragmentManager(), fragmentListsf, new String[]{"达人社区", "校园社区"});
        viewPagersf.setAdapter(adapter);
        tableLayoutsf.setupWithViewPager(viewPagersf);

        return v;
    }
}

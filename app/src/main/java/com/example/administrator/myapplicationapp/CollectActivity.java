package com.example.administrator.myapplicationapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import java.util.ArrayList;
import java.util.List;
public class CollectActivity extends AppCompatActivity {
    private TabLayout tableLayout;
    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collect_activity);
        ActionBar actionBar = getSupportActionBar();
        //添加返回按钮
        actionBar.setDisplayHomeAsUpEnabled(true);

        tableLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.viewpager);

        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new FirstFragment());
        fragmentList.add(new SecondFragment());
        fragmentList.add(new ThirdFragment());
        fragmentList.add(new ForthFragment());
        FragmentPagerAdapter adapter = new TitleFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, new String[]{"第一栏", "第二栏", "第三栏","4"});
        viewPager.setAdapter(adapter);

        tableLayout.setupWithViewPager(viewPager);
    }
}

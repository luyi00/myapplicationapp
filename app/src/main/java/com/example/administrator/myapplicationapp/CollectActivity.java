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
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;
public class CollectActivity extends BaseActivity {
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
        //收藏界面的四个fragment
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new collectfragmentAll());
        fragmentList.add(new ThirdFragment());
        fragmentList.add(new ForthFragment());
        FragmentPagerAdapter adapter = new TitleFragmentPagerAdapter(getSupportFragmentManager(), fragmentList, new String[]{"全部", "科研成果","特色活动"});
        viewPager.setAdapter(adapter);

        tableLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //重写ToolBar返回按钮的行为，关闭此Activity
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

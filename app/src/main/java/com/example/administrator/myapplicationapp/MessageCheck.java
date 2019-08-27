package com.example.administrator.myapplicationapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MessageCheck extends AppCompatActivity {
    private TabLayout mtableLayout;
    private ViewPager mviewPager;
    private List<Fragment> mfragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messagecheck);

        ActionBar actionBar = getSupportActionBar();
        //添加返回按钮
        actionBar.setDisplayHomeAsUpEnabled(true);

        mtableLayout = findViewById(R.id.message_tab);
        mviewPager = findViewById(R.id.message_viewpager);
        //收藏界面的四个fragment
        mfragmentList = new ArrayList<Fragment>();
        mfragmentList.add(new MessageFragment());
        mfragmentList.add(new SecondFragment());
        FragmentPagerAdapter adapter = new TitleFragmentPagerAdapter(getSupportFragmentManager(), mfragmentList, new String[]{"消息", "通知"});
        mviewPager.setAdapter(adapter);

        mtableLayout.setupWithViewPager(mviewPager);
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

package com.example.administrator.myapplicationapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransactionRecord extends BaseActivity {
    private TabLayout tableLayouttr;
    private ViewPager viewPagertr;
    private List<Fragment> fragmentListtr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transactionrecord);
        ActionBar actionBar = getSupportActionBar();
        //添加返回按钮
        actionBar.setDisplayHomeAsUpEnabled(true);
        tableLayouttr = findViewById(R.id.tab_trans);
        viewPagertr = findViewById(R.id.viewpager_trans);
        fragmentListtr = new ArrayList<Fragment>();
        fragmentListtr.add(new TransactionBalance()); //余额界面
        fragmentListtr.add(new Transaction());  //交易记录界面
        FragmentPagerAdapter adapter = new TitleFragmentPagerAdapter(getSupportFragmentManager(), fragmentListtr, new String[]{"余额", "交易记录"});
        viewPagertr.setAdapter(adapter);

        tableLayouttr.setupWithViewPager(viewPagertr);

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

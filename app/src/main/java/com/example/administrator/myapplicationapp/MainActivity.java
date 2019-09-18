package com.example.administrator.myapplicationapp;
//首页
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    //取得Fragment管理器
                    Fragment firstFragment = new FirstFragment();
                    //将新建的firstFragment替换容器中的其他片段，这将重新加载布局文件。
                    transaction.replace(R.id.fragment_container,firstFragment);
                    //我们在替换时可以将替换的片段加入到回退栈中，按下返回按钮时，系统会重新显示替换的片段
                    // transaction.addToBackStack(null);
                    //提交事务
                    transaction.commit();
                    return true;
                case R.id.navigation_community:
                    Fragment secondFragment = new SecondFragment();
                    transaction.replace(R.id.fragment_container,secondFragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_skill:
                    Fragment thirdFragment = new ThirdFragment();
                    transaction.replace(R.id.fragment_container,thirdFragment);
                    transaction.commit();
                    return true;
                case R.id.navigation_mine:
                    Fragment fourFragment = new ForthFragment();
                    transaction.replace(R.id.fragment_container,fourFragment);
                    transaction.commit();
                    return true;
            }
            return false;
        }
    };
    private Button bt1,bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private void init(){
        //取得Fragment管理器
        Fragment firstFragment = new FirstFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //默认下将firstFragment添加到容器中
        transaction.add(R.id.fragment_container,firstFragment);
        //提交事务
        transaction.commit();
    }
}

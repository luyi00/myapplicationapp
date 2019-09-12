package com.example.administrator.myapplicationapp;
//首页
import android.content.SharedPreferences;
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

import com.example.administrator.myapplicationapp.db.UserInformation;

public class MainActivity extends BaseActivity {
    //启动请求代码
    private static final int LOGIN_REQUEST = 1;
    //结果代码
    public static final int LOGIN_OK=1;
    //用户信息
    public UserInformation uf = null;
    //信息保存
    private SharedPreferences.Editor editor;
    private SharedPreferences pref = null;
    //碎片管理
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
    //初始化界面
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //加载碎片
        init();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }
    //初始化碎片
    private void init(){
        //取得Fragment管理器
        Fragment firstFragment = new FirstFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //默认下将firstFragment添加到容器中
        transaction.add(R.id.fragment_container,firstFragment);
        //提交事务
        transaction.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取用户信息
        pref = getSharedPreferences("userData",MODE_PRIVATE);
        if(pref!=null){
            uf = new UserInformation(pref.getString("userPhone",""));
        }else{
            uf = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
           case LOGIN_REQUEST:
               switch (resultCode){
                   case LOGIN_OK:
                       uf = (UserInformation)data.getSerializableExtra("UserInformation");
                       editor = getSharedPreferences("userData",MODE_PRIVATE).edit();
                       editor.putString("userPhone",uf.getUserPhone());
                       editor.apply();
                       break;
                   default:
               }
               break;
           default:
        }
    }
}

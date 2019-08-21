package com.example.administrator.myapplicationapp;
//用户界面，待修改
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class UsersPage extends AppCompatActivity {
    ImageButton homeBtn,infoBtn;
    TextView homeTxt,infoTxt;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userspage);

        init();
        //设置底部菜单栏的点击监听
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //背景替换
                infoBtn.setImageResource(R.drawable.info);
                infoTxt.setTextColor(ContextCompat
                        .getColor(mContext,R.color.colorBlack));
                homeBtn.setImageResource(R.drawable.home_press);
                homeTxt.setTextColor(ContextCompat
                        .getColor(mContext,R.color.colorAnHong));
                //取得Fragment管理器
                Fragment firstFragment = new FirstFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                //将新建的firstFragment替换容器中的其他片段，这将重新加载布局文件。
                transaction.replace(R.id.fragment_container,firstFragment);
                //我们在替换时可以将替换的片段加入到回退栈中，这样我们按下返回
                //按钮时，系统会重新显示替换的片段，读者可以体验一下
                // transaction.addToBackStack(null);
                //提交事务
                transaction.commit();

            }
        });
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //背景替换
                infoBtn.setImageResource(R.drawable.info_press);
                infoTxt.setTextColor(ContextCompat
                        .getColor(mContext,R.color.colorAnHong));
                homeBtn.setImageResource(R.drawable.home);
                homeTxt.setTextColor(ContextCompat
                        .getColor(mContext,R.color.colorBlack));
                //取得Fragment管理器
                Fragment secondFragment = new SecondFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                //将新建的secondFragment替换容器中的其他片段，这将重新加载布局文件。
                final Intent intent=getIntent();
                final String user=intent.getStringExtra("ordername");
                final Bundle bundle=new Bundle();
                bundle.putString("data",user);
                secondFragment.setArguments(bundle);
                transaction.replace(R.id.fragment_container,secondFragment);
                //我们在替换时可以将替换的片段加入到回退栈中，这样我们按下返回
                //按钮时，系统会重新显示替换的片段，读者可以体验一下
                //transaction.addToBackStack(null);
                //提交事务
                transaction.commit();

            }
        });
    }
    private void init(){
        mContext = this;
        homeBtn = (ImageButton)findViewById(R.id.home_btn);
        infoBtn = (ImageButton)findViewById(R.id.info_btn);
        homeTxt = (TextView)findViewById(R.id.home_txt);
        infoTxt = (TextView)findViewById(R.id.info_txt);
        //取得Fragment管理器
        Fragment firstFragment = new FirstFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        //默认下将firstFragment添加到容器中
        transaction.add(R.id.fragment_container,firstFragment);
        //提交事务
        transaction.commit();
    }
}


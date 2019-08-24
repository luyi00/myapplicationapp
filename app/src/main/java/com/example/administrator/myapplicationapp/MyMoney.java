package com.example.administrator.myapplicationapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyMoney extends AppCompatActivity {
    private ImageView moneyview;
    private TextView moneynum,moneysign;
    RelativeLayout r1,r2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mymoney);
        ActionBar actionBar = getSupportActionBar();
        //添加返回按钮
        actionBar.setDisplayHomeAsUpEnabled(true);
        moneyview=(ImageView)findViewById(R.id.money_view);
        moneyview.setImageResource(R.drawable.money_logo);
        moneynum=(TextView)findViewById(R.id.money_number);
        moneysign=(TextView)findViewById(R.id.money_sign);
        r1=(RelativeLayout)findViewById(R.id.depositmoney);
        r2=(RelativeLayout)findViewById(R.id.coupon);

        //充值点击事件
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"前往充值界面",Toast.LENGTH_LONG).show();
            }
        });
        //优惠券点击事件
        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"前往优惠券查看界面",Toast.LENGTH_LONG).show();
            }
        });
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

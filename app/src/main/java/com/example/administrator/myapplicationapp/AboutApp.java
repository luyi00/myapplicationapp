package com.example.administrator.myapplicationapp;
//关于app
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AboutApp extends AppCompatActivity {
    private TextView title,version,preversion,version2,help,feedback;
    private EditText titleEt,contentEt;
    private ImageView imglogo;
    private Button bt1,bt2;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.aboutapp);
            ActionBar actionBar = getSupportActionBar();
            //添加返回按钮
            actionBar.setDisplayHomeAsUpEnabled(true);
            imglogo=(ImageView)findViewById(R.id.imglogo);
            imglogo.setImageResource(R.drawable.logo);   //易技能logo
            title=(TextView)findViewById(R.id.abouttitle);
            version=(TextView)findViewById(R.id.version);
            preversion=(TextView)findViewById(R.id.presentversion);
            version2=(TextView)findViewById(R.id.version2);
            help=(TextView)findViewById(R.id.help);
            feedback=(TextView)findViewById(R.id.feedback);
            bt1=(Button)findViewById(R.id.btn1);
            bt2=(Button)findViewById(R.id.btn2);
            //帮助界面跳转
            bt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        Intent intent2=new Intent(AboutApp.this,Helppage.class);
                        startActivity(intent2);
                }
            });
            //反馈界面跳转
            bt2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        Intent intent2=new Intent(AboutApp.this,Feedbackpage.class);
                        startActivity(intent2);
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

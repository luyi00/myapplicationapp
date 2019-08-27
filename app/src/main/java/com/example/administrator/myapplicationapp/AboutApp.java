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

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.aboutapp);
            //添加返回按钮
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //控件获取
            Button helpAbout = (Button) findViewById(R.id.help_about);
            Button feedbackAbout = (Button) findViewById(R.id.feedback_about);
            //帮助界面跳转
            helpAbout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        Intent intent2=new Intent(AboutApp.this,Helppage.class);
                        startActivity(intent2);
                }
            });
            //反馈界面跳转
            feedbackAbout.setOnClickListener(new View.OnClickListener() {
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

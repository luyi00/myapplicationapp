package com.example.administrator.myapplicationapp;
//设置界面
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SetPage extends AppCompatActivity {
    private TextView tv,tvimage,tvname,tvsex,tvbirth,tvintro;
    private EditText etname,etset,etbirth,etintro;
    private Button aboutversion,exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutapp);
        ActionBar actionBar = getSupportActionBar();
        //添加返回按钮
        actionBar.setDisplayHomeAsUpEnabled(true);

    }
}

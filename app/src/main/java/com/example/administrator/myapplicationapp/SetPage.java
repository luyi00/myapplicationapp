package com.example.administrator.myapplicationapp;
//设置界面
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class SetPage extends AppCompatActivity {
    private TextView tvname,tvsex,tvbirth,tvintro,person,head,tvaboutapp,tvexit;
    private EditText etname,etsex,etbirth,etintro;
    private ImageButton head_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setpage);
        ActionBar actionBar = getSupportActionBar();
        //添加返回按钮
        actionBar.setDisplayHomeAsUpEnabled(true);

        person=(TextView)findViewById(R.id.person);
        head=(TextView)findViewById(R.id.head);
        tvname=(TextView)findViewById(R.id.tv_name);
        tvsex=(TextView)findViewById(R.id.tv_sex);
        tvbirth=(TextView)findViewById(R.id.tv_birth);
        tvintro=(TextView)findViewById(R.id.tv_intro);
        tvaboutapp=(TextView)findViewById(R.id.tv_aboutapp);
        tvexit=(TextView)findViewById(R.id.tv_exit);

        etname=(EditText)findViewById(R.id.et_name);
        etsex=(EditText)findViewById(R.id.et_sex);
        etbirth=(EditText)findViewById(R.id.et_birth);
        etintro=(EditText)findViewById(R.id.et_intro);
        head_img=(ImageButton)findViewById(R.id.head_img);
        head_img.setImageResource(R.drawable.icon_people_fill);
        //跳转到关于app界面
        tvaboutapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SetPage.this,AboutApp.class);
                startActivity(intent);
            }
        });
        //退出登入
        tvexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"退出登录",Toast.LENGTH_LONG).show();
            }
        });
        //点击更换头像
        head_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"更换头像",Toast.LENGTH_LONG).show();
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

package com.example.administrator.myapplicationapp;
//登入界面
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText e1,e2;
    private TextView name,pass;
    private Button btn1,btn2;
    private ImageView imgtop,imgbg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        name=(TextView)findViewById(R.id.loginname);
        name=(TextView)findViewById(R.id.loginpass);
        e1=(EditText)findViewById(R.id.lo_name);
        e2=(EditText)findViewById(R.id.lo_pass);
        imgtop=(ImageView)findViewById(R.id.img01);
        imgtop.setImageResource(R.drawable.logo);
        btn1=(Button)findViewById(R.id.b_sure);
        btn2=(Button)findViewById(R.id.b_reg);
        //确认登入
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //若用户名密码匹配
                if (e1.getText().toString().equals("")&&e2.getText().toString().equals("")) {
                    Intent intent1 = new Intent(LoginActivity.this, UsersPage.class);
                    startActivity(intent1);
                }
                //用户名密码有误
                else{
                    Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_LONG).show();
                }
            }
        });
        //注册
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent2=new Intent(LoginActivity.this,RegisterActivity.class);  //跳转到注册界面
                    startActivity(intent2);
            }
        });
    }
}

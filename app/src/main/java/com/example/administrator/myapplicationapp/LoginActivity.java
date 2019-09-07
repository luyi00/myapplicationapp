package com.example.administrator.myapplicationapp;
//登入界面
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplicationapp.db.DataBase;

public class LoginActivity extends BaseActivity {
    private Button login;
    private String user;
    private String password;
    private TextView forgetPassword;
    private TextView register;
    private DataBase dbHelper;        //数据库
    private boolean find = false;

    public static final int RESULT_OK=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //将返回键显示出来
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //控件获取
        login= (Button)findViewById(R.id.login_login);
        forgetPassword = (TextView)findViewById(R.id.forgetPassword_login);
        register = (TextView) findViewById(R.id.register_login);
        dbHelper = new DataBase(this,"UserStore.db",null,1);
        //登入
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = ((EditText) findViewById(R.id.user_login)).getText().toString().trim();
                password = ((EditText) findViewById(R.id.password_login)).getText().toString().trim();
                if(user.trim().length()<11&&password.trim().length()<6){
                    Toast.makeText(LoginActivity.this,"请先输入正确的账户和密码",Toast.LENGTH_LONG).show();
                }
                else{
//                    调用数据库比对账户和密码
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    Cursor cursor = db.query("user",null,null,null,null,null,null);
                    if(cursor.moveToFirst()){
                        do{
                            String user_phone = cursor.getString(cursor.getColumnIndex("user_phone"));
                            String user_password = cursor.getString(cursor.getColumnIndex("user_password"));
                            if(user.equals(user_phone)&&password.equals(user_password)){
                                cursor.close();
                                find = true;
                                //返回上一个activity
                                Intent intent = new Intent();
                                intent.putExtra("user_phone",user);
                                setResult(RESULT_OK,intent);
                                finish();
                            }
                        }while(cursor.moveToNext());
                        cursor.close();
                    }
                    if(!find){
                        Toast.makeText(LoginActivity.this, "账号或密码输入错误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //忘记密码
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到忘记密码的界面
                Intent intent = new Intent(LoginActivity.this,ForgetActivity.class);
                startActivities(new Intent[]{intent});
            }
        });
        //注册账号
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到注册账号界面
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}

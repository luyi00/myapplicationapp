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

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private String user;
    private String password;
    private TextView forgetPassword;
    private TextView register;
//    private DataBase dbHelper;        //数据库
    private boolean find = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //将返回键显示出来
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        login= (Button)findViewById(R.id.online);
        forgetPassword = (TextView)findViewById(R.id.forgetPassword);
        register = (TextView) findViewById(R.id.register);
//        dbHelper = new DataBase(this,"UserStore.db",null,1);
        //登入
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = ((EditText) findViewById(R.id.user)).getText().toString().trim();
                password = ((EditText) findViewById(R.id.password)).getText().toString().trim();
                if(user.trim().length()<11&&password.trim().length()<6){
                    Toast.makeText(LoginActivity.this,"请先输入正确的账户和密码",Toast.LENGTH_LONG).show();
                }
                else{
                    //调用数据库比对账户和密码
//                    SQLiteDatabase db = dbHelper.getReadableDatabase();
//                    Cursor cursor = db.query("user",null,null,null,null,null,null);
//                    if(cursor.moveToFirst()){
//                        do{
//                            String user_phone = cursor.getString(cursor.getColumnIndex("user_phone"));
//                            String user_password = cursor.getString(cursor.getColumnIndex("user_password"));
//                            if(user.equals(user_phone)&&password.equals(user_password)){
//                                cursor.close();
//                                //跳转到主页
//                                Intent intent = new Intent(LoginActivity.this,UsersPage.class);
//                                intent.putExtra("user_phone",user);
//                                startActivity(intent);
//                                find=true;
//                            }
//                        }while(cursor.moveToNext());
//                        cursor.close();
//                    }
//                    if(!find){
//                        Toast.makeText(LoginActivity.this, "账号或密码输入错误", Toast.LENGTH_SHORT).show();
//                    }
                    Intent intent = new Intent(LoginActivity.this,UsersPage.class);
                    startActivity(intent);
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

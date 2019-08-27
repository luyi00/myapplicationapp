package com.example.administrator.myapplicationapp;
//用户注册界面
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class RegisterActivity extends AppCompatActivity {
    private EditText phoneNumber_reg;
    private EditText password_reg;
    private EditText yanCode_reg;
    private Button yanButton_reg;
    private Button nextStep_reg;
//    private DataBase dbHelper;        //创建数据库
    private String phoneNumber_reg_c;
    private String password_reg_c;
    private String yanCode_reg_str;
    private boolean exits = false;
    private static final int UPDATE = 1;
    private static final int UPDATE_TEXT = 2;
    private static int clock;
    private Random random;
    private class YanHandler extends Handler
    {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch(msg.what)
            {
                case UPDATE://在收到消息时，对界面进行更新
                    yanButton_reg.setText("重新发送"+clock);
                    break;
                case UPDATE_TEXT:
                    yanButton_reg.setText(R.string.sendPass_register);
                    yanButton_reg.setEnabled(true);
                    yanCode_reg_str="1111111";
                    break;
            }
        }
    }
    private YanHandler yanHandler = new YanHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //将返回键显示出来
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        phoneNumber_reg = findViewById(R.id.phoneNumber_reg);
        password_reg = findViewById(R.id.password_reg);
        yanCode_reg = findViewById(R.id.yanCode_reg);
        yanButton_reg = findViewById(R.id.yanButton_reg);
        nextStep_reg = findViewById(R.id.nextStep_reg);
        TextView agreement_reg = findViewById(R.id.agreement_reg);
//        dbHelper = new DataBase(this,"UserStore.db",null,1);
        password_reg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(phoneNumber_reg.getText().toString().trim().length()==11&&password_reg.getText().toString().trim().length()>=6){
                    yanButton_reg.setEnabled(true);
                }else{
                    yanButton_reg.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        yanButton_reg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //发送验证码
                yanCode_reg_str=getRandomStr();
                Toast.makeText(RegisterActivity.this, "验证码为："+yanCode_reg_str, Toast.LENGTH_SHORT).show();
                //如果发送成功
                phoneNumber_reg_c = phoneNumber_reg.getText().toString().trim();
                password_reg_c = password_reg.getText().toString().trim();
                clock = 60;
                yanButton_reg.setEnabled(false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(clock>=0){
                            clock--;
                            Message msg = yanHandler.obtainMessage(UPDATE);
                            yanHandler.sendMessage(msg);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        Message msg = yanHandler.obtainMessage(UPDATE_TEXT);
                        yanHandler.sendMessage(msg);
                    }
                }).start();
            }
        });
        yanCode_reg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(yanCode_reg.getText().toString().trim().length()==6){
                    nextStep_reg.setEnabled(true);
                }else{
                    nextStep_reg.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
//        nextStep_reg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //先判断验证码是否正确
//                if(yanCode_reg.getText().toString().equals(yanCode_reg_str)){
//                    SQLiteDatabase db = dbHelper.getReadableDatabase();
//                    //判断账号是否存在
//                    Cursor cursor = db.query("user",null,null,null,null,null,null);
//                    if(cursor.moveToFirst()){
//                        do{
//                            String user_phone = cursor.getString(cursor.getColumnIndex("user_phone"));
//                            if(phoneNumber_reg.getText().toString().equals(user_phone)){
//                                Toast.makeText(RegisterActivity.this, "账号已存在,请重新输入", Toast.LENGTH_SHORT).show();
//                                exits = true;
//                                break;
//                            }
//                        }while(cursor.moveToNext());
//                        cursor.close();
//                    }
//                    //将账号保存到数据库中并返回主页面
//                    if(!exits){
//                        ContentValues values = new ContentValues();
//                        values.put("user_phone",phoneNumber_reg_c);
//                        values.put("user_password",password_reg_c);
//                        db.insert("user",null,values);
//                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
//                        finish();
//                    }
//                }else{
//                    Toast.makeText(RegisterActivity.this, "验证码错误或过期", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        agreement_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至使用条款和隐私政策界面
            }
        });
    }
    private String getRandomStr(){
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<6;i++){
            int number = random.nextInt(9);
            sb.append(number);
        }
        return sb.toString();
    }
}

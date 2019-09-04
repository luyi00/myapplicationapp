package com.example.administrator.myapplicationapp;
//用户注册界面
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplicationapp.db.DataBase;

import org.json.JSONObject;

import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class RegisterActivity extends BaseActivity {
    private static final String TAG ="RegisterActivity";

    private EditText phoneNumber_reg;
    private EditText password_reg;
    private EditText yanCode_reg;
    private Button yanButton_reg;
    private Button nextStep_reg;
    private DataBase dbHelper;        //创建数据库
    private String phoneNumber_reg_c;
    private String password_reg_c;
    private String yanCode_reg_str;
    private boolean exits = false;
    private static final int UPDATE = 1;
    private static final int UPDATE_TEXT = 2;
    private static int clock;
    private Random random;
    //短信验证
    EventHandler eventHandler;


    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x00:
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    Log.e(TAG, "result : " + result + ", event: " + event + ", data : " + data);
                    if (result == SMSSDK.RESULT_COMPLETE) { //回调  当返回的结果是complete
                        if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) { //获取验证码
                            Toast.makeText(RegisterActivity.this, "发送验证码成功", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "get verification code successful.");
                        } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) { //提交验证码
                            Log.d(TAG, "submit code successful");
                            finish();
                        } else {
                            Log.d(TAG, data.toString());
                        }
                    } else { //进行操作出错，通过下面的信息区分析错误原因
                        try {
                            Throwable throwable = (Throwable) data;
                            throwable.printStackTrace();
                            JSONObject object = new JSONObject(throwable.getMessage());
                            String des = object.optString("detail");//错误描述
                            int status = object.optInt("status");//错误代码
                            //错误代码：  http://wiki.mob.com/android-api-%E9%94%99%E8%AF%AF%E7%A0%81%E5%8F%82%E8%80%83/
                            Log.e(TAG, "status: " + status + ", detail: " + des);
                            if (status > 0 && !TextUtils.isEmpty(des)) {
                                Toast.makeText(RegisterActivity.this, des, Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 0x01:
                    yanButton_reg.setText("重新发送(" + msg.arg1 + ")");
                    break;
                case 0x02:
                    yanButton_reg.setText("获取验证码");
                    yanButton_reg.setClickable(true);
                    break;
            }
        }
    };

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
        dbHelper = new DataBase(this,"UserStore.db",null,1);

        eventHandler = new EventHandler() {

            /**
             * 在操作之后被触发
             *
             * @param event  参数1
             * @param result 参数2 SMSSDK.RESULT_COMPLETE表示操作成功，为SMSSDK.RESULT_ERROR表示操作失败
             * @param data   事件操作的结果
             */

            @Override
            public void afterEvent(int event, int result, Object data) {
                Message message = myHandler.obtainMessage(0x00);
                message.arg1 = event;
                message.arg2 = result;
                message.obj = data;
                myHandler.sendMessage(message);
            }
        };

        SMSSDK.registerEventHandler(eventHandler);
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
                phoneNumber_reg_c = phoneNumber_reg.getText().toString().trim();
                password_reg_c = password_reg.getText().toString().trim();
                //发送验证码
                SMSSDK.getVerificationCode("86", phoneNumber_reg_c);
                Toast.makeText(RegisterActivity.this, "验证码已发送", Toast.LENGTH_SHORT).show();
                //如果发送成功
                clock = 60;
                yanButton_reg.setEnabled(false);
                //开启线程去更新button的text
                new Thread() {
                    @Override
                    public void run() {
                        int totalTime = 60;
                        for (int i = 0; i < totalTime; i++) {
                            Message message = myHandler.obtainMessage(0x01);
                            message.arg1 = totalTime - i;
                            myHandler.sendMessage(message);
                            try {
                                sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        myHandler.sendEmptyMessage(0x02);
                    }
                }.start();
            }
        });
        yanCode_reg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(yanCode_reg.getText().toString().trim().length()==4){
                    nextStep_reg.setEnabled(true);
                }else{
                    nextStep_reg.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        nextStep_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先判断验证码是否正确
                 yanCode_reg_str = yanCode_reg.getText().toString();
                if(null != yanCode_reg_str && yanCode_reg_str.length() == 4){
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    //判断账号是否存在
                    Cursor cursor = db.query("user",null,null,null,null,null,null);
                    if(cursor.moveToFirst()){
                        do{
                            String user_phone = cursor.getString(cursor.getColumnIndex("user_phone"));
                            if(phoneNumber_reg.getText().toString().equals(user_phone)){
                                Toast.makeText(RegisterActivity.this, "账号已存在,请重新输入", Toast.LENGTH_SHORT).show();
                                exits = true;
                                break;
                            }
                        }while(cursor.moveToNext());
                        cursor.close();
                    }
                    //将账号保存到数据库中并返回主页面
                    if(!exits){
                        ContentValues values = new ContentValues();
                        values.put("user_phone",phoneNumber_reg_c);
                        values.put("user_password",password_reg_c);
                        db.insert("user",null,values);
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        SMSSDK.submitVerificationCode("86", phoneNumber_reg_c, yanCode_reg_str);
                    }
                }else{
                    Toast.makeText(RegisterActivity.this, "验证码错误或过期", Toast.LENGTH_SHORT).show();
                }
            }
        });
        agreement_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至使用条款和隐私政策界面
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}

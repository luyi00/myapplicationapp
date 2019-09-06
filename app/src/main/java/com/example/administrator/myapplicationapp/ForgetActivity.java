package com.example.administrator.myapplicationapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplicationapp.db.DataBase;

import org.json.JSONObject;

import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class ForgetActivity extends BaseActivity {
    private static final String TAG ="ForgetActivity";

    private EditText PhoneText_for ;
    private Button SendCode_for;
    private EditText YanCode_for;
    private Button ChangePassword_for;
    private String yanCode_for_str;
    private String PhoneText_for_str;
    private static int clock;
    //短信验证
    EventHandler eventHandler;
    //创建数据库
    private DataBase dbHelper;
    private boolean findUser = false;   //判断账户是否存在

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
                            Toast.makeText(ForgetActivity.this, "发送验证码成功", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "get verification code successful.");
                        } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) { //提交验证码
                            Log.d(TAG, "submit code successful");
                            //跳转到修改密码界面
                            Intent intent = new Intent(ForgetActivity.this,ChangePasswordPage.class);
                            //传入修改密码的手机号
                            intent.putExtra("phoneNumber",PhoneText_for_str);
                            startActivity(intent);
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
                                Toast.makeText(ForgetActivity.this, des, Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 0x01:
                    SendCode_for.setText("重新发送(" + msg.arg1 + ")");
                    break;
                case 0x02:
                    SendCode_for.setText("获取验证码");
                    SendCode_for.setClickable(true);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        //将返回键显示出来
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //获取控件
        PhoneText_for = findViewById(R.id.editText_forget);
        SendCode_for = findViewById(R.id.button_forget);
        YanCode_for = findViewById(R.id.yanCode_forget);
        ChangePassword_for = findViewById(R.id.nextStep_forget);

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
        //设置文本监听器
        PhoneText_for.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(PhoneText_for.getText().toString().trim().length()==11){
                    SendCode_for.setEnabled(true);
                }else{
                    SendCode_for.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //发送验证码按钮
        SendCode_for.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //先判断账户是否存在
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("user",null,null,null,null,null,null);
                if(cursor.moveToFirst()){
                    do{
                        //遍历Cursor所有对象，检测账户信息
                        String DBuserPhone = cursor.getString(cursor.getColumnIndex("user_phone"));
                        if(DBuserPhone.equals(PhoneText_for_str)){
                            findUser = true;
                        }
                    }while(cursor.moveToNext());
                }
                cursor.close();
               if(findUser){
                   Toast.makeText(ForgetActivity.this, "账户未存在，请先注册", Toast.LENGTH_SHORT).show();
                   finish();
               }else{
                   PhoneText_for_str=PhoneText_for.getText().toString().trim();
                   //发送验证码
                   SMSSDK.getVerificationCode("86", PhoneText_for_str);
                   //如果发送成功
                   SendCode_for.setEnabled(false);
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
            }
        });
        //文本监听
        YanCode_for.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(YanCode_for.getText().toString().trim().length()==4){
                    ChangePassword_for.setEnabled(true);
                }else{
                    ChangePassword_for.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ChangePassword_for.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //判断验证码是否正确
                yanCode_for_str = YanCode_for.getText().toString();
                if(yanCode_for_str.length() == 4){
                    //判断验证码是否正确
                    SMSSDK.submitVerificationCode("86", PhoneText_for_str, yanCode_for_str);
                }else{
                    Toast.makeText(ForgetActivity.this, "验证码输入错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterEventHandler(eventHandler);
    }
}

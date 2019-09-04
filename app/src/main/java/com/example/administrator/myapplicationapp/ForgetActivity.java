package com.example.administrator.myapplicationapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class ForgetActivity extends BaseActivity {
    private EditText PhoneText_for ;
    private Button SendCode_for;
    private EditText YanCode_for;
    private Button ChangePassword_for;
    private String yanCode_for_str;
    private String PhoneText_for_str;
    private static int clock;
    private static final int UPDATE = 1;
    private static final int UPDATE_TEXT = 2;

    private class YanHandler extends Handler
    {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch(msg.what)
            {
                case UPDATE://在收到消息时，对界面进行更新
                    SendCode_for.setText("重新发送"+clock);
                    break;
                case UPDATE_TEXT:
                    SendCode_for.setText(R.string.sendPass_forget);
                    SendCode_for.setEnabled(true);
                    yanCode_for_str="1111111";
                    break;
            }
        }
    }
    private YanHandler yanHandler = new YanHandler();
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
        SendCode_for.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送验证码
                yanCode_for_str=getRandomStr();
                Toast.makeText(ForgetActivity.this, "验证码为："+yanCode_for_str, Toast.LENGTH_SHORT).show();
                //如果发送成功
                PhoneText_for_str=PhoneText_for.getText().toString().trim();
                clock = 60;
                SendCode_for.setEnabled(false);
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
        //文本监听
        YanCode_for.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(YanCode_for.getText().toString().trim().length()==6){
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
                if(YanCode_for.getText().toString().trim().equals(yanCode_for_str)){
                    //跳转到修改密码界面
                    Intent intent = new Intent(ForgetActivity.this,ChangePasswordPage.class);
                    //传入修改密码的手机号
                    intent.putExtra("phoneNumber",PhoneText_for_str);
                    startActivity(intent);
                }else{
                    Toast.makeText(ForgetActivity.this, "验证码输入错误", Toast.LENGTH_SHORT).show();
                }
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

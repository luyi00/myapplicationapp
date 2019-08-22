package com.example.administrator.myapplicationapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetActivity extends AppCompatActivity {
    private EditText editText ;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        editText = findViewById(R.id.editText_forget);
        button = findViewById(R.id.button_forget);

        //设置文本监听器
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(editText.getText().toString().trim().length()==11){
                    button.setEnabled(true);
                }else{
                    button.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送验证码置手机——通过点击html修改密码
                Toast.makeText(ForgetActivity.this, "短信已发送至手机", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}

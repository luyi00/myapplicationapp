package com.example.administrator.myapplicationapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ChangePasswordPage extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_page);
        //将返回键显示出来
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final EditText changePassword = findViewById(R.id.password_change);
        Button nextStep = findViewById(R.id.nextStep_change);
        final CheckBox checkPassword = findViewById(R.id.radio_change);
        //显示隐藏密码
        checkPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkPassword.isChecked()){
                    //如果选中，显示密码
                    changePassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //否则隐藏密码
                    changePassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //检查密码-----可增加密码的简易程度检测
                if(changePassword.getText().toString().trim().length()<6){
                    Toast.makeText(ChangePasswordPage.this, "输入密码需要大于6位小于16位", Toast.LENGTH_SHORT).show();
                }else{
                    //更新数据库并返回主页面

                }
            }
        });
    }
}

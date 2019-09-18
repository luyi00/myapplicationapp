package com.example.administrator.myapplicationapp;
//第三个fragment
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class ThirdFragment extends Fragment {
    private Button btneed,btgettask;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_third,null);
        btneed=(Button)v.findViewById(R.id.needs);
        btgettask=(Button)v.findViewById(R.id.gettask);
        btneed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到发布需求界面
            }
        });
        btgettask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //跳转到领取任务界面
            }
        });
        return v;
    }
}

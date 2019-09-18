package com.example.administrator.myapplicationapp;
//首页fragment
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {
    private Button sort,ftf,tea;
    private EditText et;
    private String[] title={"【易技能】安卓技能","【易技能】安卓技能","【易技能】安卓技能","【易技能】安卓技能"};
    private int[] pics={R.drawable.icon_medal_fill,R.drawable.camera,R.drawable.home,R.drawable.icon_message};
    private List<String> list;
    private GridView gridviewfirst;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first, null);
        et=(EditText)v.findViewById(R.id.et);
        sort=(Button)v.findViewById(R.id.bt_sort);  //分类按钮
        ftf=(Button)v.findViewById(R.id.bt_ftf);    //面对面教学按钮
        tea=(Button)v.findViewById(R.id.bt_tea);    //实时教学按钮
        et.clearFocus();
        list=new ArrayList<>();
        for(int i=0;i<title.length;i++){
            list.add(title[i]);
        }
        gridviewfirst=(GridView)v.findViewById(R.id.gridfirst);
        CustomGridBaseAdapter c=new CustomGridBaseAdapter();
        gridviewfirst.setAdapter(c);
        gridviewfirst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //点击视频item后跳转到登入界面
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }
    private class CustomGridBaseAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater ll=getLayoutInflater();
            View v=ll.inflate(R.layout.grid_item,null);
            ImageView iv=(ImageView)v.findViewById(R.id.grid_item_iv);
            iv.setImageResource(pics[i]);
            TextView tv=(TextView)v.findViewById(R.id.grid_item_tv);
            tv.setText(list.get(i).toString());
            return v;
        }
    }
}

package com.example.administrator.myapplicationapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BufferPage extends AppCompatActivity {
    private ListView couponlist;
    private String[] title={"正在缓存","xx达人针对xx技能讲解"};
    private int[] imageid={R.drawable.video_ex,R.drawable.video_ex};
    List<HashMap<String, Object>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bufferpage);
        ActionBar actionBar = getSupportActionBar();
        //添加返回按钮
        actionBar.setDisplayHomeAsUpEnabled(true);
        couponlist=(ListView)findViewById(R.id.buffer_list);

        data=new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<title.length;i++){
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("ITEMTV1",imageid[i]);
            hashMap.put("ITEMTV2",title[i]);
            data.add(hashMap);
        }
        SimpleAdapter adapter=new SimpleAdapter(getApplicationContext(),data,R.layout.buffer_list_detail,new String[]{
                "ITEMTV1","ITEMTV2"},new int[]{R.id.buffer_itemimage01,R.id.buffer_itemtitle});
        couponlist.setAdapter(adapter);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //重写ToolBar返回按钮的行为，关闭此Activity
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

package com.example.administrator.myapplicationapp;
//更多浏览界面，与收藏界面相同
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RecordPage extends AppCompatActivity {

    private ListView listrecord;
    private String[] title={"动态配字描述","****"};
    private String[] names={"作者用户名","***"};
    private int[] imageid01={R.drawable.icon_medal_fill,R.drawable.icon_medal_fill};
    private int[] imageid02={R.drawable.icon_people_fill,R.drawable.icon_people_fill};
    List<HashMap<String, Object>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recordpage);

        ActionBar actionBar = getSupportActionBar();
        //添加返回按钮
        actionBar.setDisplayHomeAsUpEnabled(true);
        listrecord=(ListView)findViewById(R.id.record_list);
        data=new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<title.length;i++){
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("ITEMTV1",imageid01[i]);
            hashMap.put("ITEMTV2",imageid02[i]);
            hashMap.put("ITEMTV3",title[i]);
            hashMap.put("ITEMTV4",names[i]);
            data.add(hashMap);
        }
        SimpleAdapter adapter=new SimpleAdapter(this,data,R.layout.collect_all_detail,new String[]{
                "ITEMTV1","ITEMTV2","ITEMTV3","ITEMTV4"},new int[]{R.id.itemimage01,R.id.itemimage02,R.id.itemtitle,R.id.itemdetail});
        listrecord.setAdapter(adapter);


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

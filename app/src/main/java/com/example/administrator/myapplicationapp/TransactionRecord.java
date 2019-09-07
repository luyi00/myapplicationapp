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

public class TransactionRecord extends AppCompatActivity {
    private ListView listtransaction;
    private String[] title_thing={"支付给*****","转账-****"};
    private String[] time={"2019年1月1日","2019年1月1日"};
    private int[] headimages={R.drawable.icon_people_fill,R.drawable.icon_people_fill};
    private String[] money={"-8元","+6元"};
    List<HashMap<String, Object>> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transactionrecord);
        ActionBar actionBar = getSupportActionBar();
        //添加返回按钮
        actionBar.setDisplayHomeAsUpEnabled(true);
        listtransaction = (ListView) findViewById(R.id.transaction_list);
        data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < title_thing.length; i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("ITEMTV1", headimages[i]);
            hashMap.put("ITEMTV2", title_thing[i]);
            hashMap.put("ITEMTV3", money[i]);
            hashMap.put("ITEMTV4", time[i]);
            data.add(hashMap);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.transaction_detail, new String[]{
                "ITEMTV1", "ITEMTV2", "ITEMTV3" ,"ITEMTV4"}, new int[]{R.id.transactionimage_head, R.id.transaction_itemtitle, R.id.money,R.id.time});
        listtransaction.setAdapter(adapter);

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

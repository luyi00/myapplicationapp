package com.example.administrator.myapplicationapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Transaction extends Fragment {
    private ListView listtransaction;
    private String[] title_thing={"支付给*****","转账-****"};
    private String[] time={"2019年1月1日","2019年1月1日"};
    private int[] headimages={R.drawable.icon_people_fill,R.drawable.icon_people_fill};
    private String[] money={"-8元","+6元"};
    List<HashMap<String, Object>> data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.transaction, container, false);
        listtransaction = (ListView)v.findViewById(R.id.transaction_list);
        data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < title_thing.length; i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("ITEMTV1", headimages[i]);
            hashMap.put("ITEMTV2", title_thing[i]);
            hashMap.put("ITEMTV3", money[i]);
            hashMap.put("ITEMTV4", time[i]);
            data.add(hashMap);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), data, R.layout.transaction_detail, new String[]{
                "ITEMTV1", "ITEMTV2", "ITEMTV3" ,"ITEMTV4"}, new int[]{R.id.transactionimage_head, R.id.transaction_itemtitle, R.id.money,R.id.time});
        listtransaction.setAdapter(adapter);
        return v;
    }

}

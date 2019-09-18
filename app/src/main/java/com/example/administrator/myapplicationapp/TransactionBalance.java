package com.example.administrator.myapplicationapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TransactionBalance extends Fragment {
    private TextView tv_title,tv_money;
    private ListView lv;
    private String[] s={"充值","提现"};
    List<HashMap<String, Object>> data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.transactionbalance, container, false);
        String money="0.00";
        tv_title=(TextView)v.findViewById(R.id.balance_title);
        tv_money=(TextView)v.findViewById(R.id.balance);     //用户余额
        tv_money.setText(money);
        lv=(ListView)v.findViewById(R.id.balance_list);
        data=new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<s.length;i++){
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("ITEMTV1",s[i]);
            data.add(hashMap);
        }
        SimpleAdapter adapter=new SimpleAdapter(getActivity(),data,R.layout.balancelist,new String[]{
                "ITEMTV1"},new int[]{R.id.balist_item});
        lv.setAdapter(adapter);
        return v;
    }
}

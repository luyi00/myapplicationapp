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

public class MessageFragment extends Fragment {
    private ListView listmessage;
    private String[] title={"*****","****"};
    private String[] days={"2019年1月1日","2019年1月1日"};
    private int[] headimageid01={R.drawable.icon_people_fill,R.drawable.icon_people_fill};
    List<HashMap<String, Object>> data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.messagefragment01, container, false);
        listmessage=(ListView)v.findViewById(R.id.message_lstmenu);
        data=new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<title.length;i++){
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("ITEMTV1",headimageid01[i]);
            hashMap.put("ITEMTV2",title[i]);
            hashMap.put("ITEMTV3",days[i]);
            data.add(hashMap);
        }
        SimpleAdapter adapter=new SimpleAdapter(getActivity(),data,R.layout.message_detail,new String[]{
                "ITEMTV1","ITEMTV2","ITEMTV3"},new int[]{R.id.itemimage_head,R.id.message_itemtitle,R.id.message_itemdetail});
        listmessage.setAdapter(adapter);
        return v;
    }
}

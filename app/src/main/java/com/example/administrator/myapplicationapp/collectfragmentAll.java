package com.example.administrator.myapplicationapp;
//收藏界面中的全部fragment
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

public class collectfragmentAll extends Fragment {
    private ListView listall;
    private String[] title={"动态配字描述","****"};
    private String[] names={"作者用户名","***"};
    private int[] imageid01={R.drawable.icon_medal_fill,R.drawable.icon_medal_fill};
    private int[] imageid02={R.drawable.icon_people_fill,R.drawable.icon_people_fill};
    List<HashMap<String, Object>> data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.collectfragment01, container, false);
        listall=(ListView)v.findViewById(R.id.lstmenu);
        data=new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<title.length;i++){
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("ITEMTV1",imageid01[i]);
            hashMap.put("ITEMTV2",imageid02[i]);
            hashMap.put("ITEMTV3",title[i]);
            hashMap.put("ITEMTV4",names[i]);
            data.add(hashMap);
        }
        SimpleAdapter adapter=new SimpleAdapter(getActivity(),data,R.layout.collect_all_detail,new String[]{
                "ITEMTV1","ITEMTV2","ITEMTV3","ITEMTV4"},new int[]{R.id.itemimage01,R.id.itemimage02,R.id.itemtitle,R.id.itemdetail});
        listall.setAdapter(adapter);
        return v;
    }
}

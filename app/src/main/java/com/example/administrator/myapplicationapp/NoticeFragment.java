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

public class NoticeFragment extends Fragment {
    private ListView listnotice;
    private String[] title_notice={"*****","****"};
    private String[] days={"2019年1月1日","2019年1月1日"};
    private int[] headimageid01={R.drawable.icon_people_fill,R.drawable.icon_people_fill};
    private String[] notice={"***************************************************************","*************"};
    List<HashMap<String, Object>> data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.messagefragment02, container, false);
        listnotice = (ListView) v.findViewById(R.id.notice_lstmenu);
        data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < title_notice.length; i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("ITEMTV1", headimageid01[i]);
            hashMap.put("ITEMTV2", title_notice[i]);
            hashMap.put("ITEMTV3", days[i]);
            hashMap.put("ITEMTV4", notice[i]);
            data.add(hashMap);
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), data, R.layout.notice_detail, new String[]{
                "ITEMTV1", "ITEMTV2", "ITEMTV3" ,"ITEMTV4"}, new int[]{R.id.noticeitemimage_head, R.id.notice_itemtitle, R.id.notice_itemdate,R.id.notice});
        listnotice.setAdapter(adapter);
        return v;
    }
}

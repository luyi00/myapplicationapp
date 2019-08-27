package com.example.administrator.myapplicationapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BufferPage extends AppCompatActivity {
    private ListView bufferlist;
    private TextView editTv,choosemessage;
    private CheckBox check;
    private Button btn_all,btn_del;
    private LinearLayout ll; //底部menu

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
        bufferlist=(ListView)findViewById(R.id.buffer_list);
        btn_all=(Button)findViewById(R.id.btn_all);
        btn_del=(Button)findViewById(R.id.btn_del);
        ll=(LinearLayout)findViewById(R.id.bottom_menu);
        choosemessage=(TextView)findViewById(R.id.choose_message);  //设置功能，随用户选择的视频改变数据

        data=new ArrayList<HashMap<String, Object>>();
        for(int i=0;i<title.length;i++){
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("ITEMTV1",imageid[i]);
            hashMap.put("ITEMTV2",title[i]);
            data.add(hashMap);
        }
        SimpleAdapter adapter=new SimpleAdapter(getApplicationContext(),data,R.layout.buffer_list_detail,new String[]{
                "ITEMTV1","ITEMTV2"},new int[]{R.id.buffer_itemimage01,R.id.buffer_itemtitle});
        bufferlist.setAdapter(adapter);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //重写ToolBar返回按钮的行为，关闭此Activity
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_edit:
                if (item.getTitle().toString().equals("取消")) {
                    item.setTitle("编辑");
                    ll.setVisibility(View.GONE);
                    choosemessage.setVisibility(View.GONE);
                    for(int i=0;i<title.length;i++) {
                        bufferlist.getChildAt(i).findViewById(R.id.check).setVisibility(View.INVISIBLE);
                    }
                } else {
                    item.setTitle("取消");
                    ll.setVisibility(View.VISIBLE);
                    choosemessage.setVisibility(View.VISIBLE);
                    for(int i=0;i<title.length;i++) {
                        bufferlist.getChildAt(i).findViewById(R.id.check).setVisibility(View.VISIBLE);
                    }

                    //底部全部按钮点击事件
                    btn_all.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(),"选择全部",Toast.LENGTH_LONG).show();
                        }
                    });
                    //下载缓存删除事件
                    btn_del.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(),"删除事件",Toast.LENGTH_LONG).show();
                        }
                    });
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        /*super.onCreateOptionsMenu(menu);
        //添加菜单项
        MenuItem add=menu.add(0,0,0,"编辑");
        //绑定到ActionBar
        add.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;*/
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}

package com.example.administrator.myapplicationapp;
//设置界面
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplicationapp.db.DataBase;
import com.example.administrator.myapplicationapp.db.UserInformation;

import org.w3c.dom.Text;

public class SetPage extends BaseActivity {
    private static final String TAG = "SetPage";
    private TextView tvname,tvsex,tvbirth,tvintro,person,head,tvaboutapp,tvexit;
    private EditText etname,etsex,etbirth,etintro;
    private ImageView head_img;
    //用户信息
    public UserInformation uf = null;
    //信息保存
    private SharedPreferences pref = null;
    //数据库
    private DataBase dbHelper;
    //信息存在标记
    private boolean exist = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setpage);
        ActionBar actionBar = getSupportActionBar();
        //添加返回按钮
        actionBar.setDisplayHomeAsUpEnabled(true);
        //加载控件
        person=(TextView)findViewById(R.id.person);
        head=(TextView)findViewById(R.id.head);
        tvname=(TextView)findViewById(R.id.tv_name);
        tvsex=(TextView)findViewById(R.id.tv_sex);
        tvbirth=(TextView)findViewById(R.id.tv_birth);
        tvintro=(TextView)findViewById(R.id.tv_intro);
        tvaboutapp=(TextView)findViewById(R.id.tv_aboutapp);
        tvexit=(TextView)findViewById(R.id.tv_exit);
        etname=(EditText)findViewById(R.id.et_name);
        etsex=(EditText)findViewById(R.id.et_sex);
        etbirth=(EditText)findViewById(R.id.et_birth);
        etintro=(EditText)findViewById(R.id.et_intro);
        head_img=(ImageView) findViewById(R.id.head_img);
        head_img.setImageResource(R.drawable.icon_people_fill);
        //加载用户信息
        pref = getSharedPreferences("userData",MODE_PRIVATE);
        uf = new UserInformation(pref.getString("userPhone",""));
        //调用数据库加载信息
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("personalMessage",null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String user_phone = cursor.getString(cursor.getColumnIndex("user_phone"));
                if(user_phone.equals(uf.getUserPhone())){
                    cursor.close();
                    exist = true;
                    String head_image = cursor.getString(cursor.getColumnIndex("head_image"));
                    String user_name = cursor.getString(cursor.getColumnIndex("user_name"));
                    String user_sex = cursor.getString(cursor.getColumnIndex("user_sex"));
                    String user_birthday = cursor.getString(cursor.getColumnIndex("user_birthday"));
                    String user_introduce = cursor.getString(cursor.getColumnIndex("user_introduce"));
                    if(head_image!=null){
                        Bitmap bitmap = BitmapFactory.decodeFile(head_image);
                        head_img.setImageBitmap(bitmap);
                    }
                    if(user_name!=null){etname.setText(user_name);}
                    if(user_sex!=null){etsex.setText(user_sex);}
                    if(user_birthday!=null){etbirth.setText(user_birthday);}
                    if(user_introduce!=null){etintro.setText(user_introduce);}
                }
            }while(cursor.moveToNext());
            cursor.close();
        }
        //跳转到关于app界面
        tvaboutapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SetPage.this,AboutApp.class);
                startActivity(intent);
            }
        });
        //退出登入
        tvexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* @setIcon 设置对话框图标
                 * @setTitle 设置对话框标题
                 * @setMessage 设置对话框消息提示
                 * setXXX方法返回Dialog对象，因此可以链式设置属性
                 */
                final AlertDialog.Builder normalDialog = new AlertDialog.Builder(SetPage.this);
                normalDialog.setMessage("哦吼QAQ 乃确定不是手滑了吗？");
                normalDialog.setPositiveButton("注销",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //删除用户信息
                                SharedPreferences sharedPreferences = getSharedPreferences("userData", MODE_PRIVATE);
                                sharedPreferences.edit().clear().apply();
                                //结束
                                finish();
                            }
                        });
                normalDialog.setNegativeButton("我手滑了",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //不操作
                            }
                        });
                // 显示
                normalDialog.show();
            }
        });
        //点击更换头像
        head_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //更改头像
                Toast.makeText(getApplicationContext(),"更换头像",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //保存信息
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        if(exist){
            //更新信息
            //将数据添加至ContentValues
            ContentValues values = new ContentValues();
            values.put("head_image",uf.getUserImagePath());
            values.put("user_name",etname.getText().toString());
            values.put("user_sex",etsex.getText().toString());
            values.put("user_birthday",etbirth.getText().toString());
            values.put("user_introduce",etbirth.getText().toString());
            //修改model的数据
            db.update("personalMessage",values,"user_phone=?",new String[]{uf.getUserPhone()});
            Log.e(TAG, "onDestroy: UpData SUCCESS" );
        }else{
            //保存信息
            ContentValues values = new ContentValues();
            values.put("user_phone",uf.getUserPhone());
            values.put("head_image",uf.getUserImagePath());
            values.put("user_name",etname.getText().toString());
            values.put("user_sex",etsex.getText().toString());
            values.put("user_birthday",etbirth.getText().toString());
            values.put("user_introduce",etbirth.getText().toString());
            db.insert("personalMessage",null,values);
            Log.e(TAG, "onDestroy: CreateData SUCCESS" );
        }
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

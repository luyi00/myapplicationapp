package com.example.administrator.myapplicationapp;
//第四个fragment
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class ForthFragment extends Fragment {
    private ImageButton btn_setting,btn_message,btn_camera,record01,record02,record03,buffer01,buffer02,buffer03;
    private ImageView headimage,backgroundimage;
    private TextView number01,number01_under,number02,number02_under,number03,number03_under,records,buffer;
    private Button records_more,buffer_more;

    public static final int TAKE_PHOTO=1;
    private Uri imageUri;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_forth,null);
        backgroundimage=(ImageView)v.findViewById(R.id.background_image);
        backgroundimage.setAlpha(0.3f); //设置透明度，功能完善后可删除
        btn_setting=(ImageButton)v.findViewById(R.id.btn_setting);
        btn_message=(ImageButton)v.findViewById(R.id.btn_message);
        btn_camera=(ImageButton)v.findViewById(R.id.camera);//照相机按钮
        btn_setting.setImageResource(R.drawable.icon_setting_fill);//顶部设置按钮
        btn_message.setImageResource(R.drawable.comment);//顶部消息键
        btn_camera.setImageResource(R.drawable.camera);
        headimage=(ImageView)v.findViewById(R.id.head_image);
        headimage.setImageResource(R.drawable.icon_people_fill0);//头像
        number01=(TextView)v.findViewById(R.id.number01);
        number02=(TextView)v.findViewById(R.id.number02);
        number03=(TextView)v.findViewById(R.id.number03);
        number01_under=(TextView)v.findViewById(R.id.number01uder);//后台连接实时更新收藏数量的数据
        number02_under=(TextView)v.findViewById(R.id.number02uder);//后台连接实时更新信用值数据
        number03_under=(TextView)v.findViewById(R.id.number03uder);//后台实时更新交易记录数据
        records=(TextView)v.findViewById(R.id.records);
        records_more=(Button)v.findViewById(R.id.records_more);
        buffer_more=(Button)v.findViewById(R.id.buffer_more);
        //浏览记录和缓存视频ImagButton形式  需后台数据获取等方法
        record01=(ImageButton)v.findViewById(R.id.record01);
        record01.setImageResource(R.drawable.video_ex);
        record02=(ImageButton)v.findViewById(R.id.record02);
        record02.setImageResource(R.drawable.video_ex);
        record03=(ImageButton)v.findViewById(R.id.record03);
        record03.setImageResource(R.drawable.video_ex);
        buffer01=(ImageButton)v.findViewById(R.id.buffer01);
        buffer01.setImageResource(R.drawable.video_ex);
        buffer02=(ImageButton)v.findViewById(R.id.buffer02);
        buffer02.setImageResource(R.drawable.video_ex);
        buffer03=(ImageButton)v.findViewById(R.id.buffer03);
        buffer03.setImageResource(R.drawable.video_ex);

        //点击事件
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),SetPage.class);
                startActivity(intent);
            }
        });
        btn_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"查看消息",Toast.LENGTH_LONG).show();
            }
        });
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getActivity(),"相机",Toast.LENGTH_LONG).show();
                //创建File对象，用于存储拍照后的图片
                File outputImage =new File(Environment.getExternalStorageDirectory(),"head_image.ipg");
                try{
                    if(outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT>=24){
                    imageUri = FileProvider.getUriForFile(getActivity(),"com.example.administrator.myapplicationapp.fileprovider",outputImage);
                }else{
                    imageUri=Uri.fromFile(outputImage);
                }
                //启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,TAKE_PHOTO);
            }
        });

        //点击更换头像
        headimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"点击更换头像",Toast.LENGTH_LONG).show();
            }
        });
        //点击更改背景图片
        backgroundimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"更改背景图片",Toast.LENGTH_LONG).show();
            }
        });
        //查看更多
        records_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"更多记录",Toast.LENGTH_LONG).show();
            }
        });
        //查看更多下载缓存
        buffer_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),BufferPage.class);
                startActivity(intent);
            }
        });

        //收藏数据点击跳转至收藏界面
        number01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),CollectActivity.class);
                startActivity(intent);
            }
        });
        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO: {
               //显示照片

            }
            break;
            default:
        }
    }
}

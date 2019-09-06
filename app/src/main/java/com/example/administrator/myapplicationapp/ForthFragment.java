package com.example.administrator.myapplicationapp;
//第四个fragment
import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class ForthFragment extends Fragment {
    private ImageButton btn_setting,btn_message,btn_camera,record01,record02,record03,buffer01,buffer02,buffer03;
    private ImageView headimage,backgroundimage,backmid;
    private TextView number01,number01_under,number02,number02_under,number03,number03_under,records,buffer;
    private Button records_more,buffer_more;
    //相册与相机
    public static final int TAKE_PHOTO=1;
    public static final int CHOOSE_PHOTO=2;
    //回调代码
    private static final int OPEN_ALBUM=1;
    private static final int OPEN_CANMER=2;
    //图片地址
    private Uri imageUri;

    //用户信息
    private String userAccount=null;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_forth,null);
        backgroundimage=(ImageView)v.findViewById(R.id.background_image);
        backgroundimage.setAlpha(0.3f); //设置透明度，功能完善后可删除
        backmid=(ImageView)v.findViewById(R.id.back_mid);//信用积分的背景
        backmid.setImageResource(R.drawable.gray_bg);
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
        //获取用户信息


        //点击事件
        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //先判断是否登陆
                if(userAccount==null){
                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent=new Intent(getActivity(),SetPage.class);
                    startActivity(intent);
                }
            }
        });
        btn_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),MessageCheck.class);
                startActivity(intent);
            }
        });
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getActivity(),"相机",Toast.LENGTH_LONG).show();
                //启动相机和相册程序
                openPicture();
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
    //打开相机或相册
    private void openPicture() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("请选择");
        builder.setItems(new String[]{"相机", "相册"}, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        //相机
                        if (Build.VERSION.SDK_INT >= 23) {
                            int checkCallPhonePermission = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA);
                            if(checkCallPhonePermission != PackageManager.PERMISSION_GRANTED){
                                ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.CAMERA},OPEN_CANMER);
                                return;
                            }else{
                                showCamera();
                            }
                        } else {
                            showCamera();
                        }
                        break;
                    case 1:
                        //相册
                        if(ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
                            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},OPEN_ALBUM);
                        }else{
                            openAlbum();
                        }
                        break;
                }

            }
        });
        builder.create().show();
    }
    //相机
    public void showCamera(){
        //创建File对象，用于存储拍照后的图片
        File dir = new File(Environment.getExternalStorageDirectory(), "image");//在sd下创建文件夹myimage；Environment.getExternalStorageDirectory()得到SD卡路径文件
        if (!dir.exists()) {    //exists()判断文件是否存在，不存在则创建文件
            dir.mkdirs();
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");   //设置日期格式在android中，创建文件时，文件名中不能包含“：”冒号
        String filename = df.format(new Date());
        File outputImage =new File(dir, filename + ".jpg");
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
        Intent intent_camera = new Intent("android.media.action.IMAGE_CAPTURE");
        intent_camera.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        startActivityForResult(intent_camera, TAKE_PHOTO);
    }
    //相册
    public void openAlbum(){
        Intent intent_album = new Intent(Intent.ACTION_PICK);
        intent_album.setType("image/*");
        startActivityForResult(intent_album,CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case OPEN_ALBUM:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else{
                    Toast.makeText(getContext(), "权限未开启", Toast.LENGTH_SHORT).show();
                }
                break;
            case OPEN_CANMER:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showCamera();
                } else {
                    Toast.makeText(getContext(), "相机权限禁用了。请务必开启相机权", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO: {
               //显示照片
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContext().getContentResolver().openInputStream(imageUri));
                    headimage.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            break;
            case CHOOSE_PHOTO:{
                if(Build.VERSION.SDK_INT>=19){
                    //4.4及其以上系统使用该方法处理图片
                    handleImageOnKitKat(data);
                }else{
                    handleImageBeforeKitKat(data);
                }
            }
            break;
            default:
        }
    }
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(getContext(),uri)){
            //如果是document类型的uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID+"="+id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath = getImagePath(contentUri,null);
            }else if("content".equalsIgnoreCase(uri.getScheme())){
                imagePath = getImagePath(uri,null);
            }else if("file".equalsIgnoreCase(uri.getScheme())){
                imagePath = uri.getPath();
            }
            displayImage(imagePath);
        }
    }
    private void handleImageBeforeKitKat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }
    private String getImagePath(Uri uri,String selection){
        String path = null;
        //通过uri和selection获取真实的图片路径
        Cursor cursor = getContext().getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    private void displayImage(String imagePath){
        if(imagePath!=null){
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            //显示照片
            headimage.setImageBitmap(bitmap);
        }else{
            Toast.makeText(getContext(), "加载照片失败", Toast.LENGTH_SHORT).show();
        }
    }
}

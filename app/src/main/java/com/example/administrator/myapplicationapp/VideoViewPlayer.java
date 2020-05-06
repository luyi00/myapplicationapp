package com.example.administrator.myapplicationapp;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import java.util.HashMap;

public class VideoViewPlayer extends AppCompatActivity {
    String url = null;
    RelativeLayout videoViewParent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view_player);
        videoViewParent = findViewById(R.id.videoLayout);
//        initView();
        Thread ThreadPoolUtils = new Thread(new Runnable() {
            @Override
            public void run() {
                MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                Bitmap bitmap = null;
                try {
                    //这里要用FileProvider获取的Uri
                    if (url.contains("http")) {
                        retriever.setDataSource(url, new HashMap<String, String>());
                    } else {
                        retriever.setDataSource(url);
                    }
                    bitmap = retriever.getFrameAtTime();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    try {
                        retriever.release();
                    } catch (RuntimeException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        ThreadPoolUtils.start();

    }

    /**
     * 香港卫视：http://live.hkstv.hk.lxdns.com/live/hks/playlist.m3u8
     * CCTV1高清：http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8
     * CCTV3高清：http://ivi.bupt.edu.cn/hls/cctv3hd.m3u8
     * CCTV5高清：http://ivi.bupt.edu.cn/hls/cctv5hd.m3u8
     * CCTV5+高清：http://ivi.bupt.edu.cn/hls/cctv5phd.m3u8
     * CCTV6高清：http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8
     * 苹果提供的测试源（点播）：http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/gear2/prog_index.m3u8
     */
//    private void initView() {
//        url="http://ivi.bupt.edu.cn/hls/cctv6hd.m3u8";
//        final CustomVideoView videoView=(CustomVideoView)findViewById(R.id.videoView);
//        videoView.setVideoPath(url);
//        videoView.requestFocus();
//        videoView.start();
//        videoViewParent.post(new Runnable() {
//            @Override
//            public void run() {
//                int[] widthAndHeight=getWidthAndHeight(holder.videoViewParent,dynamicsBean.getWeight(),dynamicsBean.getHeight());
//                videoView.getHolder().setFixedSize(widthAndHeight[0], widthAndHeight[1]);
//                // 重绘VideoView大小，这个方法是在重写VideoView时对外抛出方法
//                videoView.setMeasure(widthAndHeight[0], widthAndHeight[1]);
//                // 请求调整
//                videoView.requestLayout();
//            }
//        });
//    }
}

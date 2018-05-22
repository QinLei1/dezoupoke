package com.luosu.activitys;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.luosu.dezoupoke.R;
import com.luosu.utils.MyHttpUtils;

public class ImgaeActivity extends Activity {
    Button btn_download_img;
    ImageView img_download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imgae);

        btn_download_img = (Button) findViewById(R.id.btn_download_img);
        img_download = (ImageView) findViewById(R.id.img_download);

        btn_download_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyHttpUtils imgutils = new MyHttpUtils();
                String imgurl = "http://192.168.3.66:8030/MyTomCatTest/imgs/timg.jpg";

                imgutils.downlaodImg(imgurl, new MyHttpUtils.ImageResultListener() {


                    @Override
                    public void onImgResult(Bitmap result) {
                        img_download.setImageBitmap(result);
                    }
                });
            }
        });


    }
}

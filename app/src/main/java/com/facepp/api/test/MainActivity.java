package com.facepp.api.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.megvii.facepp.api.bean.Face;

import butterknife.BindView;
import butterknife.ButterKnife;



public class MainActivity extends AppCompatActivity {

    private Bitmap bitmap;
    private ImageView mImageView;

    @BindView(R.id.response)
    TextView txtResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.xiaofu);
//        BitmapUtil bitmapUtil = new BitmapUtil();
//        bitmap = bitmapUtil.createWithUrl("http://image1607.oss-cn-qingdao.aliyuncs.com/xiaofu.jpeg");
        mImageView = (ImageView) findViewById(R.id.imageView);
        mImageView.setImageBitmap(bitmap);
        FacesUtil facesUtil = new FacesUtil();
        FaceMakeUpUtils faceMakeUpUtils = new FaceMakeUpUtils();
        Face face = facesUtil.getFacesInfo("http://image1607.oss-cn-qingdao.aliyuncs.com/xiaofu.jpeg");
        bitmap = faceMakeUpUtils.mouthRendering(bitmap,face);
        bitmap = faceMakeUpUtils.drawEyePoint(bitmap,face);
        mImageView.setImageBitmap(bitmap);
    }
    private String refreshView(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txtResponse.setText(response);
            }
        });
        return txtResponse.getText().toString();
    }

}
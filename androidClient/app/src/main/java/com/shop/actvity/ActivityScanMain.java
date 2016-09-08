package com.shop.actvity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kobe.shop4.R;

/**
 * Created by kobe on 5/1/16.
 */
public class ActivityScanMain extends Activity {


    private final static int SCANNIN_GREQUEST_CODE = 1;
    /**
     * 显示文本
     */
    private TextView mTextView;
    /**
     * 显示图片的IMAGVIEW
     */
    private ImageView mImageView;

    private ImageView backImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_scan);


        mTextView = (TextView) findViewById(R.id.result);
        mImageView = (ImageView) findViewById(R.id.qrcode_bitmap);
        backImageView=(ImageView)findViewById(R.id.img_back_scan);//返回按钮

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//退出
            }
        });


        Button mButton = (Button) findViewById(R.id.button1);
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(ActivityScanMain.this, MipcaActivityCapture.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);//请求一个activity
                //回调出结果
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    //扫描返回设置
                   // mTextView.setText(bundle.getString("result"));

                    Bundle bundle1=new Bundle();
                    String result=bundle.getString("result");//获取结果

                    bundle1.putString(MainActivity.SEARCHINFO,result);

                    Intent intent=new Intent();
                    intent.putExtras(bundle1);//
                    intent.setClass(ActivityScanMain.this,ActivitySearch.class);
                    //Toast.makeText(ActivityScanMain.this,"扫描结果是"+result,Toast.LENGTH_SHORT).show();
                    startActivity(intent);


                    //
                    //mImageView.setImageBitmap((Bitmap) data.getParcelableExtra("bitmap"));
                }
                break;
        }
    }
}

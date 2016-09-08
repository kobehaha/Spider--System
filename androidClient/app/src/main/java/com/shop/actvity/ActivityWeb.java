package com.shop.actvity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.kobe.shop4.R;

/**
 * Created by kobe on 5/2/16.
 */
public class ActivityWeb extends Activity{


    private WebView webView;

    private String url;

    private ImageView imgItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_item);

        getUrl();//获取ITEM
        init();

    }

    public void init(){// 初始化

        imgItem=(ImageView)findViewById(R.id.img_back_item);
        imgItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        webView=(WebView) findViewById(R.id.web);

        WebSettings settings=webView.getSettings();//设置JAVASCRIPT支持

        settings.setJavaScriptEnabled(true);//设置WEB自适应
        settings.setSupportZoom(true);


        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);

        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//设置WEBVIEW缓存

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });


        webView.loadUrl(url);


    }


    public void getUrl(){
        url=getIntent().getExtras().getString(ActivitySearch.ITEMURL);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

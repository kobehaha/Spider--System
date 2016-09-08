package com.shop.actvity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bean.Shop;
import com.example.kobe.shop4.R;

import com.inter.SpiderGetInfo;
import com.service.SpiderService;
import com.util.AdapterSearch;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 搜索页面
 * Created by kobe on 5/1/16.
 */
public class ActivitySearch extends android.app.Activity {



    public static final String ITEMURL="ITEMUTL";


    private String search;

    private List<Shop> shops = new ArrayList<>();// 保存SHOP的LIST


    private AdapterSearch adapterSearch;

    private ListView listView;//设置LISTVIEW

    private SpiderGetInfo spiderGetInfo=new SpiderService();//设置接口获取服务


    private ImageView backImageView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {//创建ACTIVITY的时候需要做的
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);//绑定视图

        getInfo();//从上一个ACTIVITY获取搜索内容

        init();//加载方法


    }

    public void init() {

        listView = (ListView) findViewById(R.id.listview);

        backImageView=(ImageView)findViewById(R.id.img_back_search);

        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shops.clear();//清除数据
                finish();
            }
        });



//        for (int i=0;i<20;i++){
//            Shop shop=new Shop();
//            String a="http://g.hiphotos.baidu.com/baike/c0%3Dbaike80%2" +
//                    "C5%2C5%2C80%2C26/sign=dbf5e06ab5fb43160e12722841cd2d46/ca1349540923dd54b0d627c9d609b3de9d8248ee.jpg";
//
//            if (i<10){
//                shop.setFlags(1);
//                shop.setPrice("200");
//                shop.setName("篮球");
//                shop.setPicture(a);
//                shop.setShopDetailUrl("http://product.dangdang.com/1162503205.html");
//                shops.add(shop);
//            } else {
//                shop.setFlags(0);
//                shop.setPrice("300");
//                shop.setShopDetailUrl("https://www.amazon.cn/SPALDING-斯伯丁-PU材质-室内外兼用-比赛用篮球-橙色-74-604Y/dp/B00RF9IR18/ref=sr_1_1?ie=UTF8&qid=1462355041&sr=8-1&keywords=篮球");
//                shop.setName("足球");
//                shop.setPicture(a);
//                shops.add(shop);
//
//            }
//        }

        new AsynGetList().execute();// 获取listView



    }


    public void getInfo() {//

        try {
            search = (String) getIntent().getExtras().get(MainActivity.SEARCHINFO);
            Toast.makeText(this, "传递数据是" + search, Toast.LENGTH_SHORT).show();
        } catch (NullPointerException e) {
            Toast.makeText(this, "传递数据失败", Toast.LENGTH_SHORT).show();
        }


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public class AsynGetList extends AsyncTask<Void, Integer, Void> {//执行异步任务，和主线程UI分开


        private ProgressDialog progressDialog;



        public AsynGetList(){

            progressDialog=ProgressDialog.show(ActivitySearch.this,"正在加载","正在获取");

        }


        @Override
        protected void onPreExecute() {//预处理
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            progressDialog.dismiss();//dialog消失

            super.onPostExecute(aVoid);
            if (shops.size() > 0) {


                Toast.makeText(ActivitySearch.this,"shops size＝"+shops.size(),Toast.LENGTH_LONG).show();
                Toast.makeText(ActivitySearch.this,"获取信息成功",Toast.LENGTH_SHORT).show();
                adapterSearch = new AdapterSearch(shops, ActivitySearch.this);
                System.out.println("listSize ="+shops.size());


                listView.setAdapter(adapterSearch);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        Bundle bundle=new Bundle();
                        bundle.putString(ActivitySearch.ITEMURL,(String)shops.get(position).getShopDetailUrl());//传递URL过去

                        Intent intent=new Intent(ActivitySearch.this,ActivityWeb.class);

                        intent.putExtras(bundle);

                        startActivity(intent);

                       // Toast.makeText(ActivitySearch.this, "点击第" + position + "个", Toast.LENGTH_SHORT).show();
                    }
                });


            } else {

                Toast.makeText(ActivitySearch.this, "获取信息失败", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected Void doInBackground(Void... params) {//后台任务

            try{

                shops=spiderGetInfo.getList(search);

            }catch (Exception e){
                e.printStackTrace();

            }
            return null;

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressDialog.setProgress(values[0]);//设置更新的序列
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            shops.clear();//退出的时候，清除数据
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}

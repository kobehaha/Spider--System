package com.service;

import android.util.Log;

import com.bean.Shop;
import com.inter.SpiderGetInfo;
import com.util.Url;


import org.apache.http.params.CoreConnectionPNames;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**    实现接口的服务类
 * Created by kobe on 5/2/16.
 * 通过JSON获取数据
 *
 * android 4.1开始就不支持http client le
 */
public class SpiderService implements SpiderGetInfo {



    private JSONObject json_object=null;
    private String json;
    private  List<Shop> shops=new ArrayList<>();


    @Override
    public List<Shop> getList(String search) throws  Exception{//遇到抛出异常

        String url= Url.Url+search;
        URL url2=new URL(url);
        HttpURLConnection urlConnection=(HttpURLConnection) url2.openConnection();

        urlConnection.setRequestMethod("GET");//设置CONNECTION
        urlConnection.setReadTimeout(50000);
        urlConnection.setConnectTimeout(15000);






        //HttpClient client=new DefaultHttpClient();


       // HttpGet get=new HttpGet(url);
       // get.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
              //  1000);
        //get.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);

        //HttpResponse response=client.execute(get);




       // HttpEntity entity = response.getEntity();// get entity

        InputStream is = urlConnection.getInputStream();// 获取内容

        Log.v("test1","zheyiggele");



        BufferedReader reader = new BufferedReader(new InputStreamReader(is,
                "GBK"));// change stream to

        // reader

        StringBuilder sbBuilder = new StringBuilder();// a build cach for

        String line = null;

        while ((line = reader.readLine()) != null) {// read start
            sbBuilder.append(line);// add to sbBuilder

        }

        is.close();

        json = sbBuilder.toString();


        json_object = new JSONObject(json);


        JSONArray Jsonarray = json_object.getJSONArray("listShop");
        Log.v("test2",json_object.toString());


        for (int i = 0; i < Jsonarray.length(); i++) {

            Shop shop=new Shop();


            JSONObject jsonObject = Jsonarray.getJSONObject(i);

            String picture= jsonObject.getString("picture");

             String detailInfo=jsonObject.getString("shopDetailUrl");

            String price =jsonObject.getString("price");

            String name=jsonObject.getString("name");



            int flags=jsonObject.getInt("flags");


            shop.setFlags(flags);//设置每个数据

            shop.setPicture(picture);

            shop.setShopDetailUrl(detailInfo);

            shop.setPrice(price);

            shop.setName(name);//设置NAME

            shops.add(shop);//增加每个SHOPS


            System.out.println(shop.toString());



        }
        urlConnection.disconnect();//断开链接

        return shops;




    }
}

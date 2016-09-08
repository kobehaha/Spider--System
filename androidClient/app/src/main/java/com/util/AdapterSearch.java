package com.util;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bean.Shop;
import com.example.kobe.shop4.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by kobe on 5/1/16.
 */
public class AdapterSearch extends BaseAdapter {


    //设置适配器

    private List<Shop> shops;

    private Context context;//上下文对象

    private LayoutInflater inflater;//布局填充器

    private ImageLoad imageLoad;

    private ImageLoader loader;//加载图片加载类

    private DisplayImageOptions options;

    public AdapterSearch(List<Shop> shops, Context context) {//构造函数传递参数


        this.shops = shops;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoad = new ImageLoad((Activity) context, 5);
        loader = imageLoad.imageLoader;//加载图片加载类
        options = imageLoad.options;

    }

    @Override
    public int getCount() {
        return shops.size();
    }

    @Override
    public Object getItem(int position) {
        return shops.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {//内部一个方法匿名类，更佳的抽象

        class ViewHold {//设置一个缓冲的VIEWHOLD
            ImageView image;
            TextView tvShop, tvTtile, tvPrice;
        }
        ViewHold viewHold;
        if (convertView == null) {

            viewHold = new ViewHold();
            convertView = inflater.inflate(R.layout.item_search, parent, false);//填充整个布局

            viewHold.image = (ImageView) convertView.findViewById(R.id.img_shop);
            viewHold.tvPrice = (TextView) convertView.findViewById(R.id.tv_shop_price);
            viewHold.tvTtile = (TextView) convertView.findViewById(R.id.tv_shop_title);
            viewHold.tvShop = (TextView) convertView.findViewById(R.id.tv_shop_shop);
            convertView.setTag(viewHold);

        } else {
            viewHold = (ViewHold) convertView.getTag();
            loader.displayImage(shops.get(position).getPicture(), viewHold.image, options);//加载图片
            if (shops.get(position).getFlags() == 1) {
                viewHold.tvShop.setText("亚马逊");
            } else {
                viewHold.tvShop.setText("当当");
            }
            viewHold.tvTtile.setText(shops.get(position).getName());
            viewHold.tvPrice.setText(shops.get(position).getPrice());



            System.out.println("----------------------");


        }


        return convertView;
    }

}

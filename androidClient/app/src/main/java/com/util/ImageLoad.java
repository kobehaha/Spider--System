package com.util;

import android.app.Activity;

import com.example.kobe.shop4.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by kobe on 5/1/16.
 */
public class ImageLoad {
    /**
     * 加载缓存的类，可以缓存图片，
     */

    public static ImageLoader imageLoader;


    @SuppressWarnings("unused")
    public static DisplayImageOptions options;

    @SuppressWarnings("deprecation")


    public ImageLoad(Activity activity, int n) {//传入activity和加载线程的数量,第二个参数为圆的参数
        imageLoader = ImageLoader.getInstance();// 图片加载类
        // 图片加载设置类
        File cacheDir = StorageUtils.getOwnCacheDirectory(// 设置缓存
                activity, "imageloader/Cache");
        @SuppressWarnings("deprecation")
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(// 配置文件
                activity)
                .threadPoolSize(n)
                .// 线程池大小
                threadPriority(Thread.NORM_PRIORITY - 2)
                .// 线程优先级
                tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCache(new UnlimitedDiskCache(cacheDir))
                .denyCacheImageMultipleSizesInMemory().build();

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.display)
                .showImageOnFail(R.mipmap.display)
                .showImageForEmptyUri(R.mipmap.display).cacheOnDisc(true)
                // 设置缓存到sd，内存中
                .displayer(new RoundedBitmapDisplayer(0)).cacheInMemory(true)
                .considerExifParams(true).build();// 设置不同形态的图片
        imageLoader.init(config);

    }


}

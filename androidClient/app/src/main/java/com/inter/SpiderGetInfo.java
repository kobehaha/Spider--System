package com.inter;

import com.bean.Shop;

import java.util.List;

/**
 * Created by kobe on 5/2/16.
 */
public interface SpiderGetInfo {

    /**
     * 从网络上获取服务的接口
     * @return
     */

    List<Shop> getList(String search) throws Exception;

}

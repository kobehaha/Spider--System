package com.service;

import java.util.LinkedList;
import java.util.List;

import com.bean2.Shop;
import com.getInfo.SpiderAm;
import com.getInfo.SpiderAmInfo;
import com.getInfo.SpiderDD;
import com.getInfo.SpiderDDInfo;
import com.util2.Url;

public class ServiceGet {// 定义一个service 服务于所有的需求

	private String search;

	public List<Shop> shops = new LinkedList<>();// 最终返回的排序后的list

	public  List<Shop> shops2 = new LinkedList<>();

	private List<Shop> AmShops;// 亚马逊的shops

	private List<Shop> Ddshops;// 当当的shops
	
	private SpiderAmInfo amInfo=new SpiderAmInfo();//查询亚马逊的信息
	
	private SpiderDDInfo ddInfo=new SpiderDDInfo();

	public ServiceGet(String search) {

		this.search = search;

	}

	public List<Shop> getShop() {
		sort();
		return shops;

	}

	private void sort() {// 获取信息 ，范型

		Ddshops = getSpriderDDShopList(Url.DDurl1 + search + Url.DDurl2);
		AmShops = getSpiderAmShopList(Url.AMurl1 + search + Url.AMurl2 + search + Url.Amurl3);
		shops.addAll(AmShops);
		shops.addAll(Ddshops);
		//Collections.sort(shops);// shop实现了comparable接口

//		for (int i = 0; i < shops.size(); i++) {
//			Shop max = shops.get(i);
//			for (int j = 0; j < shops.size() - 1; j++) {
//				if (max.compareTo(shops.get(j + 1)) < 0) {
//					max = shops.get(j + 1);// 如果后一个比前一个大max移动位置
//
//				}
//			}
//            
//			shops2.add(max);
//
//		}

	}

	public static List<Shop> getSpiderAmShopList(String url) {// 获取亚马逊list主要信息
		return SpiderAm.getSpiderList(url);
	}

	public static List<Shop> getSpriderDDShopList(String url) {// 获取当当商品的主要信息

		return SpiderDD.getSpiderList(url);

	}

	public  Shop getAmdetailInfo(Shop shop) {
		

		return amInfo.getAmdetailInfo(shop);

	}
	
	
	public Shop getDddetailInfo(Shop shop){
		
		
		
		return ddInfo.getDDdetailInfo(shop);
	}
	
	
	

}

package com.getInfo;

import java.util.ArrayList;
import java.util.List;

import com.bean2.Shop;
import com.util2.StaticValue;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
/**
 * 
 * @author kobe
 * 该类是当当爬取数据的一个类，实现了pageprocessor接口
 * 爬取数据用的webmagic 解析数据用jsoup xpath
 */
public class SpiderDD implements PageProcessor{
	
	
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);//Site是爬取的设置
	
	public static List<Shop> shops=new ArrayList<>();//全局shop，返回结构
     
	/**
	 * 获取当当的商品list 
	 * @param url  请求的list
	 * @return list
	 */
	public static List<Shop> getSpiderList(String url) {
		
			Spider.create(new SpiderDD())
			.addUrl(url).thread(5)
			.run();
			
			
		return shops;
	}

	/**
	 * 获取某一件商品的详细信息
	 * @param shop
	 * @return list 
	 */
	public static Shop GetDetailInfo(Shop shop) {
		return null;
		
	}

	@Override
	public Site getSite() {//爬取数据
		
		return site;
	}

	@Override
	public void process(Page page) {//这是爬取信息的主要逻辑    xpath
	
		
	//	String[] pictures=new String[10];//保存的picture
		
		for (int i = 1; i < StaticValue.items; i++) {
			
			Shop shop=new Shop();
			System.out.println("-------------"+"第"+i+"件商品"+"-------------");
            
			System.out.println("页面的title " + page.getHtml().xpath("//title").toString());
            
			System.out.println("商品的price "
					+ page.getHtml().xpath("//li[@class='line"+i+"']/p[@class='price']/span/text()").toString());

			shop.setPrice(page.getHtml().xpath("//li[@class='line"+i+"']/p[@class='price']/span/text()").toString().replace("¥", ""));
			
			
			
			
			System.out.println(
					"商品的name " + page.getHtml().xpath("//li[@class='line"+i+"']/p[@class='name']/a/@title").toString());
			
            shop.setName(page.getHtml().xpath("//li[@class='line"+i+"']/p[@class='name']/a/@title").toString());
			
			System.out.println("商品的图片url " + page.getHtml().xpath("//li[@class='line"+i+"']/a/img/@src").toString());

			
			shop.setPicture(page.getHtml().xpath("//li[@class='line"+i+"']/a/img/@src").toString());
			
			System.out.println(
					"商品的图片评论次数 " + page.getHtml().xpath("//li[@class='line"+i+"']/p[@class='star']/a/text()").toString());
			
            shop.setComments(page.getHtml().xpath("//li[@class='line"+i+"']/p[@class='star']/a/text()").toString());
            
			System.out.println("商品的详情URL " + page.getHtml().xpath("//li[@class='line"+i+"']/a/@href").toString());
			
			shop.setShopDetailUrl(page.getHtml().xpath("//li[@class='line"+i+"']/a/@href").toString());
			
			
			if (page.getResultItems().get("name") == null) {

				
				page.setSkip(true);
			}
			
//			SpiderDDInfo info=new SpiderDDInfo(shop);
//			
//			Spider.create(info)//爬虫一次，获取详情信息  
//			.addUrl(shop.getShopDetailUrl()).thread(1)
//			.run();
//			
//			
//			shop=info.getDetailShop();//重新副职，完整的应用
//			
//			System.out.println("this shop info"+shop);
			
			shop.setFlags(0); //flag为 0 当当网
			
			shops.add(shop);
			
			
			

		}
		
	}

	
	
	

}

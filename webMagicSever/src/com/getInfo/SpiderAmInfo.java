package com.getInfo;

import com.bean2.Shop;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class SpiderAmInfo implements PageProcessor {

/**
 * 获取亚马逊某商品详细信息的爬虫
 */
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);//Site是爬取的设置
	
	
	
	public   Shop shop;
	
	
	public SpiderAmInfo(){
		
	}
	
	public SpiderAmInfo(Shop shop) {
		this.shop=shop;
		
	}
	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {
		
		String[] pictures=new String[10];//保存图片的picture
	
		String picture1=page.getHtml().xpath("//ul[@class='a-nostyle a-button-list a-vertical a-spacing-top-micro']/li[2]//img/@src").toString();
		
		String picture2=page.getHtml().xpath("//ul[@class='a-nostyle a-button-list a-vertical a-spacing-top-micro']/li[3]//img/@src").toString();
		
		
		
		System.out.println("第一张图片"+picture1);
		
		System.out.println("这是第2张图片"+picture2);
		
		pictures[0]=picture1;
		
		pictures[1]=picture2;
		
		//shop.setPicture(pictures);
		

	}
		public  Shop getAmdetailInfo(Shop shop2) {//传递shop 查询详细信息
		

		Spider.create(new SpiderAmInfo(shop2))// 爬虫一次，获取详情信息
				.addUrl(shop2.getShopDetailUrl()).thread(1).run();

		return shop;
	}

}

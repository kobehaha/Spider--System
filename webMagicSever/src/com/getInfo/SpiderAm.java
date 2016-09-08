package com.getInfo;

import java.util.ArrayList;
import java.util.List;

import com.bean2.Shop;
import com.sun.xml.internal.txw2.Document;
import com.util2.StaticValue;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class SpiderAm implements PageProcessor {
	/**
	 * 搜索页面上获取数据
	 * 
	 */
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);// Site是爬取的设置

	public static List<Shop> shops = new ArrayList<>();// 全局shop，返回结构

	/**
	 * @param url
	 * @return 返回获取到的list
	 */
	public static List<Shop> getSpiderList(String url) {

		Spider.create(new SpiderAm())// 开始爬取
				.addUrl(url).thread(5).run();

		return shops;
	}

	/**
	 * 
	 * @param shop
	 * @return
	 */
	public static Shop GetDeatilInfo(Shop shop) {
		return null;
	}

	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {// 爬取过程

		System.out.println("title " + page.getHtml().xpath("//title").toString());
				String item = "";
		

		for (int i = 0; i < StaticValue.items; i++) {
			Shop shop = new Shop();
			System.out.println("----------------第" + i + "个商品");
			item = "//li[@id='result_" + i + "']";// 统一定义第i个商品的item
			//li[@id='result_'+i+""]

			System.out.println("第" + i + "个商品的图片是" + page.getHtml().xpath(item + "//img/@src").toString());

	
			shop.setPicture(page.getHtml().xpath(item + "//img/@src").toString());

			System.out.println("第" + i + "个商品的name "
					+ page.getHtml().xpath(item + "//div[@class='a-row a-spacing-none']/a/@title").toString());

			shop.setName(page.getHtml().xpath(item + "//div[@class='a-row a-spacing-none']/a/@title").toString());

			System.out.println("第" + i + "个商品的价格=" + page.getHtml()
					.xpath(item + "//span" + "[@class='a-size-base a-color-price s-price a-text-bold']" + "/text()"));

			// 一定要进行价格的转换
			String price = page.getHtml()
					.xpath(item + "//span" + "[@class='a-size-base a-color-price s-price a-text-bold']" + "/text()")
					.toString();

			String length = price.replace("￥", "").replace(",", "");
			
		

			
			shop.setPrice(length);
			//int size = length.length();
			
//			System.out.println("length is " + length);
//
//			
//			shop.setPrice(length.substring(0, size - 3));//最重要的转换价格

			System.out.println("进入商品的url" + page.getHtml().xpath(
					item + "//div[@class='a-section a-spacing-none a-inline-block s-position-relative']/" + "a/@href")
					.toString());

			shop.setShopDetailUrl(page.getHtml().xpath(
					item + "//div[@class='a-section a-spacing-none a-inline-block s-position-relative']/" + "a/@href")
					.toString());
			;

//			

			shop.setFlags(1);// flag 为1 亚马逊

			shops.add(shop);

		}

	}

}

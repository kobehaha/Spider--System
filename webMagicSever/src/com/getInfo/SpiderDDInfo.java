package com.getInfo;

import java.util.ArrayList;



import com.bean2.Comment;
import com.bean2.Shop;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class SpiderDDInfo implements PageProcessor{//获取当当的具体详细信息每个产品的，这部分
	//涉及了每条评论，获取详情图片，已经好评，中评，差评的数量
	
	
	
	private Site site = Site.me().setRetryTimes(3).setSleepTime(100);//Site是爬取的设置
	
	public   Shop shop;
	
	public String[] pictures=new String[4];
	
	public ArrayList<Comment> comments=	new ArrayList<>();
	
	
	public SpiderDDInfo(){
		
	}
	

	public SpiderDDInfo(Shop shop1) {
		this.shop=shop1;
	}

	@Override
	public Site getSite() {
		return site;
	}

	@Override
	public void process(Page page) {//爬取 注意爬取当当网的每张图片是通过下载每个的第一张图，然后在截取字符串获取的其他图片
		
//		System.out.println("picture "+page.getHtml().xpath("//ul[@class='pic_list clearfix']/li/a/img/@src").toString());
		
		String picture1=page.getHtml().xpath("//ul[@class='pic_list clearfix']/li/a/img/@src").toString();
		//获取的第一张图片
		//get index
		int index=picture1.indexOf("x");
		if (index>0) {
			for(int i=1;i<pictures.length;i++){
				int b=i;
				int c=i-1;
				pictures[i-1]=picture1.replaceAll("-1", "-"+b);
				System.out.println("picutre"+c+" "+pictures[i-1]);
			//	shop.setPicture(pictures);
				
			}
			//测试
//			
//			for (int i = 0; i < strings.length; i++) {
//				
//				System.out.println("获取每张图片"+strings[i]);
//				
//			}
		//	shop.setPicture(pictures);//增加图片
		}else {
			System.out.println("只有一张图片");
		}
		
		
		//String username=page.getHtml().xpath("//ul[@class='pic_list clearfix']/li/a/img/@src").toString();
		//获取好评，差评，中评的数量
		
//		System.out.println("好评数量是"+page.getHtml().xpath("//a[@id='type_2']/text()").toString());
//		System.out.println("中评数量是"+page.getHtml().xpath("//a[@id='type_3']/text()").toString());
//		System.out.println("差评数量是"+page.getHtml().xpath("//a[@id='type_4']/text()").toString());
//
//		
//		
//		
//		String userPicture=page.getHtml().xpath("//div[@class='item_wrap']/div[@class='comment_items clearfix']/div[1]/items_left/div[@class='items_left_pic']/img/@src/text()").toString();
//		
//		System.out.println("用户照片url"+userPicture);
//		
//		String userName=page.getHtml().xpath("//div[@class='item_wrap']/div[@class='pcomment_items clearfix']/div[@class='[items_left'"
//				+ "]/div[@class='items_left_name']/text()"
//				).toString();
//		
//		System.out.println("用户名称"+userName);
//		
		
		
		
	}	
	

	


	public Shop getDDdetailInfo(Shop shop2) {//获取详信息
		
		
		
		Spider.create(new SpiderDDInfo())//爬虫一次，获取详情信息  
		.addUrl(shop.getShopDetailUrl()).thread(1)
		.run();
		
		return shop;
	}
	
	
	
	
	
	
	

}

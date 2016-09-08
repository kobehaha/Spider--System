package com.bean2;

import java.util.List;

public  class Shop implements Comparable<Shop>{
//implements Comparable<Shop>{//商品信息    //实现比较器，比较
	
	
	 
	
	public static String goodComment;//好评
	
	public static String midComment;//中等
	
	public static String downComment;//差评
	
	private String  commentUrl;//评论的url
	
	private String shopDetailUrl;//商品详情的url
	
	
	private String price;//价格    //显示比较器方法,因为当当的价格标识为$100, 所以需要除符号
	
	private String  comments;//评论数,这里设置为string 防止文本
	
	private String name; //类别
	
	private List<Comment> comentsContent;//评论的信息  范型编程
	
	private int flags;//这是标识是京东还是当当网的商品，当flag=1时候是亚马逊，flag＝0时候，是当当
	
	private String picture;//图片
	
	
	public String getShopDetailUrl() {
		return shopDetailUrl;
	}

	public void setShopDetailUrl(String shopDetailUrl) {
		this.shopDetailUrl = shopDetailUrl;
	}

	
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Comment> getComentsContent() {
		return comentsContent;
	}

	public void setComentsContent(List<Comment> comentsContent) {
		this.comentsContent = comentsContent;
	}

	public int getFlags() {
		return flags;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}


	public String getCommentUrl() {
		return commentUrl;
	}

	public void setCommentUrl(String commentUrl) {
		this.commentUrl = commentUrl;
	}

//	@Override
//	public int compareTo(Shop o) {//显示比较器方法,因为当当的价格标识为$100, 亚马逊是￥6,568.00直接人命币，前面已经去掉了
//		if (Double.valueOf(this.getPrice())>=Double.valueOf(o.getPrice())) {//比较
//			return 1;
//		}else {
//			return 0;
//		}
//	
//				
//		
//	}
	
	@Override
	public String toString(){//获取详情
		return "this shop is from "+flags+" price="+price+" titile="+name+" "
			//	+ "comments="+String.valueOf(comments)+
				+" first picture="+picture+" shopDetailUrl="+shopDetailUrl+
//				" first ComentContent="+
//				comentsContent.get(0).toString()+
				//" goodComment="+goodComment+" midComment="+midComment+
				" downComment="+downComment;
	}

@Override
public int compareTo(Shop o) {
	

	
	return 0;
}
	
}

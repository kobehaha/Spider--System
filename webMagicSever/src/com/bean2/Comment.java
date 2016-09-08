package com.bean2;

public class Comment {//商品评论
	
	private String time;//时间，
	private String content;//内容
	
	private Buyer buyer;

	public Comment(){//构造方法
	}
	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString(){
		return "this content time="+time+"and content="+content+"buyer="+buyer.toString();
	}
	
	
}

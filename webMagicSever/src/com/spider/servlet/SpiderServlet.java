package com.spider.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean2.Shop;
import com.getInfo.SpiderAm;
import com.getInfo.SpiderDD;
import com.service.ServiceGet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/SpiderServlet")
public class SpiderServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; // 序列化版本

	/**
	 * 处理请求的servlet
	 */

	@Override
	public void init() throws ServletException {
		super.init();

	}

	@SuppressWarnings("unused")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JSONObject jsonObject = null;// 讲查询的数据转换成json传递给android

		JSONArray array =null;//处理
		if (jsonObject!=null) {
			jsonObject.remove("listShop");
			System.out.println("移除 jsonobject中的数据");
			
		}
		if (array!=null) {
			for (int i = 0; i < array.size(); i++) {
				array.remove(i);
				
			}
			
		}
		

		// req.setCharacterEncoding("utf-8");
		// resp.setCharacterEncoding("utf-8");
		PrintWriter writer = resp.getWriter();// 获取打印输出流

		String search = req.getParameter("search");// 获取搜索关键词

		ServiceGet serviceGet = new ServiceGet(search);

		List<Shop> shops2 = serviceGet.getShop();// 搜索
		
		System.out.println("shop2的大小是="+shops2.size());

		// List<Shop> shops2=new ArrayList<>();  
		//
		// for(int i=0;i<10;i++){
		// Shop shop=new Shop();
		// shop.setName("kobe");
		// shop.setFlags(1);
		// shops2.add(shop);
		// }

		jsonObject = new JSONObject();// 讲查询的数据转换成json传递给android
		array = new JSONArray();
		for (int i = 0; i < shops2.size(); i++) {
			Shop shop = shops2.get(i);
			JSONObject jsonObject2 = JSONObject.fromObject(shop);// 讲每一个house
																	// json对象
			shop.toString();
			array.add(jsonObject2);

		}
		

		jsonObject.element("listShop", array);// 最终把一个jsonarray转换成一个json对象
	

		writer.write(jsonObject.toString());
		serviceGet.shops.clear();
		serviceGet.shops2.clear();//清除数据
		SpiderDD.shops.clear();
		SpiderAm.shops.clear();
		
		shops2.clear();//清除数据
		jsonObject.clear();
		
		

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}

}

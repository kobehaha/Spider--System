package WebMagic;

import java.util.List;

import com.bean2.Shop;
import com.service.ServiceGet;

public class WebMagic {

	

	//public static void main(String[] args) {
		
		String search="macbook";

		ServiceGet serviceGet=new ServiceGet(search);
		List<Shop> shops2=serviceGet.getShop();//通过搜索关键字
//		System.out.println("size is"+shops2.size());
//		
//		for(int i=0;i<shops2.size();i++){
//			
//			System.out.println(shops2.get(i).toString());
//			
//		}
//		


	//}
}
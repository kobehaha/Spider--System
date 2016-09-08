package com.util2;

import java.util.Comparator;

import com.bean2.Shop;

public class ComparaShop  implements Comparator<Object>{

	

	@Override
	public int compare(Object o1, Object o2) {
		Shop shop1=(Shop)o1;
		Shop shop2=(Shop)o2;
		
		//return   shop1.compareTo(shop2);
		return 0;
	}

	

}

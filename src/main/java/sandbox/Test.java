package sandbox;

import java.util.ArrayList;
import java.util.Map;

import epages.Shop;

public class Test {
	
	
	public static void main(String[] args) {
		
		Shop shop = new Shop("http://unstable-main.epages.com/rs/shops/bklein/");
		System.out.println(shop.getProducts().toString());
	}

}

package com.springmvc.onlinestore.beans;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.springmvc.onlinestore.Product;



/**
 * cart functions 
 * adding and getting total price
 *
 */

@Component
@Scope(value=WebApplicationContext.SCOPE_SESSION, 
	proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart
{
	double totalCost = 0;
	double totalDiscount = 0;
	private Map<Product, Integer> contents = new HashMap<>();

	public Map<Product, Integer> getContents() {
		return contents;
	}
	
	public Set<Product> getProducts() {
		return contents.keySet();
	}
	
	public void addProduct(Product product, int count) {		
		if (contents.containsKey(product)) {
			contents.put(product, contents.get(product) + count);
		} 
		else {
			contents.put(product, count);
		}
	}
	
	/**
	 * getting total cost
	 */
	public double getTotalCost() {
		totalCost=0;
		
		for (Product product : contents.keySet()) {
			totalCost += product.getPrice()*contents.get(product);
		}
	System.out.println("DISCOUNT : : : : : " + getTotalDiscount());
		return totalCost;
	}

	public double getTotalDiscount() {
		totalDiscount = 0;
		for (Product it : contents.keySet()) {
			switch (it.getName()) {
			case "Butter":
				if (contents.get(it) >= 2) {
					for (Product i : contents.keySet()) {
						if (i.getName().equals("Bread")) {
							double discount = ((i.getPrice() * .5) * contents.get(i));
							totalDiscount+=discount;
							totalCost -= discount;
						}
					}
				}
				break;
			case "Milk":
				if (contents.get(it) >= 4) {
					double freemilk = (double) (contents.get(it) / 4) * it.getPrice();
					totalDiscount+=freemilk;
					totalCost -= freemilk;
				}
				break;
			}
		}
		return totalDiscount;
		
	}
	
	
	
}

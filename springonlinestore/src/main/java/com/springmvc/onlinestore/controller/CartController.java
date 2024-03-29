package com.springmvc.onlinestore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvc.onlinestore.Product;
import com.springmvc.onlinestore.ProductService;
import com.springmvc.onlinestore.beans.Cart;

@Controller
public class CartController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private Cart cart;

	@RequestMapping(value = "cart/add/{productId}")
	public String addToCart(@PathVariable("productId") Long productId, @RequestHeader("referer") String referedFrom) {
		Product product = productService.findProduct(productId);
		cart.addProduct(product, 1);
		return "redirect:" + referedFrom;
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public String showCart(Model model) {
		model.addAttribute(cart);
		return "cart";
	}

	
}

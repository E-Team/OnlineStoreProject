package com.springmvc.onlinestore.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.onlinestore.ProductService;



/**
 * Home page controller
 *
 */
@Controller
public class HomeController {
	@Autowired
	ProductService productService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale) {
		
		System.out.println("IN HOME CONTROLLER @@@@@@@@@@@@@@@@@@");
		return new ModelAndView("home", "allProducts", productService.getAllProducts());
	}
	
}

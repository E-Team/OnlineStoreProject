package com.springmvc.onlinestore.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springmvc.onlinestore.Product;
import com.springmvc.onlinestore.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	ProductService productService;
	
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@RequestMapping(params = "ftl")
	public String getProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "productlist";
	}


	@RequestMapping(value = "/{id}")
	public ModelAndView getProduct(@PathVariable("id") Long productId) {
		Product product = productService.findProduct(productId);
		return new ModelAndView("product/view", "product", product);
		
	}
	


	@ExceptionHandler(Exception.class)
	  public ModelAndView errorHandler(HttpServletRequest req, Exception exception) {
	    logger.error("Request: " + req.getRequestURL() + " raised " + exception);

	    ModelAndView mav = new ModelAndView();
	    mav.addObject("exception", exception);
	    mav.addObject("url", req.getRequestURL());
	    mav.setViewName("product/error");
	    return mav;
	  }

	
	
}

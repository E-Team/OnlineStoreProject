package com.springmvc.onlinestore;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductDao productDao;

	public Product findProduct(Long id) {
         Product product = productDao.findProduct(id);
         if (product == null) {
        	 throw new RuntimeException();
         }
         return product;
    }

	public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

}

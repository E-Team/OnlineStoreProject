package com.springmvc.onlinestore;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {

	@PersistenceContext
    private EntityManager entityManager;

	public List<Product> getAllProducts() {
        return entityManager.createQuery("SELECT o FROM Product o", Product.class).getResultList();
    }

	public Product findProduct(Long id) {
        if (id == null) return null;
        return entityManager.find(Product.class, id);
    }

}

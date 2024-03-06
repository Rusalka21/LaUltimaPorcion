package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProduct() {
		
		return productRepository.findAll();
	}
	
	public Product createProduct(Product product) {
		
		return productRepository.save(product);
		
	}
	
	public Product getProductById(int id) {
		
		return productRepository.findById(id).orElse(null);
		
	}
	
	public void deleteProduct(int id) {
		
		productRepository.deleteById(id);
	}

}

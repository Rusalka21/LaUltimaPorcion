package com.edu.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.cibertec.model.dao.Producto;
import com.edu.cibertec.repository.ProductoRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductoRepository repository;
	
	public List<Producto> getProductByTipo(String tipo) {
		return repository.findByTipo(tipo);
	}	

}

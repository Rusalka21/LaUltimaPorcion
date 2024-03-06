package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Unidad;
import com.example.demo.repository.UnidadRepository;

@Service
public class UnidadService {
	
	@Autowired
	private UnidadRepository unidadRepository;
	
	public List<Unidad> getAllUnidad() {
		return unidadRepository.findAll();
	}
	
	public Unidad getUnidadById(int id) {
		return unidadRepository.findById(id).orElse(null);
	}

}

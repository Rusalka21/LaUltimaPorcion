package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.TipoRepository;
import com.example.demo.model.Tipo;

@Service
public class TipoService {

	@Autowired
	private TipoRepository tipoRepository;
	
	public List<Tipo> getAllTipo() {
		return tipoRepository.findAll();
	}
	
	public Tipo getTipoById(int id) {
		return tipoRepository.findById(id).orElse(null);
	}
}

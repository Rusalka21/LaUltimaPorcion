package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Sabor;
import com.example.demo.repository.SaborRepository;

@Service
public class SaborService {

	@Autowired
	private SaborRepository saborRepository;
	
	public List<Sabor> getAllSabor() {
		return saborRepository.findAll();
	}
	
	public Sabor getSaborById(int id) {
		return saborRepository.findById(id).orElse(null);
	}
}

package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ClienteRepository;
import com.example.demo.model.Cliente;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> getAllCliente() {
		
		return clienteRepository.findAll();
	}
	
	public Cliente createCliente(Cliente cliente) {
		
		return clienteRepository.save(cliente);
	}
	
	public Cliente getClienteById(int id) {
		
		return clienteRepository.findById(id).orElse(null);
	}
	
	public Cliente getClienteByDni(Long dni) {
		
		return clienteRepository.findByDni(dni).orElse(null);
	}
	
	public void deleteCliente(int id) {
		
		clienteRepository.deleteById(id);
	}
	
}

package com.edu.cibertec.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.cibertec.model.dao.Cliente;
import com.edu.cibertec.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> listarClientes() {
		return clienteRepository.findAll();
	}

	public void guardarCliente(Cliente request) {
		switch (request.getNumeroDocumento().length()) {
			case 8: {
				clienteRepository.save(request);
				break;
			}
			case 11: {
				request.setRazonSocial(request.getNombre());;
				request.setNombre(null);
				clienteRepository.save(request);
				break;
			}
			default: {
				System.out.println("El numero de documento no es válido");
				break;
			}
		}
	}
	
	public void editarCliente(Cliente request) {
		clienteRepository.save(request);
	}

	public void eliminarCliente(Long id) {
		clienteRepository.deleteById(id);
	}

	public Cliente buscarClientePorId(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));
	}

	public Cliente buscarClientePorDocumento(String numeroDocumento) {
		return clienteRepository.findByNumeroDocumento(numeroDocumento);
	}

}

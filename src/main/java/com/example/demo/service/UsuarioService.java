package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> getAllUsuario() {
		
		return usuarioRepository.findAll();
	}
	
	public Usuario getUsuarioById(int id) {
		
		return usuarioRepository.findById(id).orElse(null);
	}
	
	public Usuario createUsuario(Usuario usuario) {
		
		return usuarioRepository.save(usuario);
	}
	
	public void deleteUsuario(int id) {
		
		usuarioRepository.deleteById(id);
	}

}

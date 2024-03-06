package com.edu.cibertec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.edu.cibertec.model.dao.Usuario;
import com.edu.cibertec.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}
	
	public List<Usuario> listarClientes() {
		return usuarioRepository.findAll();
	}	 

	public void guardarUsuario(Usuario request) {
		String password = this.hashPassword(request.getPassword());
		request.setPassword(password);
		usuarioRepository.save(request);
	}
	
	public Usuario buscarUsuarioPorId(Long id) {
		return usuarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado con ID: " + id));
	}
	
	public void eliminarUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}

	public Optional<Usuario> findByUsuario(String usuario) {
		return usuarioRepository.findByNombreUsuario(usuario);
	}

	public String validarCredenciales(Usuario usuario) {
		
		String hashedPassword = hashPassword(usuario.getPassword());
		System.out.println("Contraseña vista " + usuario.getPassword());
		System.out.println("Contraseña BCrypt " + hashedPassword);
		
		Optional<Usuario> usuarioOptional = findByUsuario(usuario.getNombreUsuario());
		String resultado = "";
		if (usuarioOptional.isPresent() && verificarPassword(usuario.getPassword(), usuarioOptional.get().getPassword())) {
			switch (usuarioOptional.get().getPerfil()) {
				case "vendedor" : {
					resultado = "redirect:/inicio/vendedor";
					break;
				}
				case "caja" : {
					resultado = "redirect:/inicio/caja";
					break;
				}
				case "admin" : {
					resultado =  "redirect:/inicio/admin";
					break;
				}
				default: {
					resultado = "redirect:/acceso/cerrar";
					break;
				}					
			}
		} else {
			resultado = "redirect:/acceso/cerrar";
		}
		
		return resultado;
	}

	private boolean verificarPassword(String rawPassword, String hashedPassword) {
		return passwordEncoder.matches(rawPassword, hashedPassword);
	}
	
	private String hashPassword(String rawPassword) {
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt());
    }

}

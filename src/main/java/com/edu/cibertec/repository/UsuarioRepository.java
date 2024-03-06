package com.edu.cibertec.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.cibertec.model.dao.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	Optional<Usuario> findByNombreUsuario(String usuario);

}

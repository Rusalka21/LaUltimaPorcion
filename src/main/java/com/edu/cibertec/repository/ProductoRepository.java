package com.edu.cibertec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.cibertec.model.dao.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long>{
	
	List<Producto> findByTipo(String tipo);
	Producto findByNombre(String nombre);

}

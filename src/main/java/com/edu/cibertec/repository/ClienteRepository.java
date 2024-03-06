package com.edu.cibertec.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.cibertec.model.dao.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Cliente findByNumeroDocumento(String numeroDocumento);

}

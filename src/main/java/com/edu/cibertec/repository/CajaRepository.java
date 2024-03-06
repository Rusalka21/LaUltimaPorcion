package com.edu.cibertec.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.cibertec.model.dao.Caja;

public interface CajaRepository extends JpaRepository<Caja, Long>{
	
	Caja findByFechaCierre(Date fechaCierre);

}

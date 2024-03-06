package com.edu.cibertec.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.cibertec.model.dao.Caja;
import com.edu.cibertec.model.dto.CajaRequest;
import com.edu.cibertec.repository.CajaRepository;

@Service
public class CajaService {
	
	@Autowired
	private CajaRepository cajaRepository;
	
    public Caja guardarCaja(CajaRequest request) {
        Caja cajaExistente = cajaRepository.findByFechaCierre(request.getCaja().getFechaCierre());

        if (cajaExistente != null) {
            throw new RuntimeException("Ya existe una caja con la misma fecha de cierre.");
        }
        return cajaRepository.save(request.getCaja());
    }

}

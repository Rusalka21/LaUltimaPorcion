package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_unidad")
public class Unidad {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cod_unidad;
	
	@Column(name = "unidad", nullable = false)
	private String unidad;
	
	public Unidad() {
		
	}

	public int getCod_unidad() {
		return cod_unidad;
	}

	public void setCod_unidad(int cod_unidad) {
		this.cod_unidad = cod_unidad;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}
	
	

}

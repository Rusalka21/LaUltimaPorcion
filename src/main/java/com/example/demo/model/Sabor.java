package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_sabor")
public class Sabor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cod_sabor;
	
	@Column(name = "sabor", nullable = false)
	private String sabor;
	
	public Sabor() {
		
	}

	public int getCod_sabor() {
		return cod_sabor;
	}

	public void setCod_sabor(int cod_sabor) {
		this.cod_sabor = cod_sabor;
	}

	public String getSabor() {
		return sabor;
	}

	public void setSabor(String sabor) {
		this.sabor = sabor;
	}
	
	
}

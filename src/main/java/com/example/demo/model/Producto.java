package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_producto")
public class Producto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo_producto;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name = "cod_tipo", nullable = false)
	private Tipo tipo;
	
	@ManyToOne
	@JoinColumn(name = "cod_sabor", nullable = false)
	private Sabor sabor;
	
	@ManyToOne
	@JoinColumn(name = "cod_unidad", nullable = false)
	private Unidad unidad;
	
	@Column(name = "dimensiones", nullable = false)
	private String dimensiones;
	
	@Column(name = "precio", nullable = false)
	private double precio;
	
	@Column(name = "stock", nullable = false)
	private int stock;
	
	public Producto() {
		
	}

	public int getCodigo_producto() {
		return codigo_producto;
	}

	public void setCodigo_producto(int codigo_producto) {
		this.codigo_producto = codigo_producto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Sabor getSabor() {
		return sabor;
	}

	public void setSabor(Sabor sabor) {
		this.sabor = sabor;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	public String getDimensiones() {
		return dimensiones;
	}

	public void setDimensiones(String dimensiones) {
		this.dimensiones = dimensiones;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
		

}

package com.edu.cibertec.model.dto;

import java.util.List;

import com.edu.cibertec.model.dao.OrdenCompra;

public class OrdenCompraRequest {
	private List<OrdenCompra> ordenes;
	
	public OrdenCompraRequest() {}

	public List<OrdenCompra> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(List<OrdenCompra> ordenes) {
		this.ordenes = ordenes;
	}		
}

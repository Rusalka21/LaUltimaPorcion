package com.edu.cibertec.model.dto;

import com.edu.cibertec.model.dao.Pedido;

public class PedidoRequest {
	
	private Pedido pedido;
	
	public PedidoRequest() {}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}

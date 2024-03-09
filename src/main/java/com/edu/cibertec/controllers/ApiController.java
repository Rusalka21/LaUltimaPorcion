package com.edu.cibertec.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.cibertec.model.dao.Caja;
import com.edu.cibertec.model.dao.Cliente;
import com.edu.cibertec.model.dao.OrdenCompra;
import com.edu.cibertec.model.dao.Pedido;
import com.edu.cibertec.model.dto.CajaRequest;
import com.edu.cibertec.model.dto.OrdenCompraRequest;
import com.edu.cibertec.model.dto.PedidoRequest;
import com.edu.cibertec.service.CajaService;
import com.edu.cibertec.service.ClienteService;
import com.edu.cibertec.service.OrdenCompraService;
import com.edu.cibertec.service.PedidoService;

@RestController
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
	private OrdenCompraService ordenCompraService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private CajaService cajaService;
	
	@PostMapping("/registrarOrdenCompra")
	public OrdenCompra registrarOrdenCompra(@RequestBody OrdenCompra request) {
		OrdenCompra ordenCompra = ordenCompraService.guardarOrden(request);		
		return ordenCompra;
	}
	
	@PostMapping("/finalizarOrdenCompra")
	public OrdenCompraRequest finalizarOrdenCompra(@RequestBody OrdenCompraRequest request) {
	    return ordenCompraService.finalizarOrdenCompra(request);
	}
	
	@DeleteMapping("/eliminarOrdenCompra/{idOrden}")
	public void eliminarOrdenCompra(@PathVariable Long idOrden) {
	    ordenCompraService.eliminarOrdenCompra(idOrden);        
	}
	
	@GetMapping("/buscarOrdenCompra")
	public List<OrdenCompra> buscarOrdenCompra(@RequestParam(name = "idOrdenCompra", required = false) String idOrdenCompra) {
		return ordenCompraService.buscarPorIdOrdenCompra(idOrdenCompra);		
	}
	
	@GetMapping("/buscarCliente")
	public Cliente buscarCliente(@RequestParam(name = "id", required = false) Long id ,
			@RequestParam(name = "numeroDocumento", required = false) String numeroDocumento) {
		if (!numeroDocumento.isEmpty()) {
			return clienteService.buscarClientePorDocumento(numeroDocumento);
		} else {
			return clienteService.buscarClientePorId(id);
		}
	}
	
	@PostMapping("/registrarPedido")
	public Pedido registrarPedido(@RequestBody PedidoRequest request) {
		System.out.print("revisar: " + request.getPedido().getNumPedido());
		return pedidoService.guardarPedido(request);		
	}
	
	@PostMapping("registrarCuadreCaja")
	public Caja registrarCuadreCaja(@RequestBody CajaRequest request) {
		return cajaService.guardarCaja(request);
	}
	
}

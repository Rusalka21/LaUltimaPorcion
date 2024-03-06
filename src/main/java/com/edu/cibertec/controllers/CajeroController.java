package com.edu.cibertec.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.cibertec.model.dao.Cliente;
import com.edu.cibertec.model.dao.Pedido;
import com.edu.cibertec.service.ClienteService;
import com.edu.cibertec.service.PedidoService;

@Controller
@RequestMapping("/inicio/caja")
public class CajeroController {

	@Autowired
	ClienteService clienteService;

	@Autowired
	PedidoService pedidoService;

	@GetMapping("")
	public String showIndex() {
		return "viewCaja/principal";
	}

	@GetMapping("/venta")
	public String mostrarVenta() {
		return "viewCaja/venta";
	}

	@GetMapping("/clientes")
	public String mostrarClientes(Model model) {
		List<Cliente> clientes = clienteService.listarClientes();
		model.addAttribute("clientes", clientes);
		return "viewCaja/clientes";
	}

	@GetMapping("/historialVentas")
	public String mostrarHistoricoVenta(Model model) {
		List<Pedido> pedidos = pedidoService.listarPedidosFechaAcutal();
		model.addAttribute("pedidos", pedidos);
		return "viewCaja/historicoVenta";
	}

	@GetMapping("/historialVentas/detalle/{id}")
	public String mostrarDetalleHistoricoVenta(@PathVariable Long id, Model model) {
		Pedido pedido = pedidoService.buscarPedidoPorId(id);
		BigDecimal total = pedido.getDetalles().stream()
				.map(detalle -> detalle.getSubtotal())
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		String numPedido = pedido.getNumPedido();
		
		model.addAttribute("numPedido", numPedido);
		model.addAttribute("total", total);
		model.addAttribute("pedido", pedido);
		
		return "viewCaja/historicoVentaDetalle";
	}

	@GetMapping("/nuevoCliente")
	public String mostrarNuevoCliente(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "viewCaja/nuevoCliente";
	}

	@PostMapping("/nuevoCliente")
	public String registrarNuevoCliente(@ModelAttribute Cliente request) {
		clienteService.guardarCliente(request);
		return "redirect:/inicio/caja/clientes";
	}

	@GetMapping("/cierreCaja")
	public String mostrarCierreCaja() {
		return "viewCaja/cierreCaja";
	}

	@GetMapping("/editarCliente/{id}")
	public String mostraEditarCliente(@PathVariable Long id, Model model) {
		Cliente cliente = clienteService.buscarClientePorId(id);
		model.addAttribute("cliente", cliente);
		return "viewCaja/editarCliente";
	}

	@PostMapping("/editarCliente")
	public String editarCliente(@ModelAttribute Cliente request) {
		clienteService.editarCliente(request);
		return "redirect:/inicio/caja/clientes";
	}

	@GetMapping("/eliminarCliente/{id}")
	public String eliminarCliente(@PathVariable Long id) {
		clienteService.eliminarCliente(id);
		return "redirect:/inicio/caja/clientes";
	}

	@GetMapping("/venta/imprimir/{id}")
	public String mostrarImpresionPedido(@PathVariable Long id, Model model) {
		Pedido pedido = pedidoService.buscarPedidoPorId(id);
		model.addAttribute("pedido", pedido);
		return "viewCaja/comprobante";
	}

}
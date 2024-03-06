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
import com.edu.cibertec.model.dao.Usuario;
import com.edu.cibertec.service.ClienteService;
import com.edu.cibertec.service.PedidoService;
import com.edu.cibertec.service.UsuarioService;

@Controller
@RequestMapping("/inicio/admin")
public class AdminController {
	
	@Autowired
	ClienteService clienteService;
	
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	UsuarioService usuarioService;

    @GetMapping("")
    public String mostrarAdminInicio() {
    	return "viewAdmin/principal";
    }
    
    @GetMapping("/historialVentas")
	public String mostrarHistoricoVenta(Model model) {
		List<Pedido> pedidos = pedidoService.listarPedidos();
		model.addAttribute("pedidos", pedidos);
		return "viewAdmin/historicoVenta";
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
		
		return "viewAdmin/historicoVentaDetalle";
	}

    @GetMapping("/clientes")
    public String mostrarClientes(Model model) {
		List<Cliente> clientes = clienteService.listarClientes();
		model.addAttribute("clientes", clientes);
		return "viewAdmin/clientes";
    }
    
    @GetMapping("/nuevoCliente")
	public String mostrarNuevoCliente(Model model) {
		model.addAttribute("cliente", new Cliente());
		return "viewAdmin/nuevoCliente";
	}

	@PostMapping("/nuevoCliente")
	public String registrarNuevoCliente(@ModelAttribute Cliente request) {
		clienteService.guardarCliente(request);
		return "redirect:/inicio/admin/clientes";
	}
	
	@GetMapping("/editarCliente/{id}")
	public String mostraEditarCliente(@PathVariable Long id, Model model) {
		Cliente cliente = clienteService.buscarClientePorId(id);
		model.addAttribute("cliente", cliente);
		return "viewAdmin/editarCliente";
	}
	
	@PostMapping("/editarCliente")
	public String editarCliente(@ModelAttribute Cliente request) {
		clienteService.editarCliente(request);
		return "redirect:/inicio/admin/clientes";
	}
	
	@GetMapping("/eliminarCliente/{id}")
	public String eliminarCliente(@PathVariable Long id) {
		clienteService.eliminarCliente(id);
		return "redirect:/inicio/admin/clientes";
	}
	
	@GetMapping("/usuarios")
	public String mostrarUsuarios(Model model) {
		List<Usuario> usuarios = usuarioService.listarClientes();
		model.addAttribute("usuarios", usuarios);
		return "viewAdmin/usuarios";
	}
	
	@GetMapping("/nuevoUsuario")
	public String mostrarNuevoUsuario(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "viewAdmin/nuevoUsuario";
	}
	
	@PostMapping("/nuevoUsuario")
	public String registrarNuevoUsuario(@ModelAttribute Usuario request) {
		usuarioService.guardarUsuario(request);
		return "redirect:/inicio/admin/usuarios";
	}
	
	@GetMapping("/editarUsuario/{id}")
	public String editarUsuario(@PathVariable Long id, Model model) {
		Usuario usuario = usuarioService.buscarUsuarioPorId(id);
		model.addAttribute("usuario", usuario);
		return "viewAdmin/editarUsuario";
	}
	
	@PostMapping("/editarUsuario")
	public String editarUsuario(@ModelAttribute Usuario request) {
		usuarioService.guardarUsuario(request);
		return "redirect:/inicio/admin/usuarios";
	}
	
	@GetMapping("/eliminarUsuario/{id}")
	public String eliminarUsuario(@PathVariable Long id) {
		usuarioService.eliminarUsuario(id);
		return "redirect:/inicio/admin/usuarios";
	}

}

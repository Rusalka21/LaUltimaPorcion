package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Cliente;
import com.example.demo.service.ClienteService;

@Controller
@RequestMapping("/cajero")
public class Cajero_controller {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/addcliente")
	public String mostrarCajeroAddC() {
		return "Cajero_AddC";
	}

	@PostMapping("/addcliente")
	public String procesarFormularioAddC(@RequestParam(name = "nombre") String nombre,
			@RequestParam(name = "apellidos") String apellidos, @RequestParam(name = "dni") Long dni,
			@RequestParam(name = "email") String email, @RequestParam(name = "direccion") String direccion,
			@RequestParam(name = "celular") Long celular, Model model) {

		Cliente cliente = new Cliente();
		cliente.setNombre(nombre);
		cliente.setApellidos(apellidos);
		cliente.setDni(dni);
		cliente.setEmail(email);
		cliente.setDireccion(direccion);
		cliente.setCelular(celular);

		clienteService.createCliente(cliente);

		List<Cliente> clientes = clienteService.getAllCliente();

		model.addAttribute("clientes", clientes);
		return "Cajero_clientes";
	}

	@GetMapping("/cierrecaja")
	public String mostrarCajeroCC() {
		return "Cajero_CC";
	}

	@GetMapping("/clientes")
	public String mostrarCajeroClientes(Model model) {
		List<Cliente> clientes = clienteService.getAllCliente();

		model.addAttribute("clientes", clientes);
		return "Cajero_clientes";
	}

	@GetMapping("/editcliente/{cod_cliente}")
	public String mostrarCajeroEditC(@PathVariable int cod_cliente, Model model) {
		Cliente cliente = clienteService.getClienteById(cod_cliente);
		model.addAttribute("cliente", cliente);
		return "Cajero_editC";
	}

	@PostMapping("/editcliente")
	public String editarCliente(@RequestParam(name = "codCliente") int cod_cliente,
			@RequestParam(name = "nombre") String nombre, @RequestParam(name = "apellidos") String apellidos,
			@RequestParam(name = "dni") Long dni, @RequestParam(name = "email") String email,
			@RequestParam(name = "direccion") String direccion, @RequestParam(name = "celular") Long celular,
			Model model) {

		Cliente cliente = clienteService.getClienteById(cod_cliente);
		cliente.setNombre(nombre);
		cliente.setApellidos(apellidos);
		cliente.setDni(dni);
		cliente.setEmail(email);
		cliente.setDireccion(direccion);
		cliente.setCelular(celular);

		clienteService.createCliente(cliente);

		List<Cliente> clientes = clienteService.getAllCliente();

		model.addAttribute("clientes", clientes);
		return "Cajero_clientes";
	}

	@GetMapping("/delete/{cod_cliente}")
	public String deleteProducto(@PathVariable int cod_cliente, Model model) {

		clienteService.deleteCliente(cod_cliente);

		List<Cliente> clientes = clienteService.getAllCliente();

		model.addAttribute("clientes", clientes);

		return "Cajero_clientes";
	}

	@GetMapping("/grabarventa")
	public String mostrarCajeroGV(Model model) {
		return "Cajero_Gv";
	}

	@PostMapping("/grabarventa")
	public String mostrarCajeroGVCliente(@RequestParam(name = "buscarDNI") Long buscarDNI, Model model) {
		try {
			if (buscarDNI > 0) {
				Cliente cliente = clienteService.getClienteByDni(buscarDNI);
				model.addAttribute("cliente", cliente);
				System.out.println(cliente.getNombre());
			}
		} catch (Exception e) {
		}

		return "Cajero_Gv";
	}

	@GetMapping("/historialventas")
	public String mostrarCajeroHV() {
		return "Cajero_Hv";
	}

	@GetMapping("/inicio")
	public String mostrarCajeroInicio() {
		return "Cajero_inicio";
	}

}
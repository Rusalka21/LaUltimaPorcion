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
@RequestMapping("/admin")
public class Admin_Controller {
	
	@Autowired
	private ClienteService clienteService;

    @GetMapping("/admininicio")
    public String mostrarAdminInicio() {
        return "AdminInicio";
    }

    @GetMapping("/admingestionclientes")
    public String mostrarAdminGestionClientes(Model model) {
    	
    	List<Cliente> listCliente = clienteService.getAllCliente();
    	
    	model.addAttribute("clientes" ,listCliente);
    	
        return "AdminGestionClientes";
    }

    @GetMapping("/admininventario")
    public String mostrarAdminInventario() {
        return "AdminInventario";
    }

    @GetMapping("/adminpedidos")
    public String mostrarAdminPedidos() {
        return "AdminPedidos";
    }

    @GetMapping("/adminusuarios")
    public String mostrarAdminUsuarios() {
        return "AdminUsuarios";
    }

    @GetMapping("/inventarioadd")
    public String mostrarInventarioAdd() {
        return "InventarioAdd";
    }

    @GetMapping("/inventarioedit")
    public String mostrarInventarioEdit() {
        return "InventarioEdit";
    }

    @GetMapping("/adclienteadd")
    public String mostrarAdClienteAdd() {
        return "AdClienteAdd";
    }
    
    @PostMapping("/adclienteadd")
    public String createCliente(@RequestParam(name = "nombre") String nombre,
    		@RequestParam(name= "apellidos") String apellidos,
    		@RequestParam(name = "dni") Long dni,
    		@RequestParam(name= "email") String email,
    		@RequestParam(name = "direccion") String direccion,
    		@RequestParam(name = "celular") Long celular,
    		Model model) {
    	
    	Cliente cliente = new Cliente();
    	
    	cliente.setNombre(nombre);
    	cliente.setApellidos(apellidos);
    	cliente.setDni(dni);
    	cliente.setEmail(email);
    	cliente.setDireccion(direccion);
    	cliente.setCelular(celular);
    	
    	clienteService.createCliente(cliente);
    	
    	List<Cliente> listCliente = clienteService.getAllCliente();
    	
    	model.addAttribute("clientes" ,listCliente);    	
    	
    	return "AdminGestionClientes";
    }

    @GetMapping("/adclienteedit/{cod_cliente}")
    public String mostrarAdClienteEdit(@PathVariable int cod_cliente, Model model) {
    	    	    	
    	Cliente cliente = clienteService.getClienteById(cod_cliente);
    	
    	model.addAttribute("cliente" ,cliente);    	
    	
        return "AdClienteEdit";
    }
    
    @PostMapping("/adclienteedit")
    public String editCliente(@RequestParam(name = "cod_cliente") int cod_cliente,
    		@RequestParam(name = "nombre") String nombre,
    		@RequestParam(name= "apellidos") String apellidos,
    		@RequestParam(name = "dni") Long dni,
    		@RequestParam(name= "email") String email,
    		@RequestParam(name = "direccion") String direccion,
    		@RequestParam(name = "celular") Long celular,
    		Model model) {
    	
    	Cliente cliente = clienteService.getClienteById(cod_cliente);
    	
    	cliente.setNombre(nombre);
    	cliente.setApellidos(apellidos);
    	cliente.setDni(dni);
    	cliente.setEmail(email);
    	cliente.setDireccion(direccion);
    	cliente.setCelular(celular);
    	
    	clienteService.createCliente(cliente);
    	
    	List<Cliente> listCliente = clienteService.getAllCliente();
    	
    	model.addAttribute("clientes" ,listCliente);     	
    	
    	return "AdminGestionClientes";
    }
    
    @GetMapping("/deletecliente/{cod_cliente}")
    public String deleteCliente(@PathVariable int cod_cliente, Model model) {
    	
    	clienteService.deleteCliente(cod_cliente);
    	
    	List<Cliente> listCliente = clienteService.getAllCliente();
    	
    	model.addAttribute("clientes", listCliente);
    	
    	return "AdminGestionClientes";
    }

    @GetMapping("/usuariosadd")
    public String mostrarUsuariosAdd() {
        return "UsuariosAdd";
    }

    @GetMapping("/usuariosedit")
    public String mostrarUsuariosEdit() {
        return "UsuariosEdit";
    }
    
    // Otros métodos según necesidades adicionales

}

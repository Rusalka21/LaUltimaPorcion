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
import com.example.demo.model.Product;
import com.example.demo.model.Usuario;
import com.example.demo.service.ClienteService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UsuarioService;

@Controller
@RequestMapping("/admin")
public class Admin_Controller {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ProductService productService;

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
    public String mostrarAdminInventario(Model model) {
    	
    	List<Product> listProduct = productService.getAllProduct();
    	
    	model.addAttribute("products", listProduct);
    	
        return "AdminInventario";
    }
    
    @GetMapping("/inventarioadd")
    public String mostrarInventarioAdd() {
        return "InventarioAdd";
    }
    
    @PostMapping("/inventarioadd")
    public String createProduct(@RequestParam(name= "nombre") String nombre,
    		@RequestParam(name= "tipo") String tipo,
    		@RequestParam(name= "unidad") String unidad,
    		@RequestParam(name= "descripcion") String descripcion,
    		@RequestParam(name= "precio") double precio,
    		@RequestParam(name= "stock") int stock,
    		Model model) {
    	
    	Product product = new Product();
    	
    	product.setNombre(nombre);
    	product.setTipo(tipo);
    	product.setUnidad(unidad);
    	product.setDescripcion(descripcion);
    	product.setPrecio(precio);
    	product.setStock(stock);
    	
    	productService.createProduct(product);
    	
    	List<Product> listProduct = productService.getAllProduct();
    	
    	model.addAttribute("products", listProduct);    	
    	
    	return "AdminInventario";
    }
    
    @GetMapping("/inventarioedit/{id_producto}")
    public String mostrarInventarioEdit(@PathVariable int id_producto, Model model) {
    	
    	Product product = productService.getProductById(id_producto);
    	
    	model.addAttribute("product", product); 	    	
    	
        return "InventarioEdit";
    }
    
    @PostMapping("/inventarioedit")
    public String editProduct(@RequestParam(name = "id_producto") int id_producto,
    		@RequestParam(name = "nombre") String nombre,
    		@RequestParam(name = "tipo") String tipo,
    		@RequestParam(name = "unidad") String unidad,
    		@RequestParam(name = "descripcion") String descripcion,
    		@RequestParam(name = "precio") double precio,
    		@RequestParam(name = "stock") int stock,
    		Model model) {
    	
    	Product product = productService.getProductById(id_producto);
    	
    	product.setNombre(nombre);
    	product.setTipo(tipo);
    	product.setUnidad(unidad);
    	product.setDescripcion(descripcion);
    	product.setPrecio(precio);
    	product.setStock(stock);
    	
    	productService.createProduct(product);
    	
    	List<Product> listProduct = productService.getAllProduct();
    	
    	model.addAttribute("products", listProduct);
    	
    	return "AdminInventario";
    }
    
    @GetMapping("/deleteproduct/{id_producto}")
    public String deleteProduct(@PathVariable int id_producto,
    		Model model) {
    	
    	productService.deleteProduct(id_producto);
    	
    	List<Product> listProduct = productService.getAllProduct();
    	
    	model.addAttribute("products", listProduct);
    	
    	return "AdminInventario";
    }

    @GetMapping("/adminpedidos")
    public String mostrarAdminPedidos() {    	
    	
        return "AdminPedidos";
    }

    @GetMapping("/adminusuarios")
    public String mostrarAdminUsuarios(Model model) {
    	
    	List<Usuario> listUsuario = usuarioService.getAllUsuario();
    	
    	model.addAttribute("usuarios", listUsuario);
    	
        return "AdminUsuarios";
    }
    
    @GetMapping("/usuariosadd")
    public String mostrarUsuariosAdd() {    	
    	   	
        return "UsuariosAdd";
    }
    
    @PostMapping("/adusuarioadd")
    public String createUsuario(@RequestParam(name = "nombre")String nombre,
    		@RequestParam(name = "apellidos")String apellidos,
    		@RequestParam(name = "dni")Long dni,
    		@RequestParam(name = "email")String email,
    		@RequestParam(name = "celular")Long celular,
    		@RequestParam(name = "username")String username,
    		@RequestParam(name = "contrasena")String contrasena,
    		@RequestParam(name = "tipo_usuario")String tipo_usuario,
    		Model model) {
    	
    	Usuario usuario = new Usuario();
    	
    	usuario.setNombre(nombre);
    	usuario.setApellidos(apellidos);
    	usuario.setDni(dni);
    	usuario.setEmail(email);
    	usuario.setCelular(celular);
    	usuario.setUsername(username);
    	usuario.setContrasena(contrasena);
    	usuario.setTipo_usuario(tipo_usuario);
    	
    	usuarioService.createUsuario(usuario);
    	
    	List<Usuario> listUsuario = usuarioService.getAllUsuario();
    	
    	model.addAttribute("usuarios", listUsuario);
    	
    	return "AdminUsuarios";
    }
    
    @GetMapping("/usuariosedit/{id_usuario}")
    public String mostrarUsuariosEdit(@PathVariable int id_usuario, Model model) {
    	
    	Usuario usuario = usuarioService.getUsuarioById(id_usuario);
    	
    	model.addAttribute("usuario", usuario);
    	
        return "UsuariosEdit";
    }
    
    @PostMapping("/usuariosedit")
    public String editUsuario(@RequestParam(name = "id_usuario") int id_usuario,
    		@RequestParam(name = "nombre") String nombre,
    		@RequestParam(name = "apellidos") String apellidos,
    		@RequestParam(name = "dni") Long dni,
    		@RequestParam(name = "email") String email,
    		@RequestParam(name = "celular") Long celular,
    		@RequestParam(name = "username") String username,
    		@RequestParam(name = "contrasena") String contrasena,
    		@RequestParam(name = "tipo_usuario") String tipo_usuario,
    		Model model) {
    	
    	Usuario usuario = usuarioService.getUsuarioById(id_usuario);
    	
    	usuario.setNombre(nombre);
    	usuario.setApellidos(apellidos);
    	usuario.setDni(dni);
    	usuario.setEmail(email);
    	usuario.setCelular(celular);
    	usuario.setUsername(username);
    	usuario.setContrasena(contrasena);
    	usuario.setTipo_usuario(tipo_usuario);
    	
    	usuarioService.createUsuario(usuario);
    	
    	List<Usuario> listUsuario = usuarioService.getAllUsuario();
    	
    	model.addAttribute("usuarios", listUsuario);
    	
    	return "AdminUsuarios";   	
    	
    }
    
    @GetMapping("/deleteusuario/{id_usuario}")
    public String deleteUsuario(@PathVariable int id_usuario, Model model) {
    	
    	usuarioService.deleteUsuario(id_usuario);
    	
    	List<Usuario> listUsuario = usuarioService.getAllUsuario();
    	
    	model.addAttribute("usuarios", listUsuario);
    	
    	return "AdminUsuarios";
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
    
    
    
    // Otros métodos según necesidades adicionales

}

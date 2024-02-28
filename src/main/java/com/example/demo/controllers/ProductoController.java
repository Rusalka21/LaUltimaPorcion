package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Producto;
import com.example.demo.model.Sabor;
import com.example.demo.model.Tipo;
import com.example.demo.model.Unidad;
import com.example.demo.service.ProductoService;
import com.example.demo.service.SaborService;
import com.example.demo.service.TipoService;
import com.example.demo.service.UnidadService;

@Controller
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private SaborService saborService;
	
	@Autowired
	private TipoService tipoService;
	
	@Autowired
	private UnidadService unidadService;

	@GetMapping("/getAllProductos")
	public String getAllProducto(Model model) {
		
		List<Producto> listProducto = productoService.getAllProducto();
		
		model.addAttribute("productos", listProducto);
		
		return "productoList";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		
		model.addAttribute("sabores", saborService.getAllSabor());
		model.addAttribute("tipos", tipoService.getAllTipo());
		model.addAttribute("unidades", unidadService.getAllUnidad());
		
		return "productoRegister";
	}
	
	@PostMapping("/register")
	public String createProducto(@RequestParam(name = "nombre") String nombre,
			@RequestParam(name = "cod_tipo") int cod_tipo,
			@RequestParam(name = "cod_sabor") int cod_sabor,
			@RequestParam(name = "cod_unidad") int cod_unidad,
			@RequestParam(name = "dimensiones") String dimensiones,
			@RequestParam(name = "precio") double precio,
			@RequestParam(name = "stock") int stock,
			Model model) {
		
		Producto producto = new Producto();
		producto.setNombre(nombre);
		
		Tipo tipo = tipoService.getTipoById(cod_tipo);
		producto.setTipo(tipo);
		
		Sabor sabor = saborService.getSaborById(cod_sabor);
		producto.setSabor(sabor);
		
		Unidad unidad = unidadService.getUnidadById(cod_unidad);
		producto.setUnidad(unidad);
		
		producto.setDimensiones(dimensiones);
		producto.setPrecio(precio);
		producto.setStock(stock);
		
		productoService.createProducto(producto);
		
		List<Producto> listProducto = productoService.getAllProducto();
		
		model.addAttribute("productos" ,listProducto);
		
		return "productoList";
	}
	
	@GetMapping("/edit/{codigo_producto}")
	public String getProductoById(@PathVariable int codigo_producto, Model model) {
		
		Producto producto = productoService.getProductoById(codigo_producto);
		
		model.addAttribute("producto", producto);
		model.addAttribute("sabores", saborService.getAllSabor());
		model.addAttribute("tipos", tipoService.getAllTipo());
		model.addAttribute("unidades", unidadService.getAllUnidad());
		
		return "productoEdit";
	}
	
	@PostMapping("/edit")
	public String editPelicula(@RequestParam(name = "codigo_producto") int codigo_producto,
			@RequestParam(name = "nombre") String nombre,
			@RequestParam(name = "cod_tipo") int cod_tipo,
			@RequestParam(name = "cod_sabor") int cod_sabor,
			@RequestParam(name = "cod_unidad") int cod_unidad,
			@RequestParam(name = "dimensiones") String dimensiones,
			@RequestParam(name = "precio") double precio,
			@RequestParam(name = "stock") int stock,
			Model model) {
		
		Producto producto = productoService.getProductoById(codigo_producto);
		producto.setNombre(nombre);
		
		Tipo tipo = tipoService.getTipoById(cod_tipo);
		producto.setTipo(tipo);
		
		Sabor sabor = saborService.getSaborById(cod_sabor);
		producto.setSabor(sabor);
		
		Unidad unidad = unidadService.getUnidadById(cod_unidad);
		producto.setUnidad(unidad);
		
		producto.setDimensiones(dimensiones);
		producto.setPrecio(precio);
		producto.setStock(stock);
		
		productoService.createProducto(producto);
		
		List<Producto> listProducto = productoService.getAllProducto();
		
		model.addAttribute("productos" ,listProducto);
		
		return "productoList";
	}
	
	@GetMapping("/delete/{codigo_producto}")
	public String deleteProducto(@PathVariable int codigo_producto, Model model) {
		
		productoService.deleteProducto(codigo_producto);
		
		List<Producto> listProducto = productoService.getAllProducto();
		
		model.addAttribute("productos", listProducto);
		
		return "productoList";
	}
}

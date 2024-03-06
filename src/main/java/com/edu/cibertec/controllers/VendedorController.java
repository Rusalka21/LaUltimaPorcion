package com.edu.cibertec.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.cibertec.model.dao.OrdenCompra;
import com.edu.cibertec.model.dao.Producto;
import com.edu.cibertec.service.OrdenCompraService;
import com.edu.cibertec.service.ProductService;

@Controller
@RequestMapping("/inicio/vendedor")
public class VendedorController {

	@Autowired
	private ProductService productoService;

	@Autowired
	private OrdenCompraService ordenCompraService;

	@GetMapping("")
	public String showIndex() {
		return "viewVendedor/principal";
	}

	@GetMapping("/catalogoPasteles")
	public String mostrarCatalogo(Model model) {
		List<Producto> pasteles = productoService.getProductByTipo("pasteles");
		model.addAttribute("pasteles", pasteles);
		return "viewVendedor/catalogoPasteles";
	}

	@GetMapping("/catalogoPorciones")
	public String mostrarCatalogoPorciones(Model model) {
		List<Producto> porciones = productoService.getProductByTipo("porciones");
		model.addAttribute("porciones", porciones);
		return "viewVendedor/catalogoPorciones";
	}

	@GetMapping("/catalogoBebidas")
	public String mostrarCatalogoBebidas(Model model) {
		List<Producto> bebidas = productoService.getProductByTipo("bebidas");
		model.addAttribute("bebidas", bebidas);
		return "viewVendedor/catalogoBebidas";
	}

	@GetMapping("/ordenar")
	public String mostrarOrdenar(@RequestParam(name = "idOrdenCompra", required = false) String idOrdenCompra,
	        Model model) {
	    List<OrdenCompra> ordenes = ordenCompraService.buscarPorIdOrdenCompra(idOrdenCompra);
	    BigDecimal total = ordenes.stream()
	    		.map(orden -> orden.getSubTotal())
	    		.reduce(BigDecimal.ZERO, BigDecimal::add);

	    model.addAttribute("total", total);
	    model.addAttribute("ordenes", ordenes);

	    return "viewVendedor/ordenCompra";
	}

	@GetMapping("/historial")
	public String mostrarHistorial(Model model) {
		List<OrdenCompra> ordenesDia = ordenCompraService.buscarPorFechaActual();
		Long cant = ordenesDia.stream().count();
		System.out.println("Cantidad diaria" + cant);
		model.addAttribute("ordenesDia", ordenesDia);
		return "viewVendedor/historialOrdenes";
	}
}

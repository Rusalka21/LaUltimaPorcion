package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/inicio")
public class Vendedor_controller {
	
	@GetMapping("/VendedorPrincipal")
	public String showIndex() {
		return"VendedorPrincipal";
	}

	 @GetMapping("/Catalogo")
	    public String mostrarCatalogo() {
	        return "Catalogo";
	    }
	 
	 @GetMapping("/CatalogoPorciones")
	    public String mostrarCatalogoPorciones() {
	        return "Catalogo_Porciones";
	    }
	 
	 @GetMapping("/CatalogoBebidas")
	    public String mostrarCatalogoBebidas() {
	        return "Catalogo_bebidas";
	    }

	    @GetMapping("/Ordenar")
	    public String mostrarOrdenar() {
	        return "Ordenar";
	    }

	    @GetMapping("/Historial")
	    public String mostrarHistorial() {
	        return "Historial";
	    }
	    
	    @GetMapping("/Login")
	    public String mostrarLogin() {
	        return "Login";
	    }
	
	    @GetMapping("/Cerrar")
	    public String mostrarCerrar() {
	        return "Cerrar_Sesion";
	    }
}

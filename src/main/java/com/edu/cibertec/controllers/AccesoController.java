package com.edu.cibertec.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edu.cibertec.model.dao.Usuario;
import com.edu.cibertec.service.UsuarioService;

@Controller
@RequestMapping("/acceso")
public class AccesoController {
	
	@Autowired
	private UsuarioService usuarioService;

	
	@GetMapping("/login")
	public String showLogin(Model model) {
		model.addAttribute("newUsuario", new Usuario());
		return "login";
	}
	
	@GetMapping("/cerrar")
	public String shwoClose() {
		return "cerrarSesion";
	}
	
	@PostMapping("/validarUsuario")
	public String validarUsuario(@ModelAttribute Usuario usuario) {
		String resultado = usuarioService.validarCredenciales(usuario);
		return resultado;
	}

}

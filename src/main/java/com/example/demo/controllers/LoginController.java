package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.service.UsuarioService;
import com.example.demo.model.Usuario;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
    private UsuarioService usuarioService;
	
	@PostMapping("/autenticar")
	public RedirectView autenticar(@RequestParam (name = "username") String username,
			@RequestParam (name = "contrasena") String contrasena,
			RedirectAttributes attributes) {
		
		Usuario usuario = usuarioService.autenticar(username, contrasena);
		
		if(usuario != null) {
			String redirectPage = determineRedirectPage(usuario.getTipo_usuario());
			attributes.addFlashAttribute("usuario", usuario);
			return new RedirectView(redirectPage);
		} else {
			attributes.addFlashAttribute("error", "Invalid username or password");
			return new RedirectView("/inicio/login");
		}		
	}
	
	private String determineRedirectPage(String tipo_usuario) {
	    switch (tipo_usuario) {
	        case "Vendedor":
	            return "/inicio/VendedorPrincipal";
	        case "Cajero":
	            return "/cajero/inicio";
	        case "Administrador":
	            return "/admin/admininicio";
	        default:
	            return "/login";
	    }
	}
}

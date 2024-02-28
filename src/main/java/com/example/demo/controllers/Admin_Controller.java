package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class Admin_Controller {

    @GetMapping("/admininicio")
    public String mostrarAdminInicio() {
        return "AdminInicio";
    }

    @GetMapping("/admingestionclientes")
    public String mostrarAdminGestionClientes() {
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

    @GetMapping("/adclienteedit")
    public String mostrarAdClienteEdit() {
        return "AdClienteEdit";
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

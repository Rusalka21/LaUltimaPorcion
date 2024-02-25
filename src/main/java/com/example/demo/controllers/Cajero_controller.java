package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cajero")
public class Cajero_controller {

    @GetMapping("/addcliente")
    public String mostrarCajeroAddC() {
        return "Cajero_AddC";
    }

    @GetMapping("/cierrecaja")
    public String mostrarCajeroCC() {
        return "Cajero_CC";
    }

    @GetMapping("/clientes")
    public String mostrarCajeroClientes() {
        return "Cajero_clientes";
    }

    @GetMapping("/editcliente")
    public String mostrarCajeroEditC() {
        return "Cajero_editC";
    }

    @GetMapping("/grabarventa")
    public String mostrarCajeroGV() {
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
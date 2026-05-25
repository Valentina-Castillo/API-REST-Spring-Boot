package com.ipartek.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavegacionControlador {

    @GetMapping({"/", "/login"})
    public String login() { return "login"; }

    @GetMapping("/dashboard")
    public String dashboard() { return "BASE"; }

    @GetMapping("/coches")
    public String irACoches() { return "coches/gestion"; }

    @GetMapping("/marcas")
    public String irAMarcas() { return "marcas/gestion"; }
}

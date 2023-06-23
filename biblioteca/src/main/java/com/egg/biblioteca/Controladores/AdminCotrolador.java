package com.egg.biblioteca.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminCotrolador {
    
    @GetMapping("/dashboard")
    public String panelAdministraivo(){
        return "panel.html";
    }
}

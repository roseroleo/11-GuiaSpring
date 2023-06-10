package com.Egg.News.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/") //Configura la url que responda al llamado
public class NoticaControlador {
    
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
    
    @GetMapping("/modificar")
    public String modificarNoticia(){
        return "FormularioNoticia.html";
    }
}

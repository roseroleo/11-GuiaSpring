package com.Egg.News.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/") //Configura la url que recibira el llamado
public class PortalControlador { //Localhost:880/
    
    @GetMapping("/")
    public String index(){ //Localhost:880/
        return "index.html";
    }
}

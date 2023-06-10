package com.Egg.News.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/autor")
public class AutorControlador {
    
    @GetMapping("/registrar") //localhost:8080/autor/registrar
    public String registrar(){ 
        return "form_autor.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre){
        System.out.println("Nombre "+nombre);
        return "form_autor.html";
    }
}

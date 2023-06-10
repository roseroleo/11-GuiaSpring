package com.egg.biblioteca.Controladores;

import com.egg.biblioteca.Excepciones.MyException;
import com.egg.biblioteca.Servicio.AutorServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/autor")
public class AutorControlador {
    
    @Autowired
    private AutorServicio autorServicio;
    
    @GetMapping("/registrar") //localhost:8080/autor/registrar
    public String registrar(){ 
        return "form_autor.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre){
        try {
            autorServicio.cearAutor(nombre);
        } catch (MyException ex) {
            Logger.getLogger(AutorControlador.class.getName()).log(Level.SEVERE, null, ex);
        return "form_autor.html";
        }
        return "index.html";
    }
    
    
}

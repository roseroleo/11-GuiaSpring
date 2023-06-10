package com.Egg.News.Controladores;

import com.egg.biblioteca.Servicio.AutorServicio;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/libro")
public class LibroControlador { //Localhost:8080/libro
    
    AutorServicio autorservice = new AutorServicio();
    
    @GetMapping("/crearlibro")
    public String CrearLibro(){ //Localhost:8080/libro/CrearLibro
        return "form_libro.html";
    }
}

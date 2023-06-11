package com.egg.biblioteca.Controladores;

import com.egg.biblioteca.Excepciones.MyException;
import com.egg.biblioteca.Repositorio.AutorRepositorio;
import com.egg.biblioteca.Servicio.AutorServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/autor")
public class AutorControlador {

    //Instanciamos un servicio para acceder a sus metodos
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private AutorRepositorio autorRepo;

    //Configuramos la url de comunicacion
    @GetMapping("/registrar") //localhost:8080/autor/registrar
    public String registrar() {
        //Retormanos la vista en pantalla
        return "form_autor.html";
    }

    //Impactamos en la BD mediante los metodos del servicio
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, ModelMap modelo) { //el nombre coincide con el que viende de html
        try {
            autorServicio.cearAutor(nombre);
            modelo.put("Exito", "El Autor fue cargado exitosamente");
        } catch (MyException ex) {
            modelo.put("Error", ex.getMessage());
            return "form_autor.html";
        }
        return "index.html";
    }
    
    /*
    @GetMapping("/modificar")
    public String modificar(@RequestParam String nombre){
        String idAutor = autorRepo.buscarPorNombreAutor(nombre).getId();
        try {
            autorServicio.modificarAutor(idAutor, nombre);
        } catch (MyException ex) {
            return "form_autor.html";
        }
        return "index_html";
    }
    */
}

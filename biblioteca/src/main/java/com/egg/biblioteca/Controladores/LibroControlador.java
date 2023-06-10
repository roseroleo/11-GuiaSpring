package com.egg.biblioteca.Controladores;

import com.egg.biblioteca.Entidades.Autor;
import com.egg.biblioteca.Entidades.Editorial;
import com.egg.biblioteca.Excepciones.MyException;
import com.egg.biblioteca.Servicio.AutorServicio;
import com.egg.biblioteca.Servicio.EditorialServicio;
import com.egg.biblioteca.Servicio.LibroServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/libro")
public class LibroControlador { //Localhost:8080/libro
    
    //Instanciamos los servicios
    @Autowired
    private LibroServicio libroService;
    @Autowired
    private AutorServicio autorService;
    @Autowired
    private EditorialServicio editorialService;
    
    @GetMapping("/registrar")
    public String Registrar(ModelMap modelo){ //Localhost:8080/libro/CrearLibro
        List<Autor> autores = autorService.listaAutor();
        List<Editorial> editoriales = editorialService.listaEditorial();
        return "form_libro.html";
    }
    
    @PostMapping("/registro")
    public String registro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial, ModelMap modelo){
        try {
            libroService.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            modelo.put("Exito", "El libro fue cargado");
        } catch (MyException ex) {
            modelo.put("Error", ex.getMessage());
            Logger.getLogger(LibroControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
            return "form.libro.html";
    }
}

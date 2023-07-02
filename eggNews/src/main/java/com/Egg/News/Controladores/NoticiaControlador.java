package com.Egg.News.Controladores;

import com.Egg.News.Excepciones.MyException;
import com.Egg.News.Servicios.NoticiaServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/") //Configura la url que responda al llamado inicial
public class NoticiaControlador {
    
    //Hacemos la conexion con la pagina (html) de inicio: localhost:8080/
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
    
    //Buscamos un acceso al servicio
    @Autowired
    private NoticiaServicio noticiaService;
    
    //Mostramos la pagina html (formulario) para crear la noticia
    @GetMapping("/formulario")
    public String formularioNoticia(){
        return "Formulario_Crear.html";
    }
    //Impactamos en la BD con los datos del formulario
    @PostMapping("/crear")
    public String crear(@RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo){
        try {
            noticiaService.crearNoticia(titulo, cuerpo);
            modelo.put("Exito", "La noticia fue guardada exitosamente");
            System.out.println("todo esta bien");
        } catch (MyException ex) {
            modelo.put("Error", "ex.getMessage()");
            System.out.println("ocurrio un error");
        }
        return "index.html";
    }
    
    
    /*
    //Configuramos conexion con la accion de modificar
    @GetMapping("/modificar")
    public String modificarNoticia(){
        return "FormularioNoticia.html";
    }
    */
}

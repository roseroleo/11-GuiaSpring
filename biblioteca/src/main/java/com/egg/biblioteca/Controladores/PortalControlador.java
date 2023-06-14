package com.egg.biblioteca.Controladores;

import com.egg.biblioteca.Excepciones.MyException;
import com.egg.biblioteca.Servicio.UsuarioServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/") //Configura la url que recibira el llamado
public class PortalControlador { //localhost:880/
    
    @Autowired
    private UsuarioServicio usuarioService;
    
    @GetMapping("/")
    public String index(){ //Localhost:880/
        return "index.html";
    }
    
     @GetMapping("/registrar")
    public String registrar(){ 
        return "registro.html";
    }
    
    @PostMapping("/registro")
    public String registro(@RequestParam String nombre, @RequestParam String email, 
            @RequestParam String password, @RequestParam String password2, ModelMap modelo){
        try {
            usuarioService.registrar(nombre, email, password, password2);
            modelo.put("exito", "Usuario registrado correactamente");
            return "index.html";
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
        }
        return "registro.html";
        
    }
    
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}

package Servicio;

import Entidades.Autor;
import Repositorio.AutorRepositorio;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {

    //Instanciamos un objeto de la clase autorRespositorio
    @Autowired
    AutorRepositorio autorRepositorio;

    @Transactional
    public void cearAutor(String nombre) {
        //Instancaimos un objeto de la clase Autor
        Autor autor = new Autor();
        //Seteamos
        autor.setNombre(nombre);
        //Persistimos
        autorRepositorio.save(autor);
    }
}

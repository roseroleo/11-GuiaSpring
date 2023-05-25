package Servicio;

import Entidades.Autor;
import Repositorio.AutorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public List<Autor> listaAutor() {
        List<Autor> listaAutores = new ArrayList();
        listaAutores = autorRepositorio.findAll();
        return listaAutores;
    }

    public void modificarAutor(String id, String nombre) {
        Optional<Autor> respAutor = autorRepositorio.findById(id);
        if (respAutor.isPresent()) {
            Autor autor = respAutor.get();
            autor.setNombre(nombre);
            autorRepositorio.save(autor);
        }
    }
}

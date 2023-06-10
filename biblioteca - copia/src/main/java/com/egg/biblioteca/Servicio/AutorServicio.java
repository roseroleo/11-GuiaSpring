package com.egg.biblioteca.Servicio;

import com.egg.biblioteca.Entidades.Autor;
import com.egg.biblioteca.Excepciones.MyException;
import com.egg.biblioteca.Repositorio.AutorRepositorio;
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
    public void cearAutor(String nombre) throws MyException {
        //validarAutor(nombre);
        //Instancaimos un objeto de la clase Autor
        Autor autor = new Autor();
        //Seteamos
        autor.setNombre(nombre);
        //Persistimos
        autorRepositorio.save(autor);
    }
        //Metodo para listar todos los autores de la BD
    public List<Autor> listaAutor() {
        List<Autor> listaAutores = new ArrayList();
        listaAutores = autorRepositorio.findAll();
        return listaAutores;
    }
    //Metodo para modificar un elemento en la BD
    @Transactional
    public void modificarAutor(String id, String nombre) throws MyException {
        //validarAutor(nombre);        
        Optional<Autor> respAutor = autorRepositorio.findById(id);
        if (respAutor.isPresent()) {
            Autor autor = respAutor.get();
            autor.setNombre(nombre);
            autorRepositorio.save(autor);
        }
    }
    /*
    private void validarAutor(String n)throws MyException {
        //MyException ex = new MyException();
        if (n.isEmpty() || n == null){
            throw MyException("El nombre de autor no puede ser nulo");
        }
    }
    */
}

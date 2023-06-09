package com.egg.biblioteca.Servicio;

import com.egg.biblioteca.Entidades.Autor;
import com.egg.biblioteca.Excepciones.MyException;
import com.egg.biblioteca.Repositorio.AutorRepositorio;
import com.egg.biblioteca.Repositorio.EditorialRepositorio;
import com.egg.biblioteca.Repositorio.LibroRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServicio {

    //Instanciamos entidades para acceder a la BD
    @Autowired
    private AutorRepositorio autorRepo;

    @Transactional
    public void cearAutor(String nombre) throws MyException {
        validarAutor(nombre);
        //Instancaimos un objeto de la clase Autor para acceder a sus propiedades
        Autor autor = new Autor();
        //Seteamos
        autor.setNombre(nombre);
        //Persistimos
        autorRepo.save(autor);
    }
        //Metodo para listar todos los autores de la BD
    public List<Autor> listaAutor() {
        List<Autor> listaAutores = new ArrayList();
        listaAutores = autorRepo.findAll();
        return listaAutores;
    }
    //Metodo para modificar un elemento en la BD
    @Transactional
    public void modificarAutor(String id, String nombre) throws MyException {
        //validarAutor(nombre);        
        Optional<Autor> respAutor = autorRepo.findById(id);
        if (respAutor.isPresent()) {
            Autor autor = respAutor.get();
            autor.setNombre(nombre);
            autorRepo.save(autor);
        }
    }
    
    private void validarAutor(String nombre)throws MyException {
        //MyException ex = new MyException();
        if (nombre.isEmpty()){
            throw new MyException("El nombre de autor no puede estar vacio");
        }
    }
    
}

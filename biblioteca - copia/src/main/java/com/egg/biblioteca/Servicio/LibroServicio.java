package com.egg.biblioteca.Servicio;

import com.egg.biblioteca.Entidades.Autor;
import com.egg.biblioteca.Entidades.Editorial;
import com.egg.biblioteca.Entidades.Libro;
import com.egg.biblioteca.Excepciones.MyException;
import com.egg.biblioteca.Repositorio.AutorRepositorio;
import com.egg.biblioteca.Repositorio.EditorialRepositorio;
import com.egg.biblioteca.Repositorio.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {

    //Instanciamos las clases de repositorio (BD)
    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MyException {
        //Validar la entrada correcta de datos
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        //Instanciamos un objeto de las clases LIbro, Autor y Editorial
        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
        Libro libro = new Libro();
        //Seteamos los datos del libro
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());
        //Seteamos los demas datos del libro
        libro.setAutor(autor);
        libro.setEditorial(editorial);

        //Persistimos en la BD el nuevo libro
        libroRepositorio.save(libro);
    }

    public List<Libro> listarLibros() {
        List<Libro> listaLibros = new ArrayList();
        listaLibros = libroRepositorio.findAll();
        return listaLibros;
    }

    @Transactional
    public void modificarLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MyException {
        validar(isbn, titulo, ejemplares, idAutor, idEditorial);
        //Con el Optional verificamos si el isbn del libro existe
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respEditorial = editorialRepositorio.findById(idEditorial);

        //Instanciamos
        Libro libroModificado = new Libro();
        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (respAutor.isPresent()) {
            autor = respAutor.get();
        }
        if (respEditorial.isPresent()) {
            editorial = respEditorial.get();
        }
        if (respuesta.isPresent()) {
            libroModificado = respuesta.get();
            //Seteamos los valores en la entidad
            libroModificado.setTitulo(titulo);
            libroModificado.setAutor(autor);
            libroModificado.setEditorial(editorial);
            libroModificado.setEjemplares(ejemplares);
            //Persistimos
            libroRepositorio.save(libroModificado);
        }
        //Asignamos los valores de las clases Autor y Editorial que sacamos del repositorio
        //autor = autorRepositorio.findById(idAutor).get();
        //editorial = editorialRepositorio.findById(idEditorial).get();
    }

    private void validar(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MyException {
        if (isbn == null) {
            throw new MyException("El ISBN no puede ser nulo");
        }
        if (titulo == null || titulo.isEmpty()) {
            throw new MyException("El titulo no puede ser nulo ni estar vacio");
        }
        if (ejemplares == null) {
            throw new MyException("El numero de ejemplares no puede ser nulo ");
        }
        if (idAutor.isEmpty()) {
            throw new MyException("El IdAutor no puede ser nulo ni estar vacio");
        }
        if (idEditorial == null) {
            throw new MyException("El idEditorial no puede ser nulo ni estar vacio");
        }
    }
}

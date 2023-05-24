package Servicio;

import Entidades.Autor;
import Entidades.Editorial;
import Entidades.Libro;
import Repositorio.AutorRepositorio;
import Repositorio.EditorialRepositorio;
import Repositorio.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) {
        //Instanciamos un objeto (bean) de la clase Libro
        Libro libro = new Libro();
        //Seteamos los datos del libro
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());
        //Instanciamos un objeto de las clases Autor y Editorial
        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();
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
    
    
}

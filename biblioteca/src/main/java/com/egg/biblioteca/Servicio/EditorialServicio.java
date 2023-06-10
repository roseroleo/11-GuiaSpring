package com.egg.biblioteca.Servicio;

import com.egg.biblioteca.Entidades.Editorial;
import com.egg.biblioteca.Repositorio.EditorialRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServicio {

    //Instanciamos
    @Autowired
    EditorialRepositorio eR;

    @Transactional
    public void crearEditorial(String nombre) {

        Editorial ed = new Editorial();
        ed.setNombre(nombre);
        eR.save(ed);
    }

    public List<Editorial> listaEditorial() {
        List<Editorial> listaEditoriales = new ArrayList();
        listaEditoriales = eR.findAll();
        return listaEditoriales;
    }

    @Transactional
    public void modificarEditorial(String id, String nombre) {
        Optional<Editorial> respEditorial = eR.findById(id);
        if (respEditorial.isPresent()) {
            Editorial editorial = respEditorial.get();
            editorial.setNombre(nombre);
            eR.save(editorial);
        }
    }
}

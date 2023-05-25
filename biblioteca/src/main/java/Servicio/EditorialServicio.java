package Servicio;

import Entidades.Editorial;
import Repositorio.EditorialRepositorio;
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
    public void crearEditorial(String editorial) {

        Editorial ed = new Editorial();

        ed.setNombre(editorial);

        eR.save(ed);
    }

    public List<Editorial> listaEditorial() {
        List<Editorial> listaEditoriales = new ArrayList();
        listaEditoriales = eR.findAll();
        return listaEditoriales;
    }

    public void modificarEditorial(String id, String nombre) {
        Optional<Editorial> respEditorial = eR.findById(id);
        if (respEditorial.isPresent()) {
            Editorial editorial = respEditorial.get();
            editorial.setNombre(nombre);
            eR.save(editorial);
        }
    }
}

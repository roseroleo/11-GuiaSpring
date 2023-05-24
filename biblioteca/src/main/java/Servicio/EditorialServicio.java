package Servicio;

import Entidades.Editorial;
import Repositorio.EditorialRepositorio;
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

}

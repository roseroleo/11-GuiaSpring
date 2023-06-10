package com.Egg.News.Servicios;

import com.Egg.News.Excepciones.MyException;
import com.Egg.News.Repositorios.NoticiaRepositorio;
import com.Egg.News.entidades.Noticia;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoticiaServicio {

    @Autowired
    private NoticiaRepositorio noticiaRepo;

    //Anotamos como @Transactional porque impacta la BD
    @Transactional
    public void crearNoticia(String titulo, String cuerpo) throws MyException {
        Noticia n = new Noticia();
        validar(titulo, cuerpo);
        n.setTitulo(titulo);
        n.setCuerpo(cuerpo);
        noticiaRepo.save(n);
    }

//    public Noticia consultarNoticia(String titulo) throws MyException {
//        System.out.println(noticiaRepo.consultarNoticia(titulo).getTitulo());
//        return noticiaRepo.consultarNoticia(titulo);
//        
//    }
    //Metodo de validacion de los datos ingresados
    private void validar(String titulo, String cuerpo) throws MyException {
        if (titulo == null || titulo.isEmpty()) {
            throw new MyException("El titulo no puede ser nulo ni estar vacio");
        }
        if (titulo == null || cuerpo.isEmpty()) {
            throw new MyException("El cuerpo no puede ser nulo ni estar vacio");
        }
    }

}

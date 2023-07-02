package com.Egg.News.Repositorios;

import com.Egg.News.Excepciones.MyException;
import com.Egg.News.entidades.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepositorio extends JpaRepository<Noticia, String>{
    
//    public void crearNoticia(String titulo, String cuerpo);
    
     
//    public void actualizarNoticia(String titulo, String cuerpo)  throws MyException ;
    
    //Metodo de consulta propio para buscar una noticia por titulo
    @Query("SELECT n FROM Noticia n WHERE n.titulo = :titulo")
    public Noticia consultarNoticia(@Param("titulo") String titulo);
    
//    public void eliminarNoticia(String titulo)  throws MyException ;
}

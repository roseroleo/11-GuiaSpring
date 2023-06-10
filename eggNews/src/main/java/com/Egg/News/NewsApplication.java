
package com.Egg.News;

import com.Egg.News.Servicios.NoticiaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewsApplication{ //implements CommandLineRunner { 
    //NoticiaServicio nServicio = null;
    public static void main(String[] args) {
        //@Autowired
//        NoticiaServicio nServicio = null;
        SpringApplication.run(NewsApplication.class, args);
//        nServicio.crearNoticia("Este es el titulo 1", "Este es el cuerpo 1");
//        nServicio.crearNoticia("Este es el titulo 2", "Este es el cuerpo 2");
    }

//    @Override
//    public void run(String... args) throws Exception {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

}

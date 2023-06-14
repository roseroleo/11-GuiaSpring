package com.egg.biblioteca.Servicio;

import com.egg.biblioteca.Entidades.Usuario;
import com.egg.biblioteca.Excepciones.MyException;
import com.egg.biblioteca.Repositorio.UsuarioRepositorio;
import com.egg.biblioteca.enumeraciones.Rol;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicio implements UserDetailsService{

    @Autowired
    private UsuarioRepositorio usuarioRepo;

    @Transactional
    public void registrar(String nombre, String email, String pasword, String pasword2) throws MyException {

        validar(nombre, email, pasword, pasword2);
        
        Usuario user = new Usuario();
        user.setNombre(nombre);
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(pasword));
        user.setRol(Rol.USER);
        usuarioRepo.save(user);
        
    }

    private void validar(String nombre, String email, String password, String pasword2) throws MyException {

        if (nombre == null || nombre.isEmpty()) {
            throw new MyException("El nombre no puede ser nulo ni estar vacio");
        }
        if (email == null || email.isEmpty()) {
            throw new MyException("El Email no puede ser nulo ni estar vacio");
        }
        if (password==null || password.isEmpty() || password.length()<4) {
            throw new MyException("La contraseña debe tener al menos 4 digitos");
        }
        if (!password.equals(pasword2)){
            throw new MyException("La contraseña debe coincidir");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Buscamos un usuario en el Repo que coincida con el email
        Usuario usuario = usuarioRepo.buscarPorEmail(email);
        //Si el usuario existe empazamos a trabajar con Spring Security
        if(usuario != null){
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_"+usuario.getRol().toString());
            permisos.add(p);
        return new User(usuario.getEmail(), usuario.getPassword(), permisos);
        }else{
            return null;
        }
    }
}

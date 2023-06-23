package com.egg.biblioteca;

import com.egg.biblioteca.Servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled=true)
public class WebSecurity extends WebSecurityConfigurerAdapter {
    
    @Autowired
    public UsuarioServicio usuarioService;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder a) throws Exception{
        a.userDetailsService(usuarioService)
                .passwordEncoder(new BCryptPasswordEncoder()); //encriptado de la clave
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http //Configurar usuarios
                .authorizeRequests()
                .antMatchers("/admin/*").hasRole("ADMIN") //Rol de acceso administrativo
                .antMatchers("/css/*","/js/*","/img/*","/**")
                .permitAll()
        .and().formLogin() //configurar login de usuario
                .loginPage("/login")  //Pagina de login (Url a mostrar)
                .loginProcessingUrl("/logincheck") //Url de autenticacion del usuario
                .usernameParameter("email")  //Credencial de acceso
                .passwordParameter("password") //Credencial de acceso
                .defaultSuccessUrl("/inicio")  //Pagina de retorno si todo sale bien
                .permitAll()
        .and().logout() //Configurar salida
                .logoutUrl("/logout")  //Url de cierre de cesion
                .logoutSuccessUrl("/");  //Url de retorno
    }
}


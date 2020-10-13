package com.foro.elrincondelomorbido.controllers;

import javax.servlet.http.HttpServletRequest;

import com.foro.elrincondelomorbido.models.Tema;
import com.foro.elrincondelomorbido.services.TemaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemasController {
    @Autowired
    TemaService temaService;

    @GetMapping("/{nombre_foro}")
    public Iterable<Tema> listarTemas(HttpServletRequest request, @PathVariable String nombre_foro){
        if (request.isUserInRole("ROLE_ADMIN")) {
            return temaService.temasAdmin(nombre_foro);
        }
        return temaService.temas(nombre_foro);
    }
    @PostMapping("/{nombre_foro}")
    public void crearTema(@PathVariable String nombre_foro, @RequestBody Tema tema){
        Long id_usuario = Long.valueOf(2);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            id_usuario = Long.valueOf(1);
        }
        temaService.crearTema(nombre_foro, id_usuario, tema);
    }
    @GetMapping("/{nombre_foro}/{id_tema}")
    public Tema obtenerTema(HttpServletRequest request, @PathVariable Long id_tema){
        //Temas con comentarios
        if (request.isUserInRole("ROLE_ADMIN")) {
            return temaService.obtenerTemaAdmin(id_tema);
        }
        return temaService.obtenerTema(id_tema);
    }
    @PutMapping("/{nombre_foro}/{id_tema}/positivo")
    public void puntuarTemaPositivo(@PathVariable Long id_tema){
        temaService.puntuarTemaPositivo(id_tema);
    }
    @PutMapping("/{nombre_foro}/{id_tema}/negativo")
    public void puntuarTemaNegativo(@PathVariable Long id_tema){
        temaService.puntuarTemaNegativo(id_tema);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{nombre_foro}/{id_tema}/aprobar")
    public void aprobarTema(@PathVariable Long id_tema){
        temaService.aprobarTema(id_tema);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{nombre_foro}/{id_tema}/desaprobar")
    public void desaprobarTema(@PathVariable Long id_tema){
        temaService.desaprobarTema(id_tema);
    }
}

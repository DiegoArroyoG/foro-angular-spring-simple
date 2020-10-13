package com.foro.elrincondelomorbido.controllers;

import com.foro.elrincondelomorbido.models.Comentario;
import com.foro.elrincondelomorbido.services.ComentarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComentariosController {
    @Autowired
    ComentarioService comentarioService;

    @PostMapping("/{nombre_foro}/{id_tema}")
    public void crearComentario(@PathVariable Long id_tema, @RequestBody Comentario comentario){
        Long id_usuario = Long.valueOf(2);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            id_usuario = Long.valueOf(1);
        }
        comentarioService.crearComentario(id_tema, id_usuario, comentario);
    }
    @PutMapping("/{nombre_foro}/{id_tema}/{id_comentario}/positivo")
    public void puntuarComentarioPostivo(@PathVariable Long id_comentario){
        comentarioService.puntuarTemaPositivo(id_comentario);
    }
    @PutMapping("/{nombre_foro}/{id_tema}/{id_comentario}/negativo")
    public void puntuarComentarioNegativo(@PathVariable Long id_comentario){
        comentarioService.puntuarTemaNegativo(id_comentario);
    }
    @PostMapping("/{nombre_foro}/{id_tema}/{id_comentario}")
    public void responderComentario(@PathVariable Long id_tema, @PathVariable Long id_comentario, @RequestBody Comentario comentario){
        Long id_usuario = Long.valueOf(2);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            id_usuario = Long.valueOf(1);
        }
        comentarioService.crearComentario(id_tema, id_comentario, id_usuario, comentario);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{nombre_foro}/{id_tema}/{id_comentario}/aprobar")
    public void aprobarComentario(@PathVariable Long id_comentario){
        comentarioService.aprobarComentario(id_comentario);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{nombre_foro}/{id_tema}/{id_comentario}/desaprobar")
    public void desaprobarComentario(@PathVariable Long id_comentario){
        comentarioService.desaprobarComentario(id_comentario);
    }
}

package com.foro.elrincondelomorbido.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.foro.elrincondelomorbido.models.Comentario;
import com.foro.elrincondelomorbido.models.Tema;
import com.foro.elrincondelomorbido.models.Usuario;
import com.foro.elrincondelomorbido.repositories.ComentarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {
    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    TemaService temaService;
    @Autowired
    UsuarioService usuarioService;


	public void crearComentario(Long id_tema, Long id_usuario, Comentario comentario) {
        Tema tema = temaService.obtenerTema(id_tema);
        Usuario usuario = usuarioService.obtenerUsuario(id_usuario);
        comentario.setTema(tema);
        comentario.setUsuario(usuario);
        comentario.setFecha(Date.from(LocalDateTime.now()
            .atZone(ZoneId.systemDefault())
            .toInstant()));
        comentario.setRanking(0);
        if(tema.getForo().isModerado()) comentario.setAprobado(false);
        else comentario.setAprobado(true);
        comentarioRepository.save(comentario);
        temaService.agregarComentario(id_tema, comentario);
        usuarioService.agregarComentario(id_usuario, comentario);
    }
    
    public void crearComentario(Long id_tema, Long id_comentario, Long id_usuario, Comentario comentario) {
        Tema tema = temaService.obtenerTema(id_tema);
        Usuario usuario = usuarioService.obtenerUsuario(id_usuario);
        comentario.setTema(tema);
        comentario.setUsuario(usuario);
        comentario.setFecha(Date.from(LocalDateTime.now()
            .atZone(ZoneId.systemDefault())
            .toInstant()));
        comentario.setRanking(0);
        if(tema.getForo().isModerado()) comentario.setAprobado(false);
        else comentario.setAprobado(true);
        comentarioRepository.save(comentario);
        agregarComentario(id_comentario, comentario);
        usuarioService.agregarComentario(id_usuario, comentario);
	}

	public void puntuarTemaPositivo(Long id) {
        Comentario comentario = comentarioRepository.findById(id).orElseThrow();
        comentario.setRanking(comentario.getRanking()+1);
        comentarioRepository.save(comentario);
	}

	public void puntuarTemaNegativo(Long id) {
        Comentario comentario = comentarioRepository.findById(id).orElseThrow();
        comentario.setRanking(comentario.getRanking()-1);
        comentarioRepository.save(comentario);
    }
    
    public void agregarComentario(Long id, Comentario comentario){
        Comentario comentarioPadre = comentarioRepository.findById(id).orElseThrow();
        comentarioPadre.addComentarios(comentario);
        comentarioRepository.save(comentarioPadre);
    }

	public void aprobarComentario(Long id) {
        Comentario comentario = comentarioRepository.findById(id).orElseThrow();
        comentario.setAprobado(true);
        comentarioRepository.save(comentario);
	}

	public void desaprobarComentario(Long id) {
        comentarioRepository.deleteById(id);
	}
    
}

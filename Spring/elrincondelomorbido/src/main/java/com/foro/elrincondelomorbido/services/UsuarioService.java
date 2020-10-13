package com.foro.elrincondelomorbido.services;

import com.foro.elrincondelomorbido.models.Comentario;
import com.foro.elrincondelomorbido.models.Tema;
import com.foro.elrincondelomorbido.models.Usuario;
import com.foro.elrincondelomorbido.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public Usuario obtenerUsuario(Long id){
        return usuarioRepository.findById(id).orElseThrow();
    }
    public void agregarTema(Long id, Tema tema){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        usuario.addTemas(tema);
        usuarioRepository.save(usuario);
    }
    public void agregarComentario(Long id, Comentario comentario){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        usuario.addComentarios(comentario);
        usuarioRepository.save(usuario);
    }
}

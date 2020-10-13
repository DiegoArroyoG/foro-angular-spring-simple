package com.foro.elrincondelomorbido.repositories;

import com.foro.elrincondelomorbido.models.Usuario;

import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    
}

package com.foro.elrincondelomorbido.repositories;

import com.foro.elrincondelomorbido.models.Comentario;

import org.springframework.data.repository.CrudRepository;

public interface ComentarioRepository extends CrudRepository<Comentario, Long>{
    
}

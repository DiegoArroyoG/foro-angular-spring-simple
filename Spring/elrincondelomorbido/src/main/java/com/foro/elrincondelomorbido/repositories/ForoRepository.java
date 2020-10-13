package com.foro.elrincondelomorbido.repositories;

import com.foro.elrincondelomorbido.models.Foro;

import org.springframework.data.repository.CrudRepository;

public interface ForoRepository extends CrudRepository<Foro, String> {
    
}

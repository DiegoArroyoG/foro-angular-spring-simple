package com.foro.elrincondelomorbido.controllers;

import com.foro.elrincondelomorbido.models.Foro;
import com.foro.elrincondelomorbido.services.ForoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForosController {
    @Autowired
    ForoService foroService;
    @GetMapping("/")
    public Iterable<Foro> listarForos(){
        return foroService.foros();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public void crearForo(@RequestBody Foro foro){
        foroService.crearForo(foro);
    }
}

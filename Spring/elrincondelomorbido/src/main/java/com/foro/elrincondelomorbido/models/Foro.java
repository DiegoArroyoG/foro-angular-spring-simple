package com.foro.elrincondelomorbido.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Foro {
    @Id
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Tema> temas;
    @ManyToMany
    @JsonIgnore
    private List<Usuario> moderadores;
    private boolean moderado;

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public List<Tema> getTemas() {
        return temas;
    }
    public void setTemas(List<Tema> temas) {
        this.temas = temas;
    }
    public void addTemas(Tema tema) {
        this.temas.add(tema);
    }
    public boolean isModerado() {
        return moderado;
    }
    public void setModerado(boolean moderado) {
        this.moderado = moderado;
    }
    public List<Usuario> getModeradores() {
        return moderadores;
    }
    public void setModeradores(List<Usuario> moderadores) {
        this.moderadores = moderadores;
    }
    public void addModeradores(Usuario usuario) {
        this.moderadores.add(usuario);
    }
}

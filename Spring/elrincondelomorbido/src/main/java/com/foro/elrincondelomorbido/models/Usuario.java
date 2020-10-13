package com.foro.elrincondelomorbido.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Tema> temas;
    @ManyToMany
    @JsonIgnore
    private List<Foro> moderado;
    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comentario> comentarios;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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
    public List<Foro> getModerado() {
        return moderado;
    }
    public void addForo(Foro foro) {
        this.moderado.add(foro);
    }
    public void setModerado(List<Foro> moderado) {
        this.moderado = moderado;
    }
    public List<Comentario> getComentarios() {
        return comentarios;
    }
    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }
    public void addComentarios(Comentario comentario) {
        this.comentarios.add(comentario);
    }
}

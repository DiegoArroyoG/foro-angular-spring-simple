package com.foro.elrincondelomorbido.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comentario implements Hidable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Tema tema;
    @ManyToOne
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comentario> comentarios;
    private Date fecha;
    private String contenido;
    private Integer ranking;
    private boolean aprobado;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Tema getTema() {
        return tema;
    }
    public void setTema(Tema tema) {
        this.tema = tema;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
    public Integer getRanking() {
        return ranking;
    }
    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
    public boolean isAprobado() {
        return aprobado;
    }
    public void setAprobado(boolean aprobado) {
        this.aprobado = aprobado;
    }
    @Override
    public boolean isHidden() {
        return !aprobado;
    }
}

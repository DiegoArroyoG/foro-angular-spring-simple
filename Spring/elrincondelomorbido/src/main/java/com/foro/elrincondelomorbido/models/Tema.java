package com.foro.elrincondelomorbido.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tema implements Hidable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Foro foro;
    @ManyToOne
    @JsonIgnore
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Comentario> comentario;
    @Column(nullable = false, name = "fecha_publicacion")
    private Date fechaPublicacion;
    @Column(nullable = false)
    private String titulo;
    private String contenido;
    private Integer ranking;
    private boolean aprobado;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Foro getForo() {
        return foro;
    }
    public void setForo(Foro foro) {
        this.foro = foro;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }
    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public List<Comentario> getComentario() {
        return comentario;
    }

    public void setComentario(List<Comentario> comentario) {
        this.comentario = comentario;
    }
    public void addComentario(Comentario comentario) {
        this.comentario.add(comentario);
    }

    @Override
    public boolean isHidden() {
        return !aprobado;
    }
}

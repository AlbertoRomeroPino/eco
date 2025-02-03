package org.example.model.entity;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "actividad")
public class Actividad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_actividad", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private org.example.model.entity.Categoria idCategoria;

    @OneToMany(mappedBy = "idActividad")
    private Set<org.example.model.entity.Habito> habitos = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idActividad")
    private Set<org.example.model.entity.Huella> huellas = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public org.example.model.entity.Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(org.example.model.entity.Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Set<org.example.model.entity.Habito> getHabitos() {
        return habitos;
    }

    public void setHabitos(Set<org.example.model.entity.Habito> habitos) {
        this.habitos = habitos;
    }

    public Set<org.example.model.entity.Huella> getHuellas() {
        return huellas;
    }

    public void setHuellas(Set<org.example.model.entity.Huella> huellas) {
        this.huellas = huellas;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", idCategoria=" + idCategoria.getNombre() +
                ", habitos=" + habitos +
                ", huellas=" + huellas +
                '}';
    }
}
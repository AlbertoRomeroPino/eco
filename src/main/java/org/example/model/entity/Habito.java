package org.example.model.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "habito")
public class Habito {
    @EmbeddedId
    private HabitoId id;

    @MapsId("idUsuario")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_usuario", nullable = false)
    private org.example.model.entity.Usuario idUsuario;

    @MapsId("idActividad")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_actividad", nullable = false)
    private Actividad idActividad;

    @Column(name = "frecuencia", nullable = false)
    private Integer frecuencia;

    @Lob
    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "ultima_fecha")
    private Instant ultimaFecha;

    public HabitoId getId() {
        return id;
    }

    public void setId(HabitoId id) {
        this.id = id;
    }

    public org.example.model.entity.Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(org.example.model.entity.Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Actividad getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Actividad idActividad) {
        this.idActividad = idActividad;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(Integer frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Instant getUltimaFecha() {
        return ultimaFecha;
    }

    public void setUltimaFecha(Instant ultimaFecha) {
        this.ultimaFecha = ultimaFecha;
    }


    @Override
    public String toString() {
        return "Habito{" +
                " idUsuario=" + idUsuario.getNombre() +
                ", idActividad=" + idActividad.getNombre() +
                ", frecuencia=" + frecuencia +
                ", tipo='" + tipo + '\'' +
                ", ultimaFecha=" + ultimaFecha +
                '}';
    }
}
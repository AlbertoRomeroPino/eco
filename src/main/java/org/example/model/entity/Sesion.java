package org.example.model.entity;

public class Sesion {
    private Usuario usuario;
    private static Sesion _sessionStarted;

    public static Sesion getSesion() {
        if (_sessionStarted == null) {
            _sessionStarted = new Sesion();
        }
        return _sessionStarted;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void close(){
        _sessionStarted = null;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

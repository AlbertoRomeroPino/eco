package org.example.controller;

import org.example.model.dao.UsuarioDao;
import org.example.model.entity.Sesion;
import org.example.model.entity.Usuario;
import org.example.utils.Utils;
import org.example.utils.Validacion;
import org.example.viewTerminal.View;

import java.time.Instant;

public class Controlador {
    public static void Iniciar() {


        int result;

        do {
            result = View.SeleccionInicial();

            switch (result) {
                case 1:
                    ComprobarLogin();
                    break;
                case 2:
                    RegistrarUsuario();
                    break;
                case 3:
                    View.Despedida();
                    break;
            }

        } while (result != 3) ;
    }

    public static void ComprobarLogin() {
        String nombre = Utils.leeString("Inserte el nombre de usuario");
        String contraseña = Utils.leeString("Inserte tu contraseña");

        String contraseñaEncriptada = Validacion.encryptPassword(contraseña);

        Usuario usuarioDB = UsuarioDao.BuscarNombreUsuario(nombre);

        if (usuarioDB == null) {
            View.OpcionNoAdecuada("Usuario no encontrado");
        } else {
            if (usuarioDB.getContraseña().equals(contraseñaEncriptada)) {
                Sesion.getSesion().setUsuario(usuarioDB);
                System.out.println("Bienvenido, " + usuarioDB.getNombre());

                if (!Sesion.getSesion().getUsuario().getNombre().isEmpty() &&
                        !Sesion.getSesion().getUsuario().getContraseña().isEmpty()) {
                    SesionIniciada();
                }
            } else {
                // Si la contraseña es incorrecta
                System.out.println("Contraseña incorrecta");
            }
        }
    }

    public static void RegistrarUsuario() {
        Usuario usuario = new Usuario();
        String nombreRegistrar = Utils.leeString("Inserte el nombre de usuario");
        String contraseñaRegistrar = Utils.leeString("Inserte la contraseña");
        String emailRegistrar = Utils.leeString("Inserte el email de usuario");

        usuario.setNombre(nombreRegistrar);
        usuario.setContraseña(Validacion.encryptPassword(contraseñaRegistrar));
        usuario.setEmail(emailRegistrar);
        usuario.setFechaRegistro(Instant.now());
        UsuarioDao.InsertarUsuario(usuario);
    }

    public static void SesionIniciada() {
        int result;
        boolean usuarioBorrado = false;
        do {
            result = View.SesionIniciada();

            switch (result) {
                case 1:
                    usuarioBorrado = ControladorUsuario.EditarUsuario();
                    break;
                case 2:
                    ControladorHabito.ControlHabito();
                    break;
                case 3:
                    break;
                case 4:
                    break;
            }
        } while (result != 4 && !usuarioBorrado);

    }
}

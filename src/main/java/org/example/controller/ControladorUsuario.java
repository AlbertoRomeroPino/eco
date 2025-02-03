package org.example.controller;

import org.example.model.dao.UsuarioDao;
import org.example.model.entity.Sesion;
import org.example.utils.Utils;
import org.example.utils.Validacion;
import org.example.viewTerminal.View;

public class ControladorUsuario {
    public static boolean EditarUsuario() {
        int arg;
        boolean usuarioBorrado = false;
        do {
            arg = View.ModificarUsuario();
            switch (arg) {
                case 1:
                    String nombre = Utils.leeString("Nuevo nombre: ");
                    Sesion.getSesion().getUsuario().setNombre(nombre);
                    System.out.println("Usuario editado :\n" + Sesion.getSesion().getUsuario());
                    UsuarioDao.ActualizarUsuario(Sesion.getSesion().getUsuario());
                    break;
                case 2:
                    String contraseña = Validacion.encryptPassword(Utils.leeString("Nueva contraseña"));
                    Sesion.getSesion().getUsuario().setContraseña(contraseña);
                    System.out.println("Usuario editado :\n" + Sesion.getSesion().getUsuario());
                    UsuarioDao.ActualizarUsuario(Sesion.getSesion().getUsuario());
                    break;
                case 3:
                    String correo = Utils.leeString("Nuevo correo: ");
                    Sesion.getSesion().getUsuario().setEmail(correo);
                    System.out.println("Usuario editado :\n" + Sesion.getSesion().getUsuario());
                    UsuarioDao.ActualizarUsuario(Sesion.getSesion().getUsuario());
                    break;
                case 4:
                    String borrar = Utils.leeString("¿Seguro desea borrar el usuario?.\n Ponga si para borrar el usuario?");
                    if (borrar.equalsIgnoreCase("si")) {
                        UsuarioDao.EliminarUsuario(Sesion.getSesion().getUsuario());
                        usuarioBorrado = true;
                        break;
                    }
            }
        } while (!usuarioBorrado && arg != 5);
        return usuarioBorrado;
    }
}

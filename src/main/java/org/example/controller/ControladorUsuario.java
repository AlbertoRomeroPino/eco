package org.example.controller;

import org.example.services.UsuarioService;
import org.example.utils.Utils;
import org.example.viewTerminal.ViewUsuario;

public class ControladorUsuario {
    public static boolean EditarUsuario() {
        int arg;
        boolean usuarioBorrado = false;
        do {
            arg = ViewUsuario.ModificarUsuario();
            switch (arg) {
                case 1:
                    //Nombre
                    UsuarioService.EditarNombreUsuario();
                    break;
                case 2:
                    // Contraseña
                    UsuarioService.EditarContraseñaUsuario();
                    break;
                case 3:
                    // Correo
                    UsuarioService.EditarEmailUsuario();
                    break;
                case 4:
                    String borrar = Utils.leeString("¿Seguro desea borrar el usuario?.\n Ponga si para borrar el usuario?");
                    if (borrar.equalsIgnoreCase("si")) {
                        UsuarioService.EliminarUsuario();
                        usuarioBorrado = true;
                        break;
                    }
            }
        } while (!usuarioBorrado && arg != 5);
        return usuarioBorrado;
    }
}

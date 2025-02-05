package org.example.controller;

import org.example.model.dao.HabitoDao;
import org.example.model.entity.Habito;
import org.example.services.UsuarioService;
import org.example.viewTerminal.ViewControlador;

import java.util.List;

public class Controlador {
    public static void Iniciar() {
        int result;

        do {
            result = ViewControlador.SeleccionInicial();

            switch (result) {
                case 1:
                    UsuarioService.ComprobarLogin();
                    break;
                case 2:
                    UsuarioService.RegistrarUsuario();
                    break;
                case 3:
                    ViewControlador.Despedida();
                    break;
            }

        } while (result != 3);
    }

    public static void SesionIniciada() {
        int result;
        boolean usuarioBorrado = false;
        do {
            result = ViewControlador.SesionIniciada();

            switch (result) {
                case 1:
                    usuarioBorrado = ControladorUsuario.EditarUsuario();
                    break;
                case 2:
                    ControladorHabito.ControlHabito();
                    break;
                case 3:
                    ControladorHuella.ControladorHuella();
                    break;
                case 4:
                    RecomendacionHabito();
                    break;
                case 5:

            }
        } while (result != 5 && !usuarioBorrado);

    }

    public static void RecomendacionHabito() {
        List<Habito> habito = HabitoDao.BuscarHabito();
        for (Habito habitoDB : habito) {
            System.out.println(habitoDB.getIdActividad().getNombre() + ":");
            System.out.println(habitoDB.getIdActividad().getIdCategoria().getRecomendacions());
        }
    }
}

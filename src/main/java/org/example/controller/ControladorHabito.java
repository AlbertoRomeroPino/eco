package org.example.controller;

import org.example.services.HabitoService;
import org.example.viewTerminal.ViewHabito;

public class ControladorHabito {
    public static void ControlHabito() {
        int arg;

        do {
            arg = ViewHabito.VistaHabito();

            switch (arg) {
                case 1:
                    //Crear un Habito
                    HabitoService.CreateHabito();
                    break;
                case 2:
                    // Actualizar un Habito
                    HabitoService.ActualizarHabito();
                    break;
                case 3:
                    // Mostrar todos los Habitos
                    HabitoService.MostrarHabito();
                    break;
                case 4:
                    // Borrar un Habito
                    HabitoService.EliminarHabito();
                    break;
                case 5:
                    // Salir
            }
        } while (arg != 5);

    }
}

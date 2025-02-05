package org.example.viewTerminal;

import org.example.model.entity.Actividad;
import org.example.utils.Utils;

import java.util.Scanner;

public class ViewHabito {
    public static int VistaHabito() {

        int arg;
        do {
            System.out.println("1. Crear nuevo habito");
            System.out.println("2. Modificar habito");
            System.out.println("3. Mostrar habitos");
            System.out.println("4. Eliminar habito");
            System.out.println("5. Salir ");
            arg = Utils.leeNumero("Ingresa un numero");
        } while (arg < 1 || arg > 5);
        return arg;
    }

    public static void MostrarActividades(Actividad actividad) {
        System.out.println("Id = " + actividad.getId() + " " + actividad.getNombre());
    }
}

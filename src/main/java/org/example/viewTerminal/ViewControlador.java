package org.example.viewTerminal;

import org.example.utils.Utils;

import java.util.Scanner;

public class ViewControlador {
    /**
     * 1 Ingresar Usuario | 2 Registrarse | 3 Salir
     *
     * @return el numero seleccionado
     */
    public static int SeleccionInicial() {
        int arg;
        do {
            System.out.println("Que desea hacer:");
            System.out.println("1. Ingresar usuario");
            System.out.println("2. Registrarse en la aplicacion");
            System.out.println("3. Salir");
            arg = Utils.leeNumero("Ingrese un numero");
        } while (arg < 1 || arg > 3);
        return arg;
    }

    public static int SesionIniciada() {
        int arg;
        do {
            System.out.println("1. Modificar usuario");
            System.out.println("2. Mis habitos");
            System.out.println("3. Mi huella");
            System.out.println("4. Recomendaciones para ti");
            System.out.println("5. Salir");
            arg = Utils.leeNumero("Ingrese un numero");
        } while (arg < 1 || arg > 5);
        return arg;
    }

    public static void OpcionNoAdecuada(String arg) {
        System.out.println(arg);
    }

    public static void Despedida() {
        System.out.println("Adios");
    }
}

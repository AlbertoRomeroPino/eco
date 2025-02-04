package org.example.viewTerminal;

import java.util.Scanner;

public class ViewControlador {
    /**
     * 1 Ingresar Usuario | 2 Registrarse | 3 Salir
     *
     * @return el numero seleccionado
     */
    public static int SeleccionInicial() {
        Scanner sc = new Scanner(System.in);
        int arg;
        do {
            System.out.println("Que desea hacer:");
            System.out.println("1. Ingresar usuario");
            System.out.println("2. Registrarse en la aplicacion");
            System.out.println("3. Salir");
            arg = sc.nextInt();
        } while (arg < 1 || arg > 3);
        return arg;
    }

    public static int SesionIniciada() {
        Scanner sc = new Scanner(System.in);
        int arg;
        do {
            System.out.println("1. Modificar usuario");
            System.out.println("2. Mis habitos");
            System.out.println("3. Mi huella");
            System.out.println("4. Salir");
            arg = sc.nextInt();
        } while (arg < 1 || arg > 4);
        return arg;
    }

    public static void OpcionNoAdecuada(String arg) {
        System.out.println(arg);
    }

    public static void Despedida() {
        System.out.println("Adios");
    }
}

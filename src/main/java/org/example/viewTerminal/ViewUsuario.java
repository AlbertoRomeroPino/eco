package org.example.viewTerminal;

import java.util.Scanner;

public class ViewUsuario {
    public static int ModificarUsuario() {
    Scanner sc = new Scanner(System.in);
    int arg;
    do {
        System.out.println("1. Modificar nombre ");
        System.out.println("2. Modificar contraseña ");
        System.out.println("3. Modificar email ");
        System.out.println("4. Eliminar Usuario");
        System.out.println("5. Salir ");
        arg = sc.nextInt();
    } while (arg < 1 || arg > 5);
    return arg;
}
}

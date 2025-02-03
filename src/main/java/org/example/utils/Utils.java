package org.example.utils;

import java.util.Scanner;

public class Utils {

    public static int leeNumero(String arg){
        System.out.println(arg);
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static String leeString(String arg){
        System.out.println(arg);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }


}

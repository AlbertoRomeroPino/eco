package org.example.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Utils {

    public static int leeNumero(String arg) {
        System.out.println(arg);
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static String leeString(String arg) {
        System.out.println(arg);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public static Instant leeFecha() {
        Scanner sc = new Scanner(System.in);

        // Pide cada componente por separado con valores por defecto si está vacío
        System.out.println("Introduce la fecha y hora (si dejas vacío, se tomará 00 en ese campo).");

        // Año, Mes, Día, Hora, Minuto, Segundo con valores por defecto
        String anio = pedirDato(sc, "Año (ejemplo: 2024)", "2024");
        String mes = pedirDato(sc, "Mes (ejemplo: 02)", "01");
        String dia = pedirDato(sc, "Día (ejemplo: 03)", "01");
        String hora = pedirDato(sc, "Hora (ejemplo: 15)", "00");
        String minuto = pedirDato(sc, "Minuto (ejemplo: 30)", "00");
        String segundo = pedirDato(sc, "Segundo (ejemplo: 00)", "00");

        // Construir la cadena con el formato adecuado (yyyy-MM-dd HH:mm:ss)
        String fechaCompleta = anio + "-" + mes + "-" + dia + " " + hora + ":" + minuto + ":" + segundo;

        // Mostrar la fecha generada
        System.out.println("Fecha generada: " + fechaCompleta);

        // Definir el formato esperado de la fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Convertir la cadena a LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(fechaCompleta, formatter);

        // Convertir LocalDateTime a Instant (usando UTC como zona horaria)
        Instant instant = localDateTime.atZone(java.time.ZoneOffset.UTC).toInstant();

        // Retornar el Instant
        return instant;
    }

    private static String pedirDato(Scanner sc, String mensaje, String valorPorDefecto) {
        System.out.print(mensaje + ": ");
        String entrada = sc.nextLine().trim();
        return entrada.isEmpty() ? valorPorDefecto : entrada;
    }

}

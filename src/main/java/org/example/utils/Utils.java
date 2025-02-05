package org.example.utils;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Utils {

    private static final Scanner sc = new Scanner(System.in);

    public static int leeNumero(String arg) {
        while (true) {
            System.out.print(arg + ": ");
            String entrada = sc.nextLine().trim();

            if (entrada.matches("-?\\d+")) { // Verifica si la entrada es un número entero
                return Integer.parseInt(entrada);
            } else {
                System.out.println("Error: Debes ingresar un número entero válido.");
            }
        }
    }

    public static String leeString(String arg) {
        System.out.print(arg + ": ");
        return sc.nextLine().trim();
    }

    public static BigDecimal leeBigDecimal(String arg) {
        while (true) {
            System.out.print(arg + ": ");
            String entrada = sc.nextLine().trim().replace(",", ".");

            if (entrada.matches("-?\\d+(\\.\\d+)?")) { // Verifica si es un número decimal
                return new BigDecimal(entrada);
            } else {
                System.out.println("Error: Debes ingresar un número decimal válido.");
            }
        }
    }

    public static Instant leeFecha() {
        System.out.println("Introduce la fecha y hora (si dejas vacío, se tomará el valor por defecto en cada campo).");

        String anio = pedirDatoNumerico("Año (ejemplo: 2024)", "2024", 4);
        String mes = pedirDatoNumerico("Mes (ejemplo: 02)", "01", 2);
        String dia = pedirDatoNumerico("Día (ejemplo: 03)", "01", 2);
        String hora = pedirDatoNumerico("Hora (ejemplo: 15)", "00", 2);
        String minuto = pedirDatoNumerico("Minuto (ejemplo: 30)", "00", 2);
        String segundo = pedirDatoNumerico("Segundo (ejemplo: 00)", "00", 2);

        String fechaCompleta = anio + "-" + mes + "-" + dia + " " + hora + ":" + minuto + ":" + segundo;

        System.out.println("Fecha generada: " + fechaCompleta);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime localDateTime = LocalDateTime.parse(fechaCompleta, formatter);

        return localDateTime.atZone(java.time.ZoneOffset.UTC).toInstant();
    }

    private static String pedirDatoNumerico(String mensaje, String valorPorDefecto, int longitudEsperada) {
        while (true) {
            System.out.print(mensaje + ": ");
            String entrada = sc.nextLine().trim();

            if (entrada.isEmpty()) {
                return valorPorDefecto;
            }

            if (entrada.matches("\\d{" + longitudEsperada + "}")) {
                return entrada;
            }

            System.out.println("Error: Ingresa un número válido de " + longitudEsperada + " dígitos.");
        }
    }
}

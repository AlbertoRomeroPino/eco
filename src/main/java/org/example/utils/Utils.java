package org.example.utils;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
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


        int anio = pedirDatoNumerico("Año (ejemplo: 2024)", 2024, 1000, 9999);
        int mes = pedirDatoNumerico("Mes (ejemplo: 02)", 1, 1, 12);
        int dia = pedirDatoNumerico("Día (ejemplo: 03)", 1, 1, obtenerDiasDelMes(anio, mes));
        int hora = pedirDatoNumerico("Hora (ejemplo: 15)", 0, 0, 23);
        int minuto = pedirDatoNumerico("Minuto (ejemplo: 30)", 0, 0, 59);
        int segundo = pedirDatoNumerico("Segundo (ejemplo: 00)", 0, 0, 59);

        String fechaCompleta = String.format("%04d-%02d-%02d %02d:%02d:%02d", anio, mes, dia, hora, minuto, segundo);
        System.out.println("Fecha generada: " + fechaCompleta);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(fechaCompleta, formatter);

        System.out.println("Fecha en formato Instant: " + localDateTime.atZone(ZoneOffset.UTC).toInstant());

        return localDateTime.atZone(ZoneOffset.UTC).toInstant();
    }

    private static int pedirDatoNumerico(String mensaje, int valorPorDefecto, int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int numero;
        while (true) {
            System.out.print(mensaje + " (Entre " + min + " y " + max + "): ");
            String entrada = scanner.nextLine().trim();
            try {
                numero = Integer.parseInt(entrada.isEmpty() ? String.valueOf(valorPorDefecto) : entrada);
                if (numero >= min && numero <= max) {
                    return numero;
                } else {
                    System.out.println("❌ Error: El valor debe estar entre " + min + " y " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Ingresa un número válido.");
            }
        }
    }

    public static int obtenerDiasDelMes(int anio, int mes) {
        return YearMonth.of(anio, mes).lengthOfMonth();
    }
}

package org.example.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacion {
    public static String encryptPassword(String password) {
        String hexString = null;

        try {

            MessageDigest digest = MessageDigest.getInstance("SHA3-256");

            byte[] hash = digest.digest(password.getBytes());

            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexStringBuilder.append('0');
                }
                hexStringBuilder.append(hex);
            }

            hexString = hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString;
    }


    public static boolean validarCorreo(String correo) {
        // Expresión regular para permitir solo Gmail y Hotmail con dominios .com o .es
        String regex = "^[a-zA-Z0-9._%+-]+@(gmail|hotmail)\\.(com|es)$";

        // Compilar la expresión regular
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(correo);
        if (!matcher.matches()){
            System.out.println("El correo no es correcto");
        }
        return matcher.matches(); // Devuelve true si el correo es válido
    }

}

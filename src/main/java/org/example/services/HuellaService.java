package org.example.services;

import org.example.model.dao.ActividadDao;
import org.example.model.dao.HuellaDao;
import org.example.model.entity.Actividad;
import org.example.model.entity.Huella;
import org.example.model.entity.Sesion;
import org.example.utils.Utils;
import org.example.viewTerminal.ViewHabito;

import java.math.BigDecimal;
import java.util.List;

public class HuellaService {
    public static void CreateHuella() {
        Huella huellaCrear = new Huella();


        huellaCrear.setIdUsuario(Sesion.getSesion().getUsuario());

        // Mostramos todos las actividades para que el usuario elija cualj quiere
        List<Actividad> actividadList = ActividadDao.BuscarTodasActividades();
        for (Actividad actividad : actividadList) {
            ViewHabito.MostrarActividades(actividad);
        }

        int actividadId = Utils.leeNumero("Inserte el id de la actividad");
        if (ActividadDao.BuscarPorId(actividadId) != null) {
            Actividad actividad = ActividadDao.BuscarPorId(actividadId);
            huellaCrear.setIdActividad(actividad);
            huellaCrear.setValor(Utils.leeBigDecimal("Inserte el valor"));
            huellaCrear.setUnidad(actividad.getIdCategoria().getUnidad());
            huellaCrear.setFecha(Utils.leeFecha());

            HuellaDao.InsertHuella(huellaCrear);
        }else {
            System.err.println("El id de la actividad no existe");
        }

    }

    public static void ActualizarHuella() {
        int idHuella = Utils.leeNumero("Inserte el id de la huella");

        Huella huellaActualizar = HuellaDao.BuscarHuellaId(idHuella);

        huellaActualizar.setValor(Utils.leeBigDecimal("Inserte el nuevo valor"));
        String borrar = Utils.leeString("Quieres editar la fecha de la huella \n Inserte si para editarlo");
        if (borrar.equalsIgnoreCase("si")) {
            huellaActualizar.setFecha(Utils.leeFecha());
        }
        HuellaDao.ActualizarHuella(huellaActualizar);

    }

    public static void MostrarHuella() {
        List<Huella> huellas = HuellaDao.BuscarHuella();
        for (Huella huella : huellas) {
            System.out.println(huella);
        }
    }

    public static void EliminarHuella() {
        Huella huella = HuellaDao.BuscarHuellaId(Utils.leeNumero("Inserte el id de la huella"));
        if (huella != null) {
            HuellaDao.EliminarHuella(huella);
        }else {
            System.err.println("El id de la huella no existe");
        }
    }

    public static void CompararHuella() {
        // Pedir al usuario que ingrese el ID de la huella
        int idHuella = Utils.leeNumero("Inserte el ID de la huella");

        // Buscar la huella seleccionada por el usuario
        Huella huellaAComparar = HuellaDao.BuscarHuellaId(idHuella);

        if (huellaAComparar == null) {
            System.out.println("No se encontró la huella con ese ID.");
            return;
        }

        // Obtener el factor de emisión de la categoría
        BigDecimal factorEmision = huellaAComparar.getIdActividad().getIdCategoria().getFactorEmision();
        BigDecimal valorHuella = huellaAComparar.getValor();
        BigDecimal carbonoUsuario = factorEmision.multiply(valorHuella);

        // Obtener todas las huellas de la misma categoría
        List<Huella> huellas = HuellaDao.BuscarPorActividad(huellaAComparar.getIdActividad());

        // Variables para calcular la media de otros usuarios
        BigDecimal sumaCarbono = BigDecimal.ZERO;
        int contador = 0;

        for (Huella huella : huellas) {
            if (!huella.getIdUsuario().equals(huellaAComparar.getIdUsuario())) {
                BigDecimal carbonoOtroUsuario = huella.getValor().multiply(huella.getIdActividad().getIdCategoria().getFactorEmision());
                sumaCarbono = sumaCarbono.add(carbonoOtroUsuario);
                contador++;
            }
        }

        // Calcular la media de huella de carbono de otros usuarios
        BigDecimal mediaCarbono = (contador > 0) ? sumaCarbono.divide(BigDecimal.valueOf(contador), 2, BigDecimal.ROUND_HALF_UP) : BigDecimal.ZERO;

        // Mostrar resultados
        System.out.println("\nComparación de huella de carbono");
        System.out.println("--------------------------------");
        System.out.println("Categoría: " + huellaAComparar.getIdActividad().getIdCategoria().getNombre());
        System.out.println("Tu huella de carbono: " + carbonoUsuario + " " + huellaAComparar.getIdActividad().getIdCategoria().getUnidad());

        if (contador > 0) {
            System.out.println("Media de otros usuarios: " + mediaCarbono + " " + huellaAComparar.getIdActividad().getIdCategoria().getUnidad());

            if (carbonoUsuario.compareTo(mediaCarbono) > 0) {
                System.out.println("⚠️ Tu huella de carbono está por ENCIMA de la media.");
            } else if (carbonoUsuario.compareTo(mediaCarbono) < 0) {
                System.out.println("✅ Tu huella de carbono está por DEBAJO de la media.");
            } else {
                System.out.println("🔵 Tu huella de carbono es IGUAL a la media.");
            }
        } else {
            System.out.println("No hay datos suficientes para calcular la media.");
        }
    }

}

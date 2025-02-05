package org.example.services;

import org.example.model.dao.ActividadDao;
import org.example.model.dao.HuellaDao;
import org.example.model.entity.Actividad;
import org.example.model.entity.Huella;
import org.example.model.entity.Sesion;
import org.example.utils.Utils;
import org.example.viewTerminal.ViewHabito;

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
        Actividad actividad = ActividadDao.BuscarPorId(Utils.leeNumero("Inserte el id de la actividad"));
        huellaCrear.setIdActividad(actividad);
        huellaCrear.setValor(Utils.leeBigDecimal("Inserte el valor"));
        huellaCrear.setUnidad(actividad.getIdCategoria().getUnidad());
        huellaCrear.setFecha(Utils.leeFecha());

        HuellaDao.InsertHuella(huellaCrear);
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
        HuellaDao.EliminarHuella(huella);
    }

}

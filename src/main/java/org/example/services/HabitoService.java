package org.example.services;

import org.example.model.dao.ActividadDao;
import org.example.model.dao.HabitoDao;
import org.example.model.dao.UsuarioDao;
import org.example.model.entity.Actividad;
import org.example.model.entity.Habito;
import org.example.model.entity.HabitoId;
import org.example.model.entity.Sesion;
import org.example.utils.Utils;
import org.example.viewTerminal.ViewHabito;

import java.util.List;

public class HabitoService {
    public static void CreateHabito() {
        Habito habitoCrear = new Habito();
        HabitoId id = new HabitoId();

        // Insertamos el id de usuario a habitoId
        id.setIdUsuario(Sesion.getSesion().getUsuario().getId());

        // Mostramos todos las actividades para que el usuario elija cualj quiere
        List<Actividad> actividadList = ActividadDao.BuscarTodasActividades();
        for (Actividad actividad : actividadList) {
            ViewHabito.MostrarActividades(actividad);
        }
        int idActividad = Utils.leeNumero("Inserte el id de la actividad");
        id.setIdActividad(idActividad);

        // Rellenamos el habito
        habitoCrear.setId(id);
        habitoCrear.setIdUsuario(UsuarioDao.BuscarPorId(Sesion.getSesion().getUsuario().getId()));
        habitoCrear.setIdActividad(ActividadDao.BuscarPorId(idActividad));
        habitoCrear.setTipo(Utils.leeString("Ingrese el tipo de habito:\n" +
                "diario, semanal, mensual o anual"));
        habitoCrear.setFrecuencia(Utils.leeNumero("Cuantas veces haces esta accion "));
        habitoCrear.setUltimaFecha(Utils.leeFecha());

        HabitoDao.InsertarHabito(habitoCrear);
    }

    public static void ActualizarHabito() {
        int idActividadActualizar = Utils.leeNumero("Ingrese el id de la actividad a actualizar");

        // Buscar el hábito en la base de datos
        Habito habitoActualizar = HabitoDao.BuscarHabitoId(idActividadActualizar);

        if (habitoActualizar != null) {
            System.out.println("Hábito encontrado: " + habitoActualizar);

            // Pedir nuevos valores al usuario
            habitoActualizar.setTipo(Utils.leeString("Ingrese el nuevo tipo de hábito:\n" +
                    "diario, semanal, mensual o anual"));
            habitoActualizar.setFrecuencia(Utils.leeNumero("Ingrese la nueva frecuencia"));
            habitoActualizar.setUltimaFecha(Utils.leeFecha());

            // Actualizar en la base de datos
            HabitoDao.ActualizarHabito(habitoActualizar);

            System.out.println("Hábito actualizado correctamente.");
        } else {
            System.out.println("No se encontró el hábito con el ID ingresado.");
        }
    }

    public static void MostrarHabito() {
        List<Habito> habitos = HabitoDao.BuscarHabito();
        for (Habito habito : habitos) {
            System.out.println(habito);
        }
    }

    public static void EliminarHabito() {
        Habito habito = HabitoDao.BuscarHabitoId(Utils.leeNumero("Ingrese el id de la actividad"));
        HabitoDao.EliminarHabito(habito);
    }
}

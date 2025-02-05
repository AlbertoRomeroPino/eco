package org.example.model.dao;

import org.example.model.connection.Connection;
import org.example.model.entity.*;
import org.example.utils.Utils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HabitoDao {

    public static void InsertarHabito(Habito habito) {
        Session session = Connection.getInstance().getSession();
        session.beginTransaction();

        // Asegurar que el usuario y la actividad están en la sesión
        Usuario usuarioPersistido = session.merge(habito.getIdUsuario());
        Actividad actividadPersistida = session.merge(habito.getIdActividad());

        // Actualizar el hábito con las entidades gestionadas
        habito.setIdUsuario(usuarioPersistido);
        habito.setIdActividad(actividadPersistida);

        // Verificar si ya existe el hábito
        Habito habitoExistente = session.get(Habito.class, habito.getId());
        if (habitoExistente != null) {
            System.err.println("El hábito ya existe.");
        } else {
            session.persist(habito);
            session.getTransaction().commit();
            System.out.println("Se ha agregado el hábito.");
        }
    }

    public static Habito BuscarHabitoId(int idActividad) {
        Session session = Connection.getInstance().getSession();

        // Obtener el id del usuario en sesión
        int idUsuario = Sesion.getSesion().getUsuario().getId();

        // Crear el ID compuesto
        HabitoId habitoId = new HabitoId();
        habitoId.setIdUsuario(idUsuario);
        habitoId.setIdActividad(idActividad);

        // Buscar el hábito con la clave compuesta
        return session.get(Habito.class, habitoId);
    }


    public static List<Habito> BuscarHabito() {
        Session session = Connection.getInstance().getSession();

        Query<Habito> query = session.createQuery(
                "SELECT h FROM Habito h " +
                        "JOIN FETCH h.idUsuario " +
                        "JOIN FETCH h.idActividad " +
                        "WHERE h.idUsuario.id = :idUsuario",  // Filtrar por el usuario logueado
                Habito.class
        ).setParameter("idUsuario", Sesion.getSesion().getUsuario().getId());

        List<Habito> habitos = query.list();
        return habitos;

    }

    public static void ActualizarHabito(Habito habito) {

        Session session = Connection.getInstance().getSession();
        session.beginTransaction();

        // Verificar si el hábito existe en la base de datos
        Habito habitoExistente = session.get(Habito.class, habito.getId());

        if (habitoExistente != null) {
            session.merge(habito);  // Usa merge en lugar de update
            session.getTransaction().commit();
            System.out.println("Se ha actualizado el hábito.");
        } else {
            System.out.println("Error: El hábito no existe en la base de datos.");
            session.getTransaction().rollback();
        }

    }

    public static void EliminarHabito(Habito habito) {
        Session session = Connection.getInstance().getSession();
        session.beginTransaction();

        try {
            // Buscar el hábito dentro de la misma sesión
            HabitoId id = new HabitoId();
            id.setIdUsuario(Sesion.getSesion().getUsuario().getId());
            id.setIdActividad(habito.getId().getIdActividad());

            Habito habitoTMP = session.get(Habito.class, id);

            if (habitoTMP != null) {
                session.remove(habitoTMP); // Hibernate 6
                session.getTransaction().commit();
                System.out.println("Se ha eliminado el hábito correctamente.");
            } else {
                System.out.println("No se encontró el hábito con los IDs proporcionados.");
                session.getTransaction().rollback();
            }
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}

package org.example.model.dao;

import org.example.model.connection.Connection;
import org.example.model.entity.Actividad;
import org.example.model.entity.Habito;
import org.example.model.entity.Sesion;
import org.example.model.entity.Usuario;
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
            System.out.println("El hábito ya existe.");
        } else {
            session.persist(habito);
            session.getTransaction().commit();
            System.out.println("Se ha agregado el hábito.");
        }
    }

    public static List<Habito> BuscarHabito() {
        Session session = Connection.getInstance().getSession();

        Query<Habito> query = session.createQuery(
                "SELECT h FROM Habito h " +
                        "JOIN FETCH h.idUsuario " +
                        "JOIN FETCH h.idActividad",
                Habito.class
        );

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
        session.delete(habito);
        session.getTransaction().commit();
        System.out.println("Se ha eliminado el habito.");
    }
}

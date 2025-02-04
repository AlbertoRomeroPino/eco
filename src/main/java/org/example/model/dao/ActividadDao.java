package org.example.model.dao;

import org.example.model.connection.Connection;
import org.example.model.entity.Actividad;
import org.hibernate.Session;

import java.util.List;

public class ActividadDao {
    public static Actividad BuscarPorId(int id) {
        Session session = Connection.getInstance().getSession();
        return (Actividad) session.get(Actividad.class, id);
    }

    public static List<Actividad> BuscarTodasActividades() {
        Session session = Connection.getInstance().getSession();
        return session.createQuery("from Actividad").list();
    }
}

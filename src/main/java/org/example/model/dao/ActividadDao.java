package org.example.model.dao;

import org.example.model.connection.Connection;
import org.example.model.entity.Actividad;
import org.hibernate.Session;

public class ActividadDao {
    public static Actividad findById(int id) {
        Session session = Connection.getInstance().getSession();
        return (Actividad) session.get(Actividad.class, id);
    }
}

package org.example.model.dao;

import org.example.model.connection.Connection;
import org.example.model.entity.Actividad;
import org.example.model.entity.Recomendacion;
import org.hibernate.Session;

public class RecomendacionDao {
    public static Recomendacion BuscarPorId(int id) {
        Session session = Connection.getInstance().getSession();
        return (Recomendacion) session.get(Recomendacion .class, id);
    }

}

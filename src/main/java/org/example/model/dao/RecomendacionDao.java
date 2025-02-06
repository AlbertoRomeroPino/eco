package org.example.model.dao;

import org.example.model.connection.Connection;
import org.example.model.entity.Actividad;
import org.example.model.entity.Recomendacion;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class RecomendacionDao {
    public static Recomendacion BuscarPorId(int id) {
        Session session = Connection.getInstance().getSession();
        return (Recomendacion) session.get(Recomendacion .class, id);
    }

    public static List<String> BuscarPorActividad(int idActividad) {
        Session session = Connection.getInstance().getSession();
        Query<String> query = session.createQuery(
                "SELECT r.descripcion FROM Recomendacion r " +
                        "JOIN r.idCategoria c " +
                        "JOIN Actividad a ON a.idCategoria.id = c.id " +
                        "WHERE a.id = :idActividad",
                String.class
        );
        query.setParameter("idActividad", idActividad);
        return query.getResultList(); // Devuelve una lista en lugar de un solo resultado
    }



}

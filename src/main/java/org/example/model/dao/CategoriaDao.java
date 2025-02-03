package org.example.model.dao;

import org.example.model.connection.Connection;
import org.example.model.entity.Actividad;
import org.example.model.entity.Categoria;
import org.hibernate.Session;

public class CategoriaDao {
    public static Categoria BuscarPorId(int id) {
        Session session = Connection.getInstance().getSession();
        return (Categoria) session.get(Categoria.class, id);
    }
}

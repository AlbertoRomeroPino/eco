package org.example.model.dao;

import org.example.model.connection.Connection;
import org.example.model.entity.Usuario;
import org.hibernate.Session;

public class UsuarioDao {
    /**
     * Inserta un usuario directamente a la base de datos
     * @param usuario que se valla a insertar directamente
     */
    public static void insertUsuario(Usuario usuario) {
        Session session = Connection.getInstance().getSession();
        session.beginTransaction();
        session.persist(usuario);
        session.getTransaction().commit();
        System.out.println(" Se a insertado el usuario ");
    }

    public static Usuario BuscarNombreUsuario(String nombre) {
        Session session = Connection.getInstance().getSession();
        Usuario usuario = session.get(Usuario.class, nombre);
        session.getTransaction().commit();
        return usuario;
    }

}

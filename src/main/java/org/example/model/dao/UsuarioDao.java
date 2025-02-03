package org.example.model.dao;

import org.example.model.connection.Connection;
import org.example.model.entity.Usuario;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {
    /**
     * Inserta un usuario directamente a la base de datos
     *
     * @param usuario que se valla a insertar directamente
     */
    public static void InsertarUsuario(Usuario usuario) {
        Usuario usuarioTMP = BuscarNombreUsuario(usuario.getNombre());

        if (usuarioTMP != null) {
            System.out.println("Usuario ya existente");
        }else {
            Session session = Connection.getInstance().getSession();
            session.beginTransaction();
            session.persist(usuario);
            session.getTransaction().commit();
            System.out.println(" Se a insertado el usuario ");
        }
    }

    /**
     * Busca un usuario en la base de datos
     *
     * @param nombre el nombre del usuario que se busca
     * @return el usuario con el nombre pasado
     */
    public static Usuario BuscarNombreUsuario(String nombre) {
        Session session = Connection.getInstance().getSession();
        List<Usuario> usuarios = new ArrayList<>();

        Query<Usuario> query = session.createQuery("from Usuario where nombre = :nombre", Usuario.class)
                .setParameter("nombre", nombre);
        Usuario usuario;

        usuario = query.uniqueResult();

        return usuario;
    }

    public static Usuario BuscarPorId(int id) {
        Session session = Connection.getInstance().getSession();
        List<Usuario> usuarios = new ArrayList<>();
        Query<Usuario> query = session.createQuery("from Usuario where id = :id", Usuario.class)
                .setParameter("id", id);
        Usuario usuario;
        usuario = query.uniqueResult();
        return usuario;
    }
    /**
     * Actualiza un usuario
     *
     * @param usuario el usuario ya actualizado
     */
    public static void ActualizarUsuario(Usuario usuario) {
        Session session = Connection.getInstance().getSession();
        session.beginTransaction();
        session.merge(usuario);
        session.getTransaction().commit();
        System.out.println(" Se a actualizado el usuario ");
    }

    /**
     * Elimina un usuario
     *
     * @param usuario el usuario a eliminar
     */
    public static void EliminarUsuario(Usuario usuario) {
        Session session = Connection.getInstance().getSession();
        session.beginTransaction();

        // Asociamos el usuario a la sesión actual antes de eliminarlo
        Usuario usuarioManaged = session.merge(usuario);

        session.delete(usuarioManaged);
        session.getTransaction().commit();
        session.close(); // Cerramos la sesión
        System.out.println("Se ha eliminado el usuario");
    }
}

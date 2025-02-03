package org.example.model.dao;

import org.example.model.connection.Connection;
import org.example.model.entity.Actividad;
import org.example.model.entity.Habito;
import org.example.model.entity.Huella;
import org.example.model.entity.Usuario;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class HuellaDao {
    public static void InsertHuella(Huella huella) {
        Session session = Connection.getInstance().getSession();
        session.beginTransaction();

        // Asegurar que el usuario y la actividad están en la sesión
        Usuario usuarioPersistido = session.merge(huella.getIdUsuario());
        Actividad actividadPersistida = session.merge(huella.getIdActividad());

        // Actualizar la huella con las entidades gestionadas
        huella.setIdUsuario(usuarioPersistido);
        huella.setIdActividad(actividadPersistida);

        // Verificar si ya existe la Huella (usando usuario y actividad en lugar de ID)
        Huella huellaExistente = session.createQuery(
                        "FROM Huella h WHERE h.idUsuario = :usuario AND h.idActividad = :actividad", Huella.class)
                .setParameter("usuario", huella.getIdUsuario())
                .setParameter("actividad", huella.getIdActividad())
                .uniqueResult();

        if (huellaExistente != null) {
            System.out.println("La huella ya existe.");
            session.getTransaction().rollback(); // Revertir cambios si ya existe
        } else {
            session.persist(huella);
            session.getTransaction().commit();
            System.out.println("Se ha agregado la huella.");
        }
    }


    public static List<Huella> BuscarHuella() {
        Session session = Connection.getInstance().getSession();

        Query<Huella> query = session.createQuery("SELECT h FROM Huella h " + "JOIN FETCH h.idUsuario " + "JOIN FETCH h.idActividad", Huella.class);
        List<Huella> huellas = query.list();
        return huellas;
    }

    public static void ActualizarHuella(Huella huella) {
        Session session = Connection.getInstance().getSession();
        session.beginTransaction();

        Huella huellaExistente = session.get(Huella.class, huella.getId());

        if (huellaExistente != null) {
            session.merge(huella);
            session.getTransaction().commit();
            System.out.println("Se ha actualizado el huella.");
        } else {
            System.out.println("Error: El hábito no existe en la base de datos");
            session.getTransaction().rollback();
        }
    }

    public static void EliminarHuella(Huella huella) {
        Session session = Connection.getInstance().getSession();
        session.beginTransaction();

        // Buscar la huella en la base de datos antes de eliminarla
        Huella huellaExistente = session.find(Huella.class, huella.getId());

        if (huellaExistente != null) {
            session.remove(huellaExistente);
            session.getTransaction().commit();
            System.out.println("Se ha eliminado la huella.");
        } else {
            session.getTransaction().rollback();
            System.out.println("La huella no existe, no se puede eliminar.");
        }
    }
}

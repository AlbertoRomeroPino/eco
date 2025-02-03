package org.example.model.dao;

import org.example.model.connection.Connection;
import org.example.model.entity.Actividad;
import org.example.model.entity.Habito;
import org.example.model.entity.Huella;
import org.example.model.entity.Usuario;
import org.hibernate.Session;

public class HuellaDao {
    public static void InsertHuella(Huella huella) {
        Session session = Connection.getInstance().getSession();
        session.beginTransaction();

// Asegurar que el usuario y la actividad están en la sesión
        Usuario usuarioPersistido = session.merge(huella.getIdUsuario());
        Actividad actividadPersistida = session.merge(huella.getIdActividad());

        // Actualizar el hábito con las entidades gestionadas
        huella.setIdUsuario(usuarioPersistido);
        huella.setIdActividad(actividadPersistida);

        // Verificar si ya existe el hábito
        Huella huellaExistente = session.get(Huella.class, huella.getId());
        if (huellaExistente != null) {
            System.out.println("El hábito ya existe.");
        } else {
            session.persist(huella);
            session.getTransaction().commit();
            System.out.println("Se ha agregado el hábito.");
        }
    }
}

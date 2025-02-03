import org.example.model.dao.ActividadDao;
import org.example.model.dao.HuellaDao;
import org.example.model.dao.UsuarioDao;
import org.example.model.entity.Actividad;
import org.example.model.entity.Huella;
import org.example.model.entity.Sesion;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class test3_huella {
        public static void main(String[] args) {
            int idActividad = 3;

            // Obtener el usuario de la sesión
            Sesion.getSesion().setUsuario(UsuarioDao.BuscarNombreUsuario("test"));
            System.out.println(Sesion.getSesion().getUsuario());

            // Obtener la actividad
            Actividad actividad = ActividadDao.BuscarPorId(idActividad);
            System.out.println(actividad);

            // Crear una nueva huella
            Huella huella = new Huella();
            huella.setIdUsuario(Sesion.getSesion().getUsuario());
            huella.setIdActividad(actividad);
            huella.setValor(BigDecimal.valueOf(10.5)); // Valor de prueba
            huella.setUnidad(actividad.getIdCategoria().getUnidad()); // Unidad de prueba
            huella.setFecha(Instant.now());

            // Insertar la huella en la base de datos
            HuellaDao.InsertHuella(huella);

            // Mostrar todas las huellas
            List<Huella> huellas = HuellaDao.BuscarHuella();
            for (Huella h : huellas) {
                System.out.println(h);
            }

            // **ACTUALIZACIÓN DE LA HUELLA**
            System.out.println("\nActualizando la huella...");

            huella.setValor(BigDecimal.valueOf(12.0)); // Cambiamos el valor

            // Guardar los cambios en la base de datos
            HuellaDao.ActualizarHuella(huella);

            System.out.println("\nHuella actualizada correctamente.");

            // Volver a mostrar huellas para verificar el cambio
            huellas = HuellaDao.BuscarHuella();
            for (Huella h : huellas) {
                System.out.println(h);
            }

            // **ELIMINAR LA HUELLA**
            HuellaDao.EliminarHuella(huella);
        }
}

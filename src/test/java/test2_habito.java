import org.example.model.dao.ActividadDao;
import org.example.model.dao.HabitoDao;
import org.example.model.dao.UsuarioDao;
import org.example.model.entity.Actividad;
import org.example.model.entity.Habito;
import org.example.model.entity.HabitoId;
import org.example.model.entity.Sesion;

import java.time.Instant;
import java.util.List;

public class test2_habito {
    public static void main(String[] args) {
        int idActividad = 4;

        Sesion.getSesion().setUsuario(UsuarioDao.BuscarNombreUsuario("test"));
        System.out.println(Sesion.getSesion().getUsuario());

        Actividad actividad = ActividadDao.BuscarPorId(idActividad);
        System.out.println(actividad);

        Habito habito = new Habito();

        HabitoId id = new HabitoId();
        id.setIdUsuario(Sesion.getSesion().getUsuario().getId());
        id.setIdActividad(actividad.getId());

        habito.setId(id);
        habito.setIdUsuario(UsuarioDao.BuscarPorId(Sesion.getSesion().getUsuario().getId()));
        habito.setIdActividad(ActividadDao.BuscarPorId(idActividad));
        habito.setTipo("Semanal");
        habito.setFrecuencia(5);
        habito.setUltimaFecha(Instant.now());

        HabitoDao.InsertarHabito(habito);

        List<Habito> habitos = HabitoDao.BuscarHabito();
        for (Habito h : habitos) {
            System.out.println(h);
        }

        // **ACTUALIZACIÓN DEL HÁBITO**
        System.out.println("\nActualizando el hábito...");

        habito.setTipo("Diario");      // Cambiamos el tipo
        habito.setFrecuencia(7);       // Cambiamos la frecuencia

        // Guardar los cambios en la base de datos
        HabitoDao.ActualizarHabito(habito);

        System.out.println("\nHábito actualizado correctamente.");

        // Volver a mostrar hábitos para verificar el cambio
        habitos = HabitoDao.BuscarHabito();
        for (Habito h : habitos) {
            System.out.println(h);
        }

        HabitoDao.EliminarHabito(habito);
  }
}

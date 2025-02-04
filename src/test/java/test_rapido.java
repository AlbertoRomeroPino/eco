import org.example.model.dao.ActividadDao;
import org.example.model.dao.HabitoDao;
import org.example.model.dao.UsuarioDao;
import org.example.model.entity.Actividad;
import org.example.model.entity.Habito;
import org.example.model.entity.HabitoId;
import org.example.model.entity.Sesion;
import org.example.utils.Utils;
import org.example.viewTerminal.ViewHabito;

import java.util.List;

public class test_rapido {
    public static void main(String[] args) {
        //Crear un Habito
        Habito habitoCrear = new Habito();
        HabitoId id = new HabitoId();

        // Insertamos el id de usuario a habitoId
        id.setIdUsuario(1);

        // Mostramos todos las actividades para que el usuario elija cualj quiere
        List<Actividad> actividadList = ActividadDao.BuscarTodasActividades();
        for (Actividad actividad : actividadList) {
            ViewHabito.MostrarActividades(actividad);
        }
        int idActividad = Utils.leeNumero("Inserte el id de la actividad");
        id.setIdActividad(idActividad);

        // Rellenamos el habito
        habitoCrear.setId(id);
        habitoCrear.setIdUsuario(UsuarioDao.BuscarPorId(1));
        habitoCrear.setIdActividad(ActividadDao.BuscarPorId(idActividad));
        habitoCrear.setTipo(Utils.leeString("Ingrese el tipo de habito:\n" +
                "diario, semanal, mensual o anual"));
        habitoCrear.setFrecuencia(Utils.leeNumero("Cuantas veces haste esta accion a la semana"));

        habitoCrear.setUltimaFecha(Utils.leeFecha());

        HabitoDao.InsertarHabito(habitoCrear);
    }
}

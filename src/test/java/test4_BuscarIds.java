import org.example.model.dao.ActividadDao;
import org.example.model.dao.CategoriaDao;
import org.example.model.dao.RecomendacionDao;
import org.example.model.entity.Actividad;
import org.example.model.entity.Categoria;
import org.example.model.entity.Recomendacion;

public class test4_BuscarIds {
    public static void main(String[] args) {
        int id = 1;
        Categoria categoria = CategoriaDao.BuscarPorId(id);
        System.out.println(categoria);
        Actividad actividad = ActividadDao.BuscarPorId(id);
        System.out.println(actividad);
        Recomendacion recomendacion = RecomendacionDao.BuscarPorId(id);
        System.out.println(recomendacion);
    }
}

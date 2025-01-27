import org.example.model.dao.UsuarioDao;
import org.example.model.entity.Usuario;
import org.example.utils.Validacion;

public class test1_insertarUsuario {

    public static void main(String[] args) {
        testInsertarUsuario();
    }
    /**
     * Inserta correctamente
     */
    public static void testInsertarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Usuario 1");
        usuario.setContraseña(Validacion.encryptPassword("123456"));
        usuario.setEmail("usuario1@gmail.com");
        UsuarioDao.insertUsuario(usuario);
    }
}

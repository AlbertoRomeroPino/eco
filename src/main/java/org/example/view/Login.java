package org.example.view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.model.dao.UsuarioDao;
import org.example.model.entity.Sesion;
import org.example.model.entity.Usuario;
import org.example.utils.Validacion;
import org.example.view.controllerView.Controller;
import org.example.view.controllerView.View;
import org.example.view.enums.Scenes;
import org.hibernate.Session;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login extends Controller implements Initializable {
    @FXML
    private TextField user;
    @FXML
    private PasswordField password;

    public void initialize(URL localition, ResourceBundle resourceBundle) {

    }

    @Override
    public void onOpen(Object input, Object data) throws IOException {
        showScene(Scenes.LOGIN, null);
    }

    public void showScene(Scenes scenes, Object data) throws IOException {
        View view = MenuBar.loadFXML(scenes);
    }

    public void changeScene(Scenes scenes, Object data) throws IOException {
        App.setRoot(scenes, null);
    }


    @Override
    public void onClose(Object output) {

    }

    @FXML
    public void enterApp(Event event) throws IOException {
        Usuario usuario = new Usuario();
        usuario.setNombre(user.getText());
        usuario.setContraseña(password.getText());
        if (usuario.getNombre() != null || usuario.getNombre().equals("")) {
            Usuario usuarioDB = UsuarioDao.BuscarNombreUsuario(usuario.getNombre());
            if (usuarioDB != null) {
                usuario.setContraseña(Validacion.encryptPassword(password.getText()));
                if (usuario.getNombre().equals(usuarioDB.getNombre()) &&
                        usuario.getContraseña().equals(usuarioDB.getContraseña())) {
                    Sesion.getSesion().setUsuario(usuario);
                    //Comentario a posteriori borrar
                    System.out.println(usuario.getNombre());
                    changeScene(Scenes.MENUBAR, null);
                } else {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("Error");
                    alerta.setHeaderText("Error al insertar");
                    alerta.setContentText("Compruebe los datos");
                }
            }
        }
    }


    @FXML
    public void registerInApp(Event event) throws IOException {
        changeScene(Scenes.REGISTER, null);
    }

}
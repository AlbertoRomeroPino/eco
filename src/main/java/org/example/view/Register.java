package org.example.view;


import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.App;
import org.example.model.dao.UsuarioDao;
import org.example.model.entity.Usuario;
import org.example.utils.Validacion;
import org.example.view.controllerView.Controller;
import org.example.view.enums.Scenes;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Register extends Controller implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField email;
    @FXML
    private PasswordField contraseña;

    @Override
    public void onOpen(Object input, Object data) throws IOException {

    }

    @Override
    public void onClose(Object output) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    public void registerPerson(Event event) throws IOException {
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre.getText());
        usuario.setEmail(email.getText());
        usuario.setContraseña(contraseña.getText());
        usuario.setFechaRegistro(Instant.from(LocalDate.now()));

        if (!usuario.getContraseña().equals("")) {
            usuario.setContraseña(Validacion.encryptPassword(usuario.getContraseña()));

            if (!usuario.getNombre().equals("") && !usuario.getEmail().equals("")) {
                Usuario usuarioDB = UsuarioDao.BuscarNombreUsuario(usuario.getNombre());
                if (usuarioDB == null ) {
                    UsuarioDao.insertUsuario(usuario);
                    goBack();
                }
                else {
                    Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                    alerta.setTitle("Error");
                    alerta.setHeaderText(null);
                    alerta.setContentText("Usuario ya existe");
                    alerta.showAndWait();
                }
            }
        }
    }

    @FXML
    public void goBack() throws IOException {
        App.setRoot(Scenes.LOGIN, null);
    }
}

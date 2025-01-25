package org.example.view;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.view.enums.Scenes;

import javax.swing.text.View;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Login {
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
        Person person = new Person();
        person.setNickName(user.getText());
        person.setPassword(password.getText());

        if (person.getNickName() != null || person.getNickName().equals("")) {
            Person personDB = PersonDAO.build().findID(person.getNickName());
            if (personDB != null) {
                person.setPassword(Validations.encryptPassword(person.getPassword()));
                if (person.getNickName().equals(personDB.getNickName()) &&
                        person.getPassword().equals(personDB.getPassword())) {
                    Sesion.getSesion().setPerson(personDB);
                    System.out.println(person);
                    changeScene(Scenes.MENUBAR, null);
                }
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText("Error al insertar");
                alerta.setContentText("Compruebe los datos");
            }

        }
    }

    @FXML
    public void registerInApp(Event event) throws IOException {
        changeScene(Scenes.REGISTER, null);
    }
}

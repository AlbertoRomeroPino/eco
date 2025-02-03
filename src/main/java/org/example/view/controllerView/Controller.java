package org.example.view.controllerView;

import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.App;
import org.example.view.MenuBar;
import org.example.view.enums.Scenes;


import java.io.IOException;

public abstract class Controller {
    App app;


    public void setApp(App app) {
        this.app = app;
    }


    public abstract void onOpen(Object input, Object data) throws IOException;

    public abstract void onClose(Object output);

    public void openModal(Scenes scene, String title, Controller parent, Object data) throws IOException {
        View view = MenuBar.loadFXML(scene);
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(App.stage);
        Scene _scene = new Scene(view.scene);
        view.controller.onOpen(parent, data);     //Se le pasa al modal la información Crear un objeto con los datos y parent aqui
        stage.setScene(_scene);
        stage.showAndWait();
    }



}


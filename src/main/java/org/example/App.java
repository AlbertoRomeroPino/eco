package org.example;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.view.controllerView.Controller;
import org.example.view.MenuBar;
import org.example.view.controllerView.View;
import org.example.view.enums.Scenes;


import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene; // donde se ubica esta
    public static Stage stage; // pestaña de navegador como de aplicacion
    public static Controller currentController;

    @Override
    public void start(Stage stage_) throws IOException {
        stage = stage_;
        View view = MenuBar.loadFXML(Scenes.LOGIN);
        scene = new Scene(view.scene, 640, 480);
        currentController = (Controller) view.controller;
        currentController.onOpen(null, null);
        stage_.setScene(scene);
        stage_.show();
    }


    public static void setRoot(Scenes scene_, Object data) throws IOException {
        View view = MenuBar.loadFXML(scene_);
        scene = new Scene(view.scene, 640, 480);
        currentController = (Controller) view.controller;
        currentController.onOpen(data,null);
        stage.setScene(scene);
    }

    public static void main(String[] args) {
        launch();
    }

}
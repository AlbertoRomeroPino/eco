package org.example.view;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import org.example.App;
import org.example.view.controllerView.Controller;
import org.example.view.controllerView.View;
import org.example.view.enums.Scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuBar extends Controller implements Initializable {

    @FXML
    private BorderPane borderPane;
    private Controller centerController;

    @Override
    public void onOpen(Object input, Object data) throws IOException {
        //changeScene(Scenes.MYGAME, null);
    }

    public void changeScene(Scenes scenes, Object data) throws IOException {
        View view = loadFXML(scenes);
        borderPane.setCenter(view.scene);
        this.centerController = view.controller;
        this.centerController.onOpen(data, null);

    }

    public static View loadFXML(Scenes scenes) throws IOException {
        String url = scenes.getURL();
        FXMLLoader loader = new FXMLLoader(App.class.getResource(url));
        View view = new View();
        try {
            Parent parent = loader.load();
            Controller controller = loader.getController();
            view.scene = parent;
            view.controller = controller;
        } catch (IOException e) {
            e.printStackTrace(); // Imprime la pila de errores para obtener más detalles
            throw e; // Re-lanza la excepción después de imprimir la pila
        }
        return view;




    }


    @Override
    public void onClose(Object output) {

    }


    // Los 3 de abajo son para la menuBar Help
    @FXML
    private void Exit() {
        System.exit(0);
    }

    @FXML
    private void Error() {
        Exit();
    }

    @FXML
    private void Help() {
        Exit();
    }


    public void initialize(URL localition, ResourceBundle resourceBundle) {

    }

    @FXML
    private void goToGame() throws IOException {
       // changeScene(Scenes.MYGAME, null);
    }

    @FXML
    private void goToCompany() throws IOException {
       // changeScene(Scenes.COMPANY, null);
    }
    @FXML
    private void goToAllGames() throws IOException{
        //changeScene(Scenes.ALLGAMES, null);
    }
}

package org.example.view.enums;

public enum Scenes {
    LOGIN("/org/example/view/Login.fxml"),
    REGISTER("/org/example/view/Register.fxml"),
    MENUBAR("/org/example/view/MenuBar.fxml");

    private String url;

    Scenes(String url) {
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}

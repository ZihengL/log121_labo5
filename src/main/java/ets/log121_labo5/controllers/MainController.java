package ets.log121_labo5.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

public class MainController {

    @FXML
    private void initialize() {

    }

    @FXML
    private void onOpenSettings(MouseEvent mouseEvent) {

    }

    @FXML
    private void uploadImage(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();

    }

    public void onPressRect(MouseEvent mouseEvent) {
        double x = mouseEvent.getX(), y = mouseEvent.getY();

        System.out.printf("Pressed: [ %.2f, %.2f ]\n", x, y);
    }
}
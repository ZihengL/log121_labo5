package ets.log121_labo5.controllers;

import ets.log121_labo5.controllers.command.CommandsManager;
import ets.log121_labo5.models.observer.Observable;
import ets.log121_labo5.models.observer.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.awt.*;

public class MainController implements Observer {

    @FXML private Rectangle rect;

    @Override
    public void update(Observable observable) {

    }

    // UI

    @FXML
    private void initialize() {
        CommandsManager instance = CommandsManager.getInstance();
        instance.addObserver(this);
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
package ets.log121_labo5.controllers;

import ets.log121_labo5.controllers.command.CommandsManager;
import ets.log121_labo5.models.observer.Observable;
import ets.log121_labo5.models.observer.Observer;
import javafx.fxml.FXML;

public class ImageNavigatorController implements Observer {

    @Override
    public void update(Observable observable) {

    }

    @FXML
    private void initialize() {
        CommandsManager instance = CommandsManager.getInstance();
        instance.addObserver(this);
    }
}

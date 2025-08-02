package ets.log121_labo5.controllers;

import ets.log121_labo5.models.CommandsManager;
import ets.log121_labo5.models.observer.Observable;
import ets.log121_labo5.models.observer.Observer;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Class: ImageController
 * Created on: 8/1/2025
 * Description: Classe abstraite de contrôleur qui regroupe ce qu'on en commun
 * tous les 3 panneaux contenant l'image.
 *
 * @author liuzi | Zi heng Liu
 */

public abstract class ImageController implements Observer {

    @FXML protected StackPane rootPane;
    @FXML protected ImageView view;

    @FXML
    protected void initialize() {
        CommandsManager manager = CommandsManager.getInstance();
        manager.addObserver(this);
    }

    public ImageView getImageView() {
        return this.view;
    }

    @Override
    public void update(Observable observable) {
        CommandsManager manager = (CommandsManager) observable;

        this.updateImage(manager);
    }

    public boolean updateImage(CommandsManager manager) {
        Image image = manager.getImage(), current = this.view.getImage();
        if (image == null || image.equals(current)) return false;

        this.view.setImage(manager.getImage());
        return true;
    }
}

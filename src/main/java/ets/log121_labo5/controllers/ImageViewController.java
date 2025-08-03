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
 * Description: Classe abstraite de contrôleur qui regroupe tout ce qui est en commun
 * parmis les panneaux contenant un ImageView, ce qui inclu les panneaux avec une vue
 * contrôlable et le panneau de vignette en haut de la fenêtre de l'application.
 *
 * @author liuzi | Zi heng Liu
 */

public abstract class ImageViewController implements Observer {

    @FXML protected StackPane rootPane;
    @FXML protected ImageView view;

    // Ajoute l'instance de soit à la liste d'observateurs.
    @FXML
    protected void initialize() {
        CommandsManager manager = CommandsManager.getInstance();
        manager.addObserver(this);
    }

    public ImageView getImageView() {
        return this.view;
    }

    // Implémentation d'update d'Observer.
    @Override
    public void update(Observable observable) {
        CommandsManager manager = (CommandsManager) observable;

        this.updateImage(manager);
    }

    // Retourne vrai s'il effectue un changement de l'image.
    public boolean updateImage(CommandsManager manager) {
        Image image = manager.getImage(), current = this.view.getImage();
        if (image == null || image.equals(current)) return false;

        this.view.setImage(manager.getImage());
        return true;
    }
}

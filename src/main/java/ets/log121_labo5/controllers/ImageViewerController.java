package ets.log121_labo5.controllers;


import ets.log121_labo5.controllers.command.CommandsManager;
import ets.log121_labo5.models.observer.Observable;
import ets.log121_labo5.models.observer.Observer;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * Class: ImageViewController
 * Created on: 7/12/2025
 * Description: Controleur pour l'image thumbnail de l'application.
 * Code partiellement identique à ImageNavigatorController, mais n'inclus pas tout
 * ce qui est nécéssaire pour la navigation et l'édition d'image.
 *
 * La classe était originallement l'objet mère de ImageNavigatorController, mais
 * une séparation complète était nécéssaire due à des bogues de comportement que cela créait.
 * @author liuzi | Zi heng Liu
 */

public class ImageViewerController implements Observer {

    @FXML protected BorderPane rootPane;
    @FXML protected ImageView imageContainer;

    @FXML
    protected void initialize() {
        CommandsManager manager = CommandsManager.getInstance();
        manager.addObserver(this);

        this.imageContainer.fitWidthProperty().bind(this.rootPane.widthProperty().subtract(20));
        this.imageContainer.fitHeightProperty().bind(this.rootPane.heightProperty().subtract(20));
    }

    @Override
    public void update(Observable observable) {
        CommandsManager manager = (CommandsManager) observable;

        Image image = manager.getImage();
        this.updateImage(image);
    }

    public void updateImage(Image image) {
        Image current = this.imageContainer.getImage();

        if (image != null && !image.equals(current))
            this.imageContainer.setImage(image);
    }
}

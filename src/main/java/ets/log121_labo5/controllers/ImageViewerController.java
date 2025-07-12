package ets.log121_labo5.controllers;


import ets.log121_labo5.controllers.command.CommandsManager;
import ets.log121_labo5.models.observer.Observable;
import ets.log121_labo5.models.observer.Observer;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * Class: ImageViewController
 * Created on: 7/12/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class ImageViewerController implements Observer {

    @FXML protected AnchorPane rootPane;
    @FXML protected ImageView imageContainer;

    @FXML
    protected void initialize() {
        CommandsManager manager = CommandsManager.getInstance();
        manager.addObserver(this);
    }

    @Override
    public void update(Observable observable) {
        CommandsManager manager = (CommandsManager) observable;

        Image image = manager.getImage();
        this.updateImage(image);
    }

    public void updateImage(Image image) {
        Image current = this.imageContainer.getImage();

        if (!image.equals(current))
            this.imageContainer.setImage(image);
    }
}

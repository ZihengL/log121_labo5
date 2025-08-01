package ets.log121_labo5.controllers;


import ets.log121_labo5.controllers.command.CommandsManager;
import ets.log121_labo5.models.observer.Observable;
import ets.log121_labo5.models.observer.Observer;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

/**
 * Class: ImageViewController
 * Created on: 7/12/2025
 * Description: Controleur pour l'image thumbnail de l'application.
 * Code partiellement identique à ImageNavigatorController, mais n'inclus pas tout
 * ce qui est nécéssaire pour la navigation et l'édition d'image.
 *
 * La classe était originallement l'objet mère de ImageNavigatorController, mais
 * une séparation complète était nécéssaire due à des bogues de comportement.
 *
 * @author liuzi | Zi heng Liu
 */

public class ImageViewerController extends ImageController {

    public static final int FITWIDTH = 300;
    public static final int FITHEIGHT = 200;

//    @FXML protected StackPane rootPane;
//    @FXML protected ImageView view;

//    @FXML
//    protected void initialize() {
//        CommandsManager manager = CommandsManager.getInstance();
//        manager.addObserver(this);
//    }

//    @Override
//    public void update(Observable observable) {
//        CommandsManager manager = (CommandsManager) observable;
//
//        Image image = manager.getImage();
//        this.updateImage(image);
//    }

    public boolean updateImage(CommandsManager manager) {
        if (!super.updateImage(manager)) return false;

        this.view.setFitWidth(FITWIDTH);
        this.view.setFitHeight(FITHEIGHT);

        return true;
    }
}

package ets.log121_labo5.controllers;

import ets.log121_labo5.controllers.command.CommandsManager;
import ets.log121_labo5.controllers.command.commands.*;
import ets.log121_labo5.models.Perspective;
import ets.log121_labo5.models.observer.Observable;
import ets.log121_labo5.models.observer.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


public class MainController implements Observer {

    // MENUBAR: FICHIER
    @FXML private MenuItem saveAppStateItem;
    @FXML private MenuItem loadAppStateItem;
    @FXML private MenuItem loadImageItem;
    @FXML private MenuItem quitItem;
    // MENUBAR: ÉDITION
    @FXML private MenuItem undoItem;
    @FXML private MenuItem redoItem;
    // MENUBAR: PRESSE-PAPIER
    @FXML private MenuItem pickStrategemItem;

    // IMAGEVIEWS & PARENT CONTAINERS
    @FXML private VBox thumbnailPane;
    @FXML private ImageView thumbnailImage;
//    @FXML private VBox leftsidePane;
//    @FXML private ImageView leftsideImage;
//    @FXML private VBox rightsidePane;
//    @FXML private ImageView rightsideImage;

    // SUBCONTROLLERS & ASSOCIATED PANES
    private ImageNavigatorController leftsidePaneController;
    @FXML private AnchorPane leftsidePane;
    private ImageNavigatorController rightsidePaneController;
    @FXML private AnchorPane rightsidePane;

    // UI

    @FXML
    private void initialize() {
        // OBSERVER
        CommandsManager manager = CommandsManager.getInstance();
        manager.addObserver(this);

        // Liaison de propriété de l'image du thumbnail->leftside/rightside
//        this.leftsideImage.imageProperty().bind(this.thumbnailImage.imageProperty());
//        this.rightsideImage.imageProperty().bind(this.thumbnailImage.imageProperty());

        /* --- MENUBAR --- */

        // FICHIER
        this.saveAppStateItem.setOnAction(new SaveStateCommand());
        this.loadAppStateItem.setOnAction(new LoadStateCommand());
        this.loadImageItem.setOnAction(new LoadImageCommand());
        this.quitItem.setOnAction(new QuitCommand());
        // ÉDITION
        this.undoItem.setOnAction(new UndoCommand());
        this.redoItem.setOnAction(new UndoCommand());
        // PRESSE-PAPIER
        this.pickStrategemItem.setOnAction(new SetStratagemCommand());

        /* --- NAVIGATION --- */

        /* --- PERSPECTIVE --- */

        // TODO: FIGURE OUT THIS SHIT FOR PERSPECTIVE (PUT IT IN IMAGENAVCONTROLLER)
        PerspectiveGetter leftsideGetter = () -> {

        };
    }

    // TODO: TEST METHOD. REMOVE LATER
    public void onPressRect(MouseEvent mouseEvent) {
        double x = mouseEvent.getX(), y = mouseEvent.getY();

        System.out.printf("Pressed: [ %.2f, %.2f ]\n", x, y);
    }

    // OBSERVER

    @Override
    public void update(Observable observable) {
        CommandsManager manager = (CommandsManager) observable;

        Image image = manager.getImage();
        if (!this.updateImage(image)) return;

        Perspective leftside = manager.getLeftside(),
                    rightside = manager.getRightside();

    }

    // OTHER

    public boolean updateImage(Image image) {
        Image current = this.thumbnailImage.getImage();
        if (!image.equals(current)) {
            this.thumbnailImage.setImage(image);
            return true;
        }

        return false;
    }
}
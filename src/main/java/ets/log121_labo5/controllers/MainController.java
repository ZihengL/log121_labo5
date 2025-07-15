package ets.log121_labo5.controllers;

import ets.log121_labo5.controllers.command.CommandsManager;
import ets.log121_labo5.controllers.command.commands.menubar.*;
import ets.log121_labo5.models.observer.Observable;
import ets.log121_labo5.models.observer.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

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

    // SUBCONTROLLERS
    @FXML private BorderPane thumbnailPane;
    @FXML private ImageViewerController thumbnailPaneController;
    @FXML private BorderPane leftsidePane;
    @FXML private ImageNavigatorController leftsidePaneController;
    @FXML private BorderPane rightsidePane;
    @FXML private ImageNavigatorController rightsidePaneController;

    @FXML private ContextMenuController contextMenuController;
    // UI

    @FXML
    private void initialize() {
        // OBSERVER
        CommandsManager manager = CommandsManager.getInstance();
        manager.addObserver(this);

        /* --- MENUBAR --- */
        // FICHIER
        this.saveAppStateItem.setOnAction(new SaveStateCommand());
        this.loadAppStateItem.setOnAction(new LoadStateCommand());
        // --
        this.loadImageItem.setOnAction(new LoadImageCommand());
        // --
        this.quitItem.setOnAction(new QuitCommand());
        // ÉDITION
        this.undoItem.setOnAction(new UndoCommand());
        this.redoItem.setOnAction(new RedoCommand());
        // PRESSE-PAPIER
        this.pickStrategemItem.setOnAction(new SetStratagemCommand());

        /* --- PERSPECTIVE & NAVIGATION --- */
        PerspectiveGetter leftsideGetter = manager::getLeftside;
        PerspectiveSetter leftsideSetter = manager::setLeftside;
        this.leftsidePaneController.setPerspectiveLambdas(leftsideGetter, leftsideSetter);

        PerspectiveGetter rightsideGetter = manager::getRightside;
        PerspectiveSetter rightsideSetter = manager::setRightside;
        this.rightsidePaneController.setPerspectiveLambdas(rightsideGetter, rightsideSetter);

        /* --- CONTEXT MENU --- */
        this.contextMenuController = new ContextMenuController();
        this.contextMenuController.addToPanes(this.leftsidePane, this.rightsidePane);
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
    }
}
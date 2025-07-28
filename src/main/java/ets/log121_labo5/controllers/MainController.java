package ets.log121_labo5.controllers;

import ets.log121_labo5.models.command.CommandsManager;
import ets.log121_labo5.models.command.commands.menubar.*;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class MainController {

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
        /* --- MENUBAR --- */
            // FICHIER
        this.saveAppStateItem.setOnAction(new SaveStateCommand());
        this.loadAppStateItem.setOnAction(new LoadStateCommand());
        this.loadImageItem.setOnAction(new LoadImageCommand());
        this.quitItem.setOnAction(new QuitCommand());
            // ÉDITION
        this.undoItem.setOnAction(new UndoCommand());
        this.redoItem.setOnAction(new RedoCommand());
            // PRESSE-PAPIER
        this.pickStrategemItem.setOnAction(new SetStratagemCommand());

        /* --- PERSPECTIVE --- */
        CommandsManager manager = CommandsManager.getInstance();

        PerspectiveGetter leftsideGetter = manager::getLeftside;
        PerspectiveSetter leftsideSetter = manager::setLeftside;
        this.leftsidePaneController.setPerspectiveAccessors(leftsideGetter, leftsideSetter);

        PerspectiveGetter rightsideGetter = manager::getRightside;
        PerspectiveSetter rightsideSetter = manager::setRightside;
        this.rightsidePaneController.setPerspectiveAccessors(rightsideGetter, rightsideSetter);

        /* --- CONTEXT MENU --- */
        this.contextMenuController = new ContextMenuController();
        this.contextMenuController.addToPanes(this.leftsidePane, this.rightsidePane);

        // TEMPORARY: DEFAULT IMG
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\ets\\log121_labo5\\saves\\default.jpg";
        Image image = new Image(path);
        manager.setImage(image);

        System.out.println(image.getUrl());
    }
}
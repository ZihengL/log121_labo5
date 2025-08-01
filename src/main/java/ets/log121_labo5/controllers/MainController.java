package ets.log121_labo5.controllers;

import ets.log121_labo5.models.PerspectiveGetter;
import ets.log121_labo5.models.PerspectiveSetter;
import ets.log121_labo5.controllers.command.CommandsManager;
import ets.log121_labo5.controllers.command.commands.menubar.edit.RedoCommand;
import ets.log121_labo5.controllers.command.commands.menubar.edit.UndoCommand;
import ets.log121_labo5.controllers.command.commands.menubar.files.LoadImageCommand;
import ets.log121_labo5.controllers.command.commands.menubar.files.LoadStateCommand;
import ets.log121_labo5.controllers.command.commands.menubar.files.QuitCommand;
import ets.log121_labo5.controllers.command.commands.menubar.files.SaveStateCommand;
import ets.log121_labo5.models.observer.Observable;
import ets.log121_labo5.models.observer.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

public class MainController implements Observer {

    @FXML private GridPane rootPane;

    // MENUBAR: FICHIER
    @FXML private MenuItem saveAppStateItem;
    @FXML private MenuItem loadAppStateItem;
    @FXML private MenuItem loadImageItem;
    @FXML private MenuItem quitItem;
    // MENUBAR: ÉDITION
    @FXML private MenuItem undoItem;
    @FXML private MenuItem redoItem;

    // CONTROLLERS
    @FXML private StackPane thumbnailPane;
    @FXML private ImageViewerController thumbnailPaneController;
    @FXML private StackPane leftsidePane;
    @FXML private ImageNavigatorController leftsidePaneController;
    @FXML private StackPane rightsidePane;
    @FXML private ImageNavigatorController rightsidePaneController;

    // CONTEXT MENU CONTROLLER
    @FXML private ContextMenuController contextMenuController;

    // UI
    @FXML
    private void initialize() {
        CommandsManager manager = CommandsManager.getInstance();
        manager.addObserver(this);

        /* --- MENUBAR --- */
            // FICHIER
        this.saveAppStateItem.setOnAction(new SaveStateCommand());
        this.loadAppStateItem.setOnAction(new LoadStateCommand());
        this.loadImageItem.setOnAction(new LoadImageCommand());
        this.quitItem.setOnAction(new QuitCommand());
            // ÉDITION
        this.undoItem.setOnAction(new UndoCommand());
        this.redoItem.setOnAction(new RedoCommand());

        /* --- PERSPECTIVE --- */
//        CommandsManager manager = CommandsManager.getInstance();

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
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\ets\\log121_labo5\\images\\moon.jpg";
        manager.setImage(new Image(path));
    }

    @Override
    public void update(Observable observable) {
//        double width = this.leftsidePane.getWidth() + this.rightsidePane.getWidth();
//
//        System.out.println(this.rootPane.getWidth());
//        this.rootPane.setMinWidth(width);
//        System.out.println(this.rootPane.getWidth());
    }
}
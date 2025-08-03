package ets.log121_labo5.controllers;

import ets.log121_labo5.models.PerspectiveGetter;
import ets.log121_labo5.models.PerspectiveSetter;
import ets.log121_labo5.models.CommandsManager;
import ets.log121_labo5.controllers.commands.menubar.edit.RedoCommand;
import ets.log121_labo5.controllers.commands.menubar.edit.UndoCommand;
import ets.log121_labo5.controllers.commands.menubar.files.LoadImageCommand;
import ets.log121_labo5.controllers.commands.menubar.files.LoadStateCommand;
import ets.log121_labo5.controllers.commands.menubar.files.QuitCommand;
import ets.log121_labo5.controllers.commands.menubar.files.SaveStateCommand;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Record: MainController
 * Created on: 7/6/2025
 * Description: Contrôleur primaire à la racine de tous les sous-panneaux.
 *
 * @author liuzi | Zi heng Liu
 */

public class MainController {

    // MENUBAR: FICHIER
    @FXML private MenuItem saveAppStateItem;
    @FXML private MenuItem loadAppStateItem;
    @FXML private MenuItem loadImageItem;
    @FXML private MenuItem quitItem;
    // MENUBAR: ÉDITION
    @FXML private MenuItem undoItem;
    @FXML private MenuItem redoItem;

    // CONTROLLERS
    @FXML private StackPane leftsidePane;
    @FXML private ImageNavigatorController leftsidePaneController;
    @FXML private StackPane rightsidePane;
    @FXML private ImageNavigatorController rightsidePaneController;

    // UI
    @FXML
    private void initialize() {
        /* --- MENUBAR --- */

        // Ajout des fonctionnalités de commandes aux boutons du bar de menu.
        // FICHIER
        this.saveAppStateItem.setOnAction(new SaveStateCommand());
        this.loadAppStateItem.setOnAction(new LoadStateCommand());
        this.loadImageItem.setOnAction(new LoadImageCommand());
        this.quitItem.setOnAction(new QuitCommand());
        // ÉDITION
        this.undoItem.setOnAction(new UndoCommand());
        this.redoItem.setOnAction(new RedoCommand());

        /* --- PERSPECTIVE --- */

        // Nous passons par la voie d'interfaces fonctionnelles afin de définir
        // les accesseurs de chaque instance de ImageNavigatorController afin
        // d'éviter de devoir créer des classes ou des méthodes supplémentaires
        // soit au niveau du gestionnaire de commandes(CommandsManager) ou au
        // niveau des contrôleurs. Ces interfaces fonctionnels diminue donc
        // le masse de code nécéssaire. Il est aussi à noter que si nous décidons
        // d'ajouter d'autres panneaux d'images dans une mise à jour futur de l'application,
        // il ne serait pas impossible de réusiner le code afin d'utiliser
        // une collection d'objets Perspective et de contrôleurs ImageNavigatorController en
        // utilisant ce même méthode.
        CommandsManager manager = CommandsManager.getInstance();

        PerspectiveGetter leftsideGetter = manager::getLeftside;
        PerspectiveSetter leftsideSetter = manager::setLeftside;
        this.leftsidePaneController.setPerspectiveAccessors(leftsideGetter, leftsideSetter);

        PerspectiveGetter rightsideGetter = manager::getRightside;
        PerspectiveSetter rightsideSetter = manager::setRightside;
        this.rightsidePaneController.setPerspectiveAccessors(rightsideGetter, rightsideSetter);

        // Enlever les commentaires pour charger une image
        // tout de suite après le lancement de l'application.
//        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\ets\\log121_labo5\\images\\moon.jpg";
//        manager.setImage(new Image(path));
    }
}
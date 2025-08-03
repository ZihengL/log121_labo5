package ets.log121_labo5.controllers;


import ets.log121_labo5.controllers.commands.imageviewCommand.contextmenu.*;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.ImageView;


/**
 * Class: ContextMenuController
 * Created on: 7/12/2025
 * Description: Classe contrôleur pour le menu de contexte.
 *
 * @author liuzi | Zi heng Liu
 */

public class ContextMenuController {

    // STATIC

    // Singleton parce qu'une instance unique du contrôleur existe afin de pouvoir copier/coller
    // la Perspective d'un ImageView à un autre. Par conséquent, nous ne faisons
    // que propager, ou ajouter sa fonctionnalité aux ImageViews.
    private static final ContextMenuController contextMenuController = new ContextMenuController();

    public static ContextMenuController getInstance() {
        return ContextMenuController.contextMenuController;
    }

    // Ajoute la fonction de pouvoir cacher et révéler le menu de contexte à
    // l'ImageView en paramètre lors d'un clique.
    public static void addFunctionToView(ImageView view) {
        ContextMenu menu = contextMenuController.menu;

        view.setOnMousePressed(new ShowContextMenuCommand(menu));
    }

    // INSTANCE

    @FXML private final ContextMenu menu;

    // Instanciation et initialisation du menu clique-droit avec ses sous-menus,
    // ce qui inclue l'option de copier/coller, ainsi que les stratégies de copie.
    public ContextMenuController() {
        this.menu = new ContextMenu();

        // COPIER/COLLER
        MenuItem copyItem = new MenuItem("Copier");
        copyItem.setOnAction(new CopyCommand());

        MenuItem pasteItem = new MenuItem("Coller");
        pasteItem.setOnAction(new PasteCommand());

        SeparatorMenuItem separator = new SeparatorMenuItem();

        // STRATÉGIE DE COPIE
        RadioMenuItem radioToggleCopyZoom = new RadioMenuItem("Zoom");
        radioToggleCopyZoom.setOnAction(new ToggleCopyZoomCommand());
        radioToggleCopyZoom.setSelected(true);

        RadioMenuItem radioToggleCopyPosition = new RadioMenuItem("Position");
        radioToggleCopyPosition.setOnAction(new ToggleCopyPositionCommand());
        radioToggleCopyPosition.setSelected(true);

        this.menu.getItems().setAll(
                copyItem,
                pasteItem,
                separator,
                radioToggleCopyZoom,
                radioToggleCopyPosition
        );
    }
}

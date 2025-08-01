package ets.log121_labo5.controllers;


import ets.log121_labo5.models.Perspective;
import ets.log121_labo5.models.command.commands.contextmenu.*;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.Pane;


/**
 * Class: ContextMenuController
 * Created on: 7/12/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class ContextMenuController {

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

    // On ajoute la fonctionalité du menu clique-droit à tous les panneaux en argument.
    // Pas de classe Command puisque nous n'avons pas de raison d'enregistrer l'ouverture
    // ou la fermeture d'une fenêtre de contexte.
    public void addToPanes(Pane ...panes) {
        for (Pane pane : panes)
            pane.setOnMousePressed(event -> {
                if (!this.menu.isShowing() && event.isSecondaryButtonDown())
                    this.menu.show(pane, event.getScreenX(), event.getScreenY());
                else
                    this.menu.hide();

                event.consume();
            });
    }
}

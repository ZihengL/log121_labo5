package ets.log121_labo5.controllers;


import ets.log121_labo5.models.command.commands.contextmenu.CopyCommand;
import ets.log121_labo5.models.command.commands.contextmenu.PasteCommand;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
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

    private PerspectiveGetter copyGetter;

    @FXML private final ContextMenu menu;

    // Instanciation et initialisation du menu clique-droit avec ses sous-menus(copier/coller).
    public ContextMenuController() {
        this.menu = new ContextMenu();

        MenuItem copyItem = new MenuItem("Copier");
        copyItem.setOnAction(new CopyCommand());

        MenuItem pasteItem = new MenuItem("Coller");
        pasteItem.setOnAction(new PasteCommand());

        this.menu.getItems().setAll(copyItem, pasteItem);
    }

    // On ajoute la fonctionalité du menu clique-droit à tous les panneaux en argument.
    // Pas de classe Command puisque nous n'avons pas de raison d'enregistrer l'ouverture
    // ou la fermeture d'une fenêtre de contexte.
    public void addToPanes(Pane ...panes) {
        for (Pane pane : panes) {
            EventHandler<ContextMenuEvent> handler = event -> {
                if (!this.menu.isShowing())
                    this.menu.show(pane, event.getScreenX(), event.getScreenY());
                else
                    this.menu.hide();

                event.consume();
            };

            pane.setOnContextMenuRequested(handler);
        }
    }
}

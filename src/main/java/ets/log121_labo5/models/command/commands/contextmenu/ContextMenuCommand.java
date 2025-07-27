package ets.log121_labo5.models.command.commands.contextmenu;


import ets.log121_labo5.controllers.ImageNavigatorController;
import ets.log121_labo5.models.Perspective;
import ets.log121_labo5.models.command.Command;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;

/**
 * Class: ContextMenuCommand
 * Created on: 7/13/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public abstract class ContextMenuCommand extends Command<ActionEvent> {

    protected static Perspective copy = null;

    // Fonctionne en tandem avec les instances de lambdas dans PerspectiveGetter/PerspectiveSetter
    // afin de pouvoir accéder à la bonne instance de Perspective dépendamment de l'instance
    // de ImageNavigatorController.
    protected ImageNavigatorController getEventController(ActionEvent event) {
        Node node = ((MenuItem) event.getSource()).getParentPopup().getOwnerNode();

        return (ImageNavigatorController) node.getProperties().get("controller");
    }
}

package ets.log121_labo5.controllers.command;


import ets.log121_labo5.controllers.ImageNavigatorController;
import javafx.event.ActionEvent;
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

    // Fonctionne en tandem avec les instances de lambdas dans PerspectiveGetter/PerspectiveSetter
    // afin de pouvoir accéder à la bonne instance de Perspective dépendamment de l'instance
    // de ImageNavigatorController.
    protected ImageNavigatorController getSourceController(ActionEvent event) {
        Node node = ((MenuItem) event.getSource()).getParentPopup().getOwnerNode();

        return (ImageNavigatorController) node.getProperties().get("controller");
    }
}

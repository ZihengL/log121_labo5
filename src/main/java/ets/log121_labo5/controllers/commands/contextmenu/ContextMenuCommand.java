package ets.log121_labo5.controllers.commands.contextmenu;


import ets.log121_labo5.controllers.ImageNavigatorController;
import ets.log121_labo5.models.Perspective;
import ets.log121_labo5.controllers.commands.Command;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;

/**
 * Class: ContextMenuCommand
 * Created on: 7/13/2025
 * Description: Classe parent de tous les classes du menu de contexte. Les méthodes/attributs
 * statiques servent comme le gestionnaire d'options de copie. Bien que nous aurons pu
 * créer une classe dédiée à ce rôle,
 *
 * @author liuzi | Zi heng Liu
 */

public abstract class ContextMenuCommand implements Command<ActionEvent> {

    // STATIC

    private static Perspective copy = null;
    private static ArrayList<CopyModifier> modifiers = new ArrayList<>();

    protected static void addModifier(CopyModifier modifier) {
        modifiers.add(modifier);
    }

    protected static Perspective getModifiedCopy(Perspective target) {
        Perspective copy = ContextMenuCommand.copy;
        if (copy == null) return target;

        for (CopyModifier modifier : modifiers)
            if (modifier.isActive)
                target = modifier.applyModifier(target, copy);

        return target;
    }

    protected static void setCopy(Perspective perspective) {
        copy = perspective.copy();
    }

    // INSTANCE

    // Fonctionne en tandem avec les instances de lambdas dans PerspectiveGetter/PerspectiveSetter
    // afin de pouvoir accéder à la bonne instance de Perspective dépendamment de l'instance
    // de ImageNavigatorController.
    protected ImageNavigatorController getEventController(ActionEvent event) {
        Node node = ((MenuItem) event.getSource()).getParentPopup().getOwnerNode();

        return (ImageNavigatorController) node.getProperties().get("controller");
    }
}

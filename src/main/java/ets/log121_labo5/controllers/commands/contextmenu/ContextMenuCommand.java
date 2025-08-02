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
 * statiques servent comme un gestionnaire d'options de copie. Idéalement, nous aurons pu créer
 * une classe dans le modèle dédiée à ce rôle. Néanmoins, la structure présente à été conçu pour
 * permettre une grande fléxibilité au code ainsi qu'il minimise la manutention si l'on décide
 * d'ajouter des fonctionnalités au copier/coller. La dette prise pour ce raccourci est donc minime
 * en considérant qu'un réusinage dans le futur ne serait pas trop difficile.
 *
 * @author liuzi | Zi heng Liu
 */

public abstract class ContextMenuCommand implements Command<ActionEvent> {

    // STATIC

    // Nous avons une instance de l'objet que l'on veut copier
    // et une liste d'abonnés de type CopyModifier. Ce type à
    // pour rôle de transmettre des attributs d'un objet
    // Perspective à un autre.
    private static Perspective copy = null;
    private static final ArrayList<CopyModifier> modifiers = new ArrayList<>();

    protected static void setCopy(Perspective perspective) {
        copy = perspective.copy();
    }

    // Ajoute un CopyModifier à la liste.
    protected static void addModifier(CopyModifier modifier) {
        modifiers.add(modifier);
    }

    // Méthode invoquée lorsque l'utilisateur active l'action de coller.
    // On traverse la liste de CopyModifier afin de transmettre les attributs
    // de la copie au récipient de manière séquentielle, et on retourne le résultat.
    // Cette approche nous permet d'ajouter autant de fonctionnalités que nous le désirons avec facilité.
    protected static void applyModifiers(Perspective recipient) {
        if (ContextMenuCommand.copy == null) return;

        for (CopyModifier modifier : modifiers)
                modifier.applyModifier(recipient, ContextMenuCommand.copy);
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

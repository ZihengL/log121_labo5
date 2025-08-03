package ets.log121_labo5.controllers.commands.imageviewCommand.contextmenu;


import ets.log121_labo5.models.Perspective;
import javafx.event.ActionEvent;

/**
 * Class: PasteCommand
 * Created on: 7/12/2025
 * Description: Classe commande qui hérite de ContextMenuCommand qui applique
 * les stratégies de transmission d'attributs courament en sélection au Perspective
 * destinataire.
 *
 * @author liuzi | Zi heng Liu
 */

public class PasteCommand extends ContextMenuCommand {

    // Le récipient est la Perspective où l'utilisateur veut appliquer
    // les stratégies de copie. On invoque applyModifiers() avec le récipient
    // en paramètre, qui à son tour applique les stratégies de copie séquentiellement.
    // Finalement, on fait appel à setPerspective() du contrôleur du Perspective
    // destinataire pour appliquer les modifications.
    @Override
    public void handle(ActionEvent event) {
//        ImageNavigatorController controller = this.getEventController(event);
//        Perspective recipient = controller.getPerspective().copy();
        Perspective recipient = this.getPerspective(event).copy();
        if (recipient == null) return;

        ContextMenuCommand.applyModifiers(recipient);
        this.setPerspective(event, recipient);
//        controller.setPerspective(recipient);
    }
}

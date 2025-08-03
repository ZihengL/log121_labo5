package ets.log121_labo5.controllers.commands.imageviewCommand.contextmenu;

import ets.log121_labo5.models.Perspective;

/**
 * Class: ToggleCopyPositionCommand
 * Created on: 7/30/2025
 * Description: Stratégie qui copie la position du modèle.
 *
 * @author liuzi | Zi heng Liu
 */

public class ToggleCopyPositionCommand extends CopyModifier {

    // On transfert à partir du centre du modèle au centre du récipient
    // puisque par défaut la position minX/minY se réfèrre au coin supérieur
    // gauche du rectangle constituant la vue. Au même niveau de zoom, ce n'est
    // pas un problème, mais autrement l'effet devient inattendu et donc pourquoi
    // nous nous référrons au centre.
    @Override
    public void applyModifier(Perspective recipient, Perspective model) {
        if (this.isActive)
            recipient.setCenter(model.getCenter());
    }
}

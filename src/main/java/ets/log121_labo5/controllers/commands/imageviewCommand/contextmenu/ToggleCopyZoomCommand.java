package ets.log121_labo5.controllers.commands.imageviewCommand.contextmenu;


import ets.log121_labo5.models.Perspective;

/**
 * Class: ToggleCopyZoomCommand
 * Created on: 7/30/2025
 * Description: Stratégie de copie qui applique le niveau de zoom
 * du modèle au récipient.
 *
 * @author liuzi | Zi heng Liu
 */

public class ToggleCopyZoomCommand extends CopyModifier {

    // Le niveau de zoom n'est rien d'autre que les dimensions
    // du rectangle de la vue.
    @Override
    public void applyModifier(Perspective recipient, Perspective model) {
        if (this.isActive)
            recipient.setSize(model.getSize());
    }
}

package ets.log121_labo5.controllers.command.commands.contextmenu;

import ets.log121_labo5.models.Perspective;

/**
 * Class: ToggleCopyPositionCommand
 * Created on: 7/30/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class ToggleCopyPositionCommand extends CopyModifier {

    @Override
    public Perspective applyModifier(Perspective target, Perspective model) {
        target.setPosition(model.getViewportPosition());
        return target;
    }
}

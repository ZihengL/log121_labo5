package ets.log121_labo5.controllers.commands.contextmenu;


import ets.log121_labo5.models.Perspective;

/**
 * Class: CopyZoomDecorator
 * Created on: 7/30/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class ToggleCopyZoomCommand extends CopyModifier {

    @Override
    public Perspective applyModifier(Perspective target, Perspective model) {
        target.setSize(model.getSize());
        return target;
    }
}

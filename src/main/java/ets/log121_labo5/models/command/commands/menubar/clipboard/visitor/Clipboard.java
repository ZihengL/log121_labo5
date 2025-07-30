package ets.log121_labo5.models.command.commands.menubar.clipboard.visitor;


import ets.log121_labo5.models.Perspective;

/**
 * Class: Clipboard
 * Created on: 7/30/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public abstract class Clipboard {

    Perspective perspective;

    public Perspective getCopy() {
        return this.perspective;
    }
}

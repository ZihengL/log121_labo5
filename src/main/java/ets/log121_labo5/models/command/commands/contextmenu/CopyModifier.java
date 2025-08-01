package ets.log121_labo5.models.command.commands.contextmenu;


import ets.log121_labo5.models.Perspective;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioMenuItem;

/**
 * Class: CopyModifierCommand
 * Created on: 7/31/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public abstract class CopyModifier extends ContextMenuCommand {

    protected boolean isActive;

    public CopyModifier() {
        this(true);
    }

    public CopyModifier(boolean isActive) {
        this.isActive = isActive;

        ContextMenuCommand.addModifier(this);
    }

    @Override
    public void execute(ActionEvent event) {
        this.isActive = ((RadioMenuItem) event.getSource()).isSelected();
    }

    public abstract Perspective applyModifier(Perspective target, Perspective model);
}

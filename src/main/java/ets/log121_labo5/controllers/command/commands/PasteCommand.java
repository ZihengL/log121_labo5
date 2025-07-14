package ets.log121_labo5.controllers.command.commands;


import ets.log121_labo5.controllers.ContextMenuCommand;
import ets.log121_labo5.controllers.ImageNavigatorController;
import ets.log121_labo5.controllers.command.Command;
import ets.log121_labo5.models.Perspective;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;

/**
 * Class: PasteCommand
 * Created on: 7/12/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class PasteCommand extends ContextMenuCommand {

    private final CopyCommand copyCommand;

    public PasteCommand(CopyCommand copyCommand) {
        this.copyCommand = copyCommand;
    }

    @Override
    public void execute(ActionEvent event) {
        Perspective perspective = this.copyCommand.getPerspective();

        if (perspective != null) {
            ImageNavigatorController controller = this.getSourceController(event);

            controller.setPerspective(perspective);
        }
    }

    @Override
    public void undo() {

    }
}

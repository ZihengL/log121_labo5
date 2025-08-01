package ets.log121_labo5.controllers.command.commands.contextmenu;


import ets.log121_labo5.controllers.ImageNavigatorController;
import ets.log121_labo5.models.Perspective;
import javafx.event.ActionEvent;

/**
 * Class: PasteCommand
 * Created on: 7/12/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class PasteCommand extends ContextMenuCommand {

    @Override
    public void handle(ActionEvent event) {
        ImageNavigatorController controller = this.getEventController(event);

        Perspective target = controller.getPerspective().copy();
        Perspective copy = ContextMenuCommand.getModifiedCopy(target);

        if (copy != null)
            controller.setPerspective(copy);
    }
}

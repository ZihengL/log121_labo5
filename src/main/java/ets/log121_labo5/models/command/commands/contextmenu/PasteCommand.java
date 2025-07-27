package ets.log121_labo5.models.command.commands.contextmenu;


import ets.log121_labo5.controllers.ImageNavigatorController;
import ets.log121_labo5.models.Perspective;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;

/**
 * Class: PasteCommand
 * Created on: 7/12/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class PasteCommand extends ContextMenuCommand {

    @Override
    public void execute(ActionEvent event) {
        if (ContextMenuCommand.copy == null) return;

        ImageNavigatorController controller = this.getEventController(event);
        controller.setPerspective(ContextMenuCommand.copy);
    }

    @Override
    public void undo() {

    }

//    public void executeOLD(ActionEvent event) {
//        Perspective perspective = this.copyCommand.getPerspective();
//
//        if (perspective != null) {
//            ImageNavigatorController controller = this.getEventController(event);
//
//            controller.setPerspective(perspective);
//        }
//    }
}

package ets.log121_labo5.models.command.commands.contextmenu;


import ets.log121_labo5.controllers.ImageNavigatorController;
import ets.log121_labo5.models.Perspective;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.RadioMenuItem;

/**
 * Class: CopyCommand
 * Created on: 7/12/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class CopyCommand extends ContextMenuCommand {

    // Instance de Perspective donnée par le PerspectiveGetter de leftside/rightside.
    // On utilise une copie pour briser la référence et maintenir son état actuel.
    @Override
    public void execute(ActionEvent event) {
        ImageNavigatorController controller = this.getEventController(event);
        ContextMenuCommand.setCopy(controller.getPerspective());
    }
}

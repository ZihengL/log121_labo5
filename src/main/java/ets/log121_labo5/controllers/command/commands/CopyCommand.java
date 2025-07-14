package ets.log121_labo5.controllers.command.commands;


import ets.log121_labo5.controllers.command.ContextMenuCommand;
import ets.log121_labo5.controllers.ImageNavigatorController;
import ets.log121_labo5.models.Perspective;
import javafx.event.ActionEvent;

/**
 * Class: CopyCommand
 * Created on: 7/12/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class CopyCommand extends ContextMenuCommand {

    private Perspective perspective;

    @Override
    public void execute(ActionEvent event) {
        ImageNavigatorController controller = this.getSourceController(event);

        // Instance de Perspective donnée par le PerspectiveGetter de leftside/rightside.
        this.perspective = controller.getPerspective();
    }

    @Override
    public void undo() {

    }

    public Perspective getPerspective() {
        return this.perspective;
    }
}

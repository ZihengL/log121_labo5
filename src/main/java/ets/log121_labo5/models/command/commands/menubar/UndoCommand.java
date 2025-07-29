package ets.log121_labo5.models.command.commands.menubar;


import ets.log121_labo5.models.command.Command;
import ets.log121_labo5.models.command.CommandsManager;
import javafx.event.ActionEvent;

/**
 * Class: UndoCommand
 * Created on: 7/11/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class UndoCommand extends Command<ActionEvent> {

    @Override
    public void execute(ActionEvent event) {
        CommandsManager.getInstance().undo();
    }
}

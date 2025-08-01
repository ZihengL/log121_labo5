package ets.log121_labo5.controllers.command.commands.menubar.edit;


import ets.log121_labo5.controllers.command.Command;
import ets.log121_labo5.controllers.command.CommandsManager;
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
    public void handle(ActionEvent event) {
        CommandsManager.getInstance().undo();
    }
}

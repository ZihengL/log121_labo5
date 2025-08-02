package ets.log121_labo5.controllers.command.commands.menubar.edit;


import ets.log121_labo5.controllers.command.Command;
import ets.log121_labo5.controllers.command.CommandsManager;
import javafx.event.ActionEvent;

/**
 * Class: RedoCommand
 * Created on: 7/11/2025
 * Description: Classe dérivative de Command qui invoque
 * la méthode redo(), ou refaire, de CommandsManager.
 *
 * @author liuzi | Zi heng Liu
 */

public class RedoCommand extends Command<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        CommandsManager.getInstance().redo();
    }
}

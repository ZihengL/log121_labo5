package ets.log121_labo5.controllers.command.commands;


import ets.log121_labo5.controllers.command.Command;
import ets.log121_labo5.controllers.command.CommandsManager;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

/**
 * Class: QuitCommand
 * Created on: 7/11/2025
 * Description: Commande qui éxécute l'action de quitter l'application.
 *
 * @author liuzi | Zi heng Liu
 */

public class QuitCommand extends Command<ActionEvent> {

    // On n'invoque
    @Override
    public void execute(ActionEvent event) {
//        CommandsManager manager = CommandsManager.getInstance();
//
//        manager.quitApplication(Command.stage);
        Command.stage.close();
    }

    // Rien pour undo parce qu'on ne peut pas défaire l'action de quitter l'application.
    @Override
    public void undo() {}
}

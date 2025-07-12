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

public class QuitCommand extends Command {

    // On quitte en passant en argument la fenêtre principale de l'application
    // lorsqu'on invoque quitApp() du gestionnaire de commandes.
    @Override
    public void execute(ActionEvent event) {
        CommandsManager manager = CommandsManager.getInstance();

        manager.quitApplication(Command.stage);
    }

    // Rien pour undo parce qu'on ne peut pas défaire l'action de quitter l'application.
    @Override
    public void undo() {}
}

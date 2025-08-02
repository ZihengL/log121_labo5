package ets.log121_labo5.controllers.command.commands.menubar.files;


import ets.log121_labo5.Application;
import ets.log121_labo5.controllers.command.Command;
import ets.log121_labo5.controllers.command.CommandsManager;
import javafx.event.ActionEvent;

/**
 * Class: QuitCommand
 * Created on: 7/11/2025
 * Description: Commande qui éxécute l'action de quitter l'application.
 * Il ne réfère pas au gestionnaire de commandes pour éxécuter l'action
 * puisqu'il nécéssite pas de changement d'états des données(puisque tout est
 * détruit à la fermeture du programme).
 *
 * @author liuzi | Zi heng Liu
 */

public class QuitCommand extends Command<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        Application.getStage().close();
    }
}

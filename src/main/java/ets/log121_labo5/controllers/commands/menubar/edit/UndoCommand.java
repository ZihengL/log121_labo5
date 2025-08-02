package ets.log121_labo5.controllers.commands.menubar.edit;


import ets.log121_labo5.controllers.commands.Command;
import ets.log121_labo5.models.CommandsManager;
import javafx.event.ActionEvent;

/**
 * Class: UndoCommand
 * Created on: 7/11/2025
 * Description: Classe commande qui invoque undo() du gestionnaire de commandes.
 *
 * @author liuzi | Zi heng Liu
 */

public class UndoCommand implements Command<ActionEvent> {

    @Override
    public void handle(ActionEvent event) {
        CommandsManager.getInstance().undo();
    }
}

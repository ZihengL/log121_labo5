package ets.log121_labo5.controllers.command;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Class: Commander
 * Created on: 7/9/2025
 * Description: Interface générique qui déclare les services nécessaires pour quelconque classe
 * qui s'occupe d'implémenter une commande spécifique.
 *
 * @author liuzi | Zi heng Liu
 */

public interface Command extends EventHandler<ActionEvent> {

    public void execute();

    public void undo();
}

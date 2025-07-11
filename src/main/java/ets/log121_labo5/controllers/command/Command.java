package ets.log121_labo5.controllers.command;


/**
 * Class: Commander
 * Created on: 7/9/2025
 * Description: Interface générique qui déclare les services nécessaires pour quelconque classe
 * qui s'occupe d'implémenter une commande spécifique.
 *
 * @author liuzi | Zi heng Liu
 */

public interface Command {

    public void execute();

    public void undo();
}

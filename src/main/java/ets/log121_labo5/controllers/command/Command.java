package ets.log121_labo5.controllers.command;


/**
 * Class: Commander
 * Created on: 7/9/2025
 * Description: Interface générique qui déclare le service unique que doit
 * avoir une classe qui implémente celle-ci; l'éxécution d'une action selon un état.
 *
 * @author liuzi | Zi heng Liu
 */

public interface Command {

    public void execute();

    public void undo();
}

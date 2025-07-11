package ets.log121_labo5.controllers.command.commands;


import ets.log121_labo5.controllers.command.CommandsManager;

/**
 * Class: SavePerspectiveAction
 * Created on: 7/9/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class SaveStateCommand extends GenericCommand {

    public SaveStateCommand() {

    }

    @Override
    public void execute() {
        CommandsManager manager = CommandsManager.getInstance();

        // TODO
    }

    @Override
    public void undo() {

    }
}

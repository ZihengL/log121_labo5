package ets.log121_labo5.controllers.command.commands;


import ets.log121_labo5.controllers.command.Command;
import ets.log121_labo5.controllers.command.CommandsManager;
import ets.log121_labo5.models.Perspective;
import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Arrays;

/**
 * Class: SavePerspectiveAction
 * Created on: 7/9/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class SavePerspective implements Command {

    public SavePerspective() {

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

package ets.log121_labo5.controllers.command.commands;


import ets.log121_labo5.controllers.command.CommandsManager;
import ets.log121_labo5.controllers.command.FileDialogCommand;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Class: SavePerspectiveAction
 * Created on: 7/9/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class SaveStateCommand extends FileDialogCommand {

    // TEMPLATE INHERITED

    @Override
    protected void setDialogOptions(FileChooser fc) {
        fc.setTitle("Sauvegarder l'état courante.");
    }

    @Override
    protected File fireDialog(Stage stage, FileChooser fc) {
        return fc.showSaveDialog(stage);
    }

    @Override
    protected void invokeCommand(File file) {
        CommandsManager.getInstance().saveState(file);
    }

    // COMMAND INHERITED

    @Override
    public void undo() {

    }
}

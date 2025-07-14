package ets.log121_labo5.controllers.command.commands;


import ets.log121_labo5.controllers.command.CommandsManager;
import ets.log121_labo5.controllers.command.FileDialogCommand;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

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

        super.setDialogOptions(fc);
    }

    @Override
    protected File fireDialog(Stage stage, FileChooser fc) {
        return fc.showSaveDialog(stage);
    }

    @Override
    protected void invokeCommand(File file) {
        try {
            CommandsManager.getInstance().saveState(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // COMMAND INHERITED

    @Override
    public void undo() {

    }
}

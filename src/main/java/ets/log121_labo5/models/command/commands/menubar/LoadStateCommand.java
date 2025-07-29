package ets.log121_labo5.models.command.commands.menubar;


import ets.log121_labo5.models.command.CommandsManager;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Class: LoadPerspectiveAction
 * Created on: 7/9/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class LoadStateCommand extends FileDialogCommand {

    // TEMPLATE INHERITED

    @Override
    protected void setDialogOptions(FileChooser fc) {
        fc.setTitle("Charger un état sauvegardé");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Serializable", "*.ser")
        );

        super.setDialogOptions(fc);
    }

    @Override
    protected File fireDialog(Stage stage, FileChooser fc) {
        return fc.showOpenDialog(stage);
    }

    @Override
    protected void invokeCommand(File file) {
        try {
            CommandsManager.getInstance().loadStateFromFiles(file);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

package ets.log121_labo5.models.command.commands.menubar;

import ets.log121_labo5.models.command.CommandsManager;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class LoadImageCommand extends FileDialogCommand {

    // TEMPLATE INHERITED

    @Override
    protected void setDialogOptions(FileChooser fc) {
        fc.setTitle("Charger un image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")
        );

        super.setDialogOptions(fc);
    }

    @Override
    protected File fireDialog(Stage stage, FileChooser fc) {
        return fc.showOpenDialog(stage);
    }

    @Override
    protected void invokeCommand(File file) {
        CommandsManager.getInstance().loadImage(file);
    }
}

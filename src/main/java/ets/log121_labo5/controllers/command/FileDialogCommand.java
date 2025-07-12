package ets.log121_labo5.controllers.command;


import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Class: FileChooserDialogCommand
 * Created on: 7/11/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public abstract class FileDialogCommand extends Command {

    // TEMPLATE METHOD
    @Override
    public final void execute(ActionEvent event) {
        FileChooser fc = new FileChooser();
        this.setDialogOptions(fc);

        File file = this.fireDialog(Command.stage, fc);
        if (file != null)
            this.invokeCommand(file);
    }

    protected abstract void setDialogOptions(FileChooser fc);

    protected abstract File fireDialog(Stage stage, FileChooser fc);

    protected abstract void invokeCommand(File file);
}

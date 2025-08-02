package ets.log121_labo5.controllers.commands.menubar.files;


import ets.log121_labo5.Application;
import ets.log121_labo5.controllers.commands.Command;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Class: FileChooserDialogCommand
 * Created on: 7/11/2025
 * Description: Classe abstraite qui défini une méthode skelette pour le procès de
 * la création ou la récupération d'un fichier à partir d'une fenêtre modale de type
 * FileChooser
 *
 * @author liuzi | Zi heng Liu
 */

public abstract class FileDialogCommand implements Command<ActionEvent> {

    public static final File DEFAULT_DIRECTORY = new File(".\\src\\main\\resources\\ets\\log121_labo5");

    // TEMPLATE METHOD
    @Override
    public final void handle(ActionEvent event) {
        FileChooser fc = new FileChooser();
        this.setDialogOptions(fc);

        File file = this.fireDialog(Application.getStage(), fc);
        if (file != null)
            this.invokeCommand(file);
    }

    protected void setDialogOptions(FileChooser fc) {
        File target = FileDialogCommand.DEFAULT_DIRECTORY;
        if (target.exists())
            fc.setInitialDirectory(target);
    }

    protected abstract File fireDialog(Stage stage, FileChooser fc);

    protected abstract void invokeCommand(File file);
}

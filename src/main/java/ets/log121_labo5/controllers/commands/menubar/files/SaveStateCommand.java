package ets.log121_labo5.controllers.commands.menubar.files;


import ets.log121_labo5.models.CommandsManager;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Class: SaveStateCommand
 * Created on: 7/9/2025
 * Description: Classe commande de type FileDialogCommand. À l'éxécution,
 * il crée une fenêtre qui invite l'utilisateur à créer ou écraser un fichier
 * existant , et invoque
 *
 * @author liuzi | Zi heng Liu
 */

public class SaveStateCommand extends FileDialogCommand {

    // TEMPLATE INHERITED

    @Override
    protected void setDialogOptions(FileChooser fc) {
        fc.setTitle("Sauvegarder l'état courant.");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Serializable", "*.ser")
        );

        File dir = new File(FileDialogCommand.DEFAULT_DIRECTORY + "\\saves");
        this.setDefaultDirectory(fc, dir);
    }

    @Override
    protected File fireDialog(Stage stage, FileChooser fc) {
        return fc.showSaveDialog(stage);
    }

    @Override
    protected void invokeCommand(File file) {
        try {
            CommandsManager.getInstance().saveStateToFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

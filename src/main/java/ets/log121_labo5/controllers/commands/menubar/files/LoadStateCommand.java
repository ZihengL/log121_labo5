package ets.log121_labo5.controllers.commands.menubar.files;


import ets.log121_labo5.models.CommandsManager;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Class: LoadStateCommand
 * Created on: 7/9/2025
 * Description: Classe commande de type FileDialogCommand qui invite
 * l'utilisateur à récolter un fichier d'extension .ser, et invoque
 * la méthode loadStateFromFile() du gestionnaire de commandes avec le
 * fichier en argument.
 *
 * @author liuzi | Zi heng Liu
 */

public class LoadStateCommand extends FileDialogCommand {

    // TEMPLATE INHERITED

    // Applique un filtre qui considère seulement les fichiers .ser comme valide
    // et déplace l'usager au répertoire des sauvegardes.
    @Override
    protected void setDialogOptions(FileChooser fc) {
        fc.setTitle("Charger un état sauvegardé");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Serializable", "*.ser")
        );

        File dir = new File(FileDialogCommand.DEFAULT_DIRECTORY + "\\saves");
        this.setDefaultDirectory(fc, dir);
    }

    @Override
    protected File fireDialog(Stage stage, FileChooser fc) {
        return fc.showOpenDialog(stage);
    }

    // Invoque la méthode loadState() du gestionnaire de commandes avec le fichier choisi
    // en paramètre. Nous n'avons pas développé la gestion d'erreurs plus en profondeur
    // pour des raisons de temps.
    @Override
    protected void invokeCommand(File file) {
        try {
            CommandsManager.getInstance().loadStateFromFile(file);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

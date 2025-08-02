package ets.log121_labo5.controllers.commands.menubar.files;

import ets.log121_labo5.models.CommandsManager;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class LoadImageCommand extends FileDialogCommand {

    // TEMPLATE INHERITED

    // Filtre d'extensions les plus communs pour les formats d'images.
    // Nous plaçons également l'utilisateur au répertoire défaut de l'application
    // pour stocker les images.
    @Override
    protected void setDialogOptions(FileChooser fc) {
        fc.setTitle("Charger un image");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg")
        );

        File dir = new File(FileDialogCommand.DEFAULT_DIRECTORY + "\\images");
        this.setDefaultDirectory(fc, dir);
    }

    @Override
    protected File fireDialog(Stage stage, FileChooser fc) {
        return fc.showOpenDialog(stage);
    }

    // Si valide, on charge l'image et on invoque setImage() du gestionnaire de commandes.
    @Override
    protected void invokeCommand(File file) {
        if (!file.exists()) return;

        Image image = new Image(file.toURI().toString());
        CommandsManager.getInstance().setImage(image);
    }
}

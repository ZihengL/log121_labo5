package ets.log121_labo5.controllers.command.commands;


import ets.log121_labo5.controllers.command.Command;
import ets.log121_labo5.controllers.command.CommandsManager;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Class: LoadPerspectiveAction
 * Created on: 7/9/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class LoadPerspective extends GenericCommand {

    @Override
    public void execute() {
        try {
            JFileChooser fc = new JFileChooser();

            int option = fc.showOpenDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                FileInputStream inputStream = new FileInputStream(fc.getSelectedFile());
                Image image = new Image(inputStream);

                CommandsManager.getInstance().setImage(image);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void undo() {

    }
}

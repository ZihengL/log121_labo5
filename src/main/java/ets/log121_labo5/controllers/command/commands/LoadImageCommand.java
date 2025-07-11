package ets.log121_labo5.controllers.command.commands;

import ets.log121_labo5.controllers.command.CommandsManager;
import ets.log121_labo5.models.Perspective;
import javafx.scene.image.Image;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoadImageCommand extends GenericCommand {

    @Override
    public void execute() {
        try {
            JFileChooser fc = new JFileChooser();

            int option = fc.showOpenDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                FileInputStream inputStream = new FileInputStream(fc.getSelectedFile());
                Image image = new Image(inputStream);

//                CommandsManager.getInstance().setImage(image);
                // TODO: TEMP SETTER. RESETTING ALL PERSPECTIVE ON LOADING A NEW IMAGE
                CommandsManager.getInstance().set(image, new Perspective(), new Perspective());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void undo() {

    }
}

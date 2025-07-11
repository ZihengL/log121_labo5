package ets.log121_labo5.controllers.command.commands;

import ets.log121_labo5.controllers.command.Command;
import ets.log121_labo5.controllers.command.CommandsManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoadImage implements EventHandler<ActionEvent>, Command {

    @Override
    public void handle(ActionEvent event) {
        this.execute();
    }

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

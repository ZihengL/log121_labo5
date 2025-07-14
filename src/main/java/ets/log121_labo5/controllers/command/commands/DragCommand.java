package ets.log121_labo5.controllers.command.commands;


import ets.log121_labo5.controllers.command.Command;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import javax.swing.text.html.ImageView;

/**
 * Class: DragCommand
 * Created on: 7/14/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class DragCommand extends Command<MouseEvent> {

    private final PanCommand panCommand;
    @FXML private final ImageView view;

    public DragCommand(PanCommand panCommand, ImageView view) {
        this.panCommand = panCommand;
        this.view = view;
    }

    @Override
    public void execute(MouseEvent event) {
        double deltaX = event.getX() - panCommand.getX();
        double deltaY = event.getY() - panCommand.getY();


    }

    @Override
    public void undo() {

    }
}

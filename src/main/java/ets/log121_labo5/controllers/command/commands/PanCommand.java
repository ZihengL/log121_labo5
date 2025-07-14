package ets.log121_labo5.controllers.command.commands;


import ets.log121_labo5.controllers.command.Command;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

/**
 * Class: PanCommand
 * Created on: 7/14/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class PanCommand extends Command<MouseEvent> {

    private double x;
    private double y;

    @Override
    public void execute(MouseEvent event) {
        this.x = event.getX();
        this.y = event.getY();
    }

    @Override
    public void undo() {

    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }
}

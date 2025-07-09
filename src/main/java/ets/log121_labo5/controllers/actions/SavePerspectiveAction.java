package ets.log121_labo5.controllers.actions;


import ets.log121_labo5.controllers.command.Command;
import ets.log121_labo5.models.Perspective;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;

import java.awt.event.ActionListener;

/**
 * Class: SavePerspectiveAction
 * Created on: 7/9/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class SavePerspectiveAction implements Command {

    private Image image;
    private Perspective leftside;
    private Perspective rightside;

    public SavePerspectiveAction(Image image, Perspective leftside, Perspective rightside) {
        this.image = image;
        this.leftside = leftside;
        this.rightside = rightside;
    }

    @Override
    public void execute() {

    }
}

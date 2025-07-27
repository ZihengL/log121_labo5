package ets.log121_labo5.models.command.commands.navigation;


import ets.log121_labo5.controllers.ImageNavigatorController;
import ets.log121_labo5.models.command.Command;
import ets.log121_labo5.models.command.CommandsManager;
import ets.log121_labo5.models.Perspective;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;

/**
 * Class: ZoomCommand
 * Created on: 7/14/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

// Code basé sur: https://gist.github.com/james-d/ce5ec1fd44ce6c64e81a
public class ZoomCommand extends Command<ScrollEvent> {

    @Override
    public void execute(ScrollEvent event) {
        ImageView view = (ImageView) event.getSource();
        if (view.getImage() == null) return;

        ImageNavigatorController controller = (ImageNavigatorController) view.getProperties().get("controller");

        Perspective perspective = controller.getPerspective();
        Point2D position = new Point2D(event.getX(), event.getY());
        double delta = -event.getDeltaY();

        CommandsManager.getInstance().zoom(perspective, position, delta);
    }

    @Override
    public void undo() {

    }
}

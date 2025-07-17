package ets.log121_labo5.controllers.command.commands.navigation;


import ets.log121_labo5.controllers.ImageNavigatorController;
import ets.log121_labo5.controllers.command.Command;
import ets.log121_labo5.controllers.command.CommandsManager;
import ets.log121_labo5.models.Perspective;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;

/**
 * Class: PanningZoomCommand
 * Created on: 7/16/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class PanningZoomCommand extends Command<ScrollEvent> {

    @Override
    public void execute(ScrollEvent event) {
        ImageView view = (ImageView) event.getSource();
        if (view.getImage() == null) return;

        ImageNavigatorController controller = (ImageNavigatorController) view.getProperties().get("controller");
        CommandsManager manager = CommandsManager.getInstance();
        Perspective perspective = controller.getPerspective();

        double delta = -event.getDeltaY();
        manager.zoom(perspective, delta);

        Point2D point = new Point2D(event.getX(), event.getY());
        Point2D target = view.localToParent(point);
        Bounds bounds = view.getBoundsInLocal();

        manager.pan(perspective, target, bounds);
    }

    @Override
    public void undo() {

    }

}

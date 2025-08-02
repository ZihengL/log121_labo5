package ets.log121_labo5.controllers.commands.navigation;


import ets.log121_labo5.controllers.ImageNavigatorController;
import ets.log121_labo5.controllers.commands.Command;
import ets.log121_labo5.models.CommandsManager;
import ets.log121_labo5.models.Perspective;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Class: PanCommand
 * Created on: 7/14/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class PanCommand implements Command<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        if (event.getButton() != MouseButton.PRIMARY) return;

        ImageView view = (ImageView) event.getSource();
        if (view.getImage() == null) return;

        ImageNavigatorController controller = (ImageNavigatorController) view.getProperties().get("controller");

        Perspective perspective = controller.getPerspective();
        Point2D position = new Point2D(event.getX(), event.getY());
        Bounds bounds = view.getBoundsInLocal();

        CommandsManager.getInstance().pan(perspective, position, bounds);
    }
}

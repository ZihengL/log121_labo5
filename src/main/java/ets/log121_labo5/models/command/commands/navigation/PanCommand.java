package ets.log121_labo5.models.command.commands.navigation;


import ets.log121_labo5.controllers.ImageNavigatorController;
import ets.log121_labo5.models.command.Command;
import ets.log121_labo5.models.command.CommandsManager;
import ets.log121_labo5.models.Perspective;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

/**
 * Class: PanCommand
 * Created on: 7/14/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class PanCommand extends Command<MouseEvent> {

    @Override
    public void execute(MouseEvent event) {
        if (event.getButton() != MouseButton.PRIMARY) return;

        ImageView view = (ImageView) event.getSource();
        if (view.getImage() == null) return;

        ImageNavigatorController controller = (ImageNavigatorController) view.getProperties().get("controller");

        Perspective perspective = controller.getPerspective();
        Point2D position = new Point2D(event.getX(), event.getY());
        Bounds bounds = view.getBoundsInLocal();

//        Affine inverse = null;
//        try {
//            inverse = (Affine) view.getLocalToParentTransform().createInverse();
//        } catch (NonInvertibleTransformException e) {
//            e.printStackTrace();
//            return;
//        }
//
//        Point2D imagePoint = inverse.transform(event.getX(), event.getY());
//        System.out.println(imagePoint);

        CommandsManager.getInstance().pan(perspective, position, bounds);
    }

    @Override
    public void undo() {

    }
}

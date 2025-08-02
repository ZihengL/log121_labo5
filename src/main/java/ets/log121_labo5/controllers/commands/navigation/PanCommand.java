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
 * Description: Classe commande qui s'occupe du défilement de la vue.
 *
 * @author liuzi | Zi heng Liu
 */

public class PanCommand implements Command<MouseEvent> {

    // Si le bouton provenant de la source de l'événement est le clic gauche,
    // et que la vue n'est pas nulle(lorsqu'il n'y a pas d'image),
    // on récolte le contrôleur de l'ImageView qui a été stocké en tant que propriété.
    // À partir de celle-ci, nous pouvons accéder à la Perspective
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

package ets.log121_labo5.controllers.commands.imageviewCommand.navigation;


import ets.log121_labo5.controllers.commands.imageviewCommand.ImageViewCommand;
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

public class PanCommand extends ImageViewCommand<MouseEvent> {

    // Si le bouton provenant de la source de l'événement est le clic gauche,
    // et que la vue n'est pas nulle(lorsqu'il n'y a pas d'image),
    // on récolte l'objet Perspective lié à la vue, et on invoque la commande de défilement
    // du gestionnaire de commandes en injectant la Perspective ainsi que
    // l'information par rapport à sa position, et les bornes du rectangle par
    // rapport au cadre d'image, l'ImageView.
    @Override
    public void handle(MouseEvent event) {
        if (event.getButton() != MouseButton.PRIMARY) return;

        ImageView view = this.getImageView(event);
        if (view.getImage() == null) return;

        Perspective perspective = this.getPerspective(event);
        Point2D position = new Point2D(event.getX(), event.getY());
        Bounds bounds = view.getBoundsInLocal();

        CommandsManager.getInstance().pan(perspective, position, bounds);
    }
}

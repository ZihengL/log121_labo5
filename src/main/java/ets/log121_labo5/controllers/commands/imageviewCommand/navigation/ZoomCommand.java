package ets.log121_labo5.controllers.commands.imageviewCommand.navigation;


import ets.log121_labo5.controllers.commands.imageviewCommand.ImageViewCommand;
import ets.log121_labo5.models.CommandsManager;
import ets.log121_labo5.models.Perspective;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;

/**
 * Class: ZoomCommand
 * Created on: 7/14/2025
 * Description: Classe qui invoque la commande zoom du gestionnaire de commandes.
 *
 * @author liuzi | Zi heng Liu
 */

public class ZoomCommand extends ImageViewCommand<ScrollEvent> {

    // On récolte les informations nécéssaires de la vue comme la position de la sourie
    // et le delta du rouleau de sourie(on inverse la valeur puisque rouler vers le haut
    // compte comme une valeur négative alors que nous voulons aggrandir la vue).
    // On injecte également l'instance de l'objet Perspective au gestionnaire de commandes.
    @Override
    public void handle(ScrollEvent event) {
        ImageView view = this.getImageView(event);
        if (view.getImage() == null) return;

        Perspective perspective = this.getPerspective(view);
        Point2D position = new Point2D(event.getX(), event.getY());
        double delta = -event.getDeltaY();

        CommandsManager.getInstance().zoom(perspective, position, delta);
    }
}

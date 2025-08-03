package ets.log121_labo5.controllers.commands.imageviewCommand;


import ets.log121_labo5.controllers.commands.Command;
import ets.log121_labo5.models.Perspective;
import ets.log121_labo5.models.PerspectiveGetter;
import ets.log121_labo5.models.PerspectiveSetter;
import javafx.event.Event;
import javafx.scene.image.ImageView;

/**
 * Class: ImageViewCommand
 * Created on: 8/2/2025
 * Description: Superclasse abstraite des types d'actions qui se font directement sur
 * le cadre de photo. Les méthodes par défaut qui s'y trouvent permet de facilement
 * retrouver l'objet Perspective lié au cadre grâce aux interfaces fonctionelles que
 * nous avons stockés dans le cadre, soit l'ImageView.
 *
 * @author liuzi | Zi heng Liu
 */

public abstract class ImageViewCommand<T extends Event> implements Command<T> {

    // Retourne l'objet Perspective lié à l'ImageView, qui se trouve
    // à être la source des événements.
    public Perspective getPerspective(T event) {
        ImageView view = this.getImageView(event);
        PerspectiveGetter getter = (PerspectiveGetter) view.getProperties().get("PerspectiveGetter");

        return getter.getPerspective();
    }

    // Même principe que la méthode précédente, mais on invoque le mutateur
    // stocké sous forme de propriété.
    public void setPerspective(T event, Perspective perspective) {
        ImageView view = this.getImageView(event);
        PerspectiveSetter setter = (PerspectiveSetter) view.getProperties().get("PerspectiveSetter");

        setter.setPerspective(perspective);
    }

    // Retourne la source de l'événement sous sa forme explicite.
    public ImageView getImageView(T event) {
        return (ImageView) event.getSource();
    }
}

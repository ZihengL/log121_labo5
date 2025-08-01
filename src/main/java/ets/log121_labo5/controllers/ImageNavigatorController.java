package ets.log121_labo5.controllers;

import ets.log121_labo5.models.PerspectiveGetter;
import ets.log121_labo5.models.PerspectiveSetter;
import ets.log121_labo5.controllers.command.CommandsManager;
import ets.log121_labo5.controllers.command.commands.navigation.PanCommand;
import ets.log121_labo5.controllers.command.commands.navigation.ZoomCommand;
import ets.log121_labo5.models.Perspective;
import ets.log121_labo5.models.observer.Observable;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;

/**
 * Class: ImageNavigatorController
 * Created on: 7/12/2025
 * Description: Contrôleur pour les vues qui incluent une
 * Perspective dans le modèle.
 *
 * @author liuzi | Zi heng Liu
 */

public class ImageNavigatorController extends ImageController {

    private PerspectiveGetter perspectiveGetter;
    private PerspectiveSetter perspectiveSetter;

    @FXML
    protected void initialize() {
        super.initialize();

        // Sauvegarde l'instance de la classe en tant que propriété
        // afin d'y avoir accès dans la commande lorsqu'on source un événement.
        this.rootPane.getProperties().put("controller", this);
        this.view.getProperties().put("controller", this);

        // ZOOM & PAN
        this.view.setOnScroll(new ZoomCommand());
        this.view.setOnMouseClicked(new PanCommand());
    }

    // On passe par la voie d'interfaces fonctionnels afin de donner accès à l'objet
    // Perspective dédié à l'instance du controleur.
    public void setPerspectiveAccessors(PerspectiveGetter getter, PerspectiveSetter setter) {
        this.perspectiveGetter = getter;
        this.perspectiveSetter = setter;
    }

    public Perspective getPerspective() {
        return this.perspectiveGetter.getPerspective();
    }

    public void setPerspective(Perspective perspective) {
        this.perspectiveSetter.setPerspective(perspective);
    }

    // UPDATE

    @Override
    public void update(Observable observable) {
        super.update(observable);

        Perspective perspective = this.getPerspective();
        this.updateViewport(perspective);
    }

    public boolean updateImage(CommandsManager manager) {
        if (!super.updateImage(manager)) return false;

        Rectangle2D bounds = this.getPerspective().getBounds();
        double width = bounds.getWidth(), height = bounds.getHeight();

        this.view.setFitWidth(bounds.getWidth());
        this.view.setFitHeight(bounds.getHeight());

        return true;
    }

    public void updateViewport(Perspective perspective) {
        Rectangle2D viewport = perspective.getViewport();

        this.view.setViewport(viewport);
    }
}

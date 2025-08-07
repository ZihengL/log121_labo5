package ets.log121_labo5.controllers;

import ets.log121_labo5.Application;
import ets.log121_labo5.models.PerspectiveGetter;
import ets.log121_labo5.models.PerspectiveSetter;
import ets.log121_labo5.models.CommandsManager;
import ets.log121_labo5.controllers.commands.imageviewCommand.navigation.PanCommand;
import ets.log121_labo5.controllers.commands.imageviewCommand.navigation.ZoomCommand;
import ets.log121_labo5.models.Perspective;
import ets.log121_labo5.models.observer.Observable;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Class: ImageNavigatorController
 * Created on: 7/12/2025
 * Description: Contrôleur pour les vues qui incluent une
 * Perspective dans le modèle.
 *
 * @author liuzi | Zi heng Liu
 */

public class ImageNavigatorController extends ImageViewController {

    private PerspectiveGetter perspectiveGetter;
    private PerspectiveSetter perspectiveSetter;

    @FXML
    protected void initialize() {
        super.initialize();

        // ZOOM & PAN
        this.view.setOnScroll(new ZoomCommand());
        this.view.setOnMouseClicked(new PanCommand());

        // Invocation de la méthode statique du contrôleur de
        // menu de contexte pour ajouter la fonction cacher/révéler
        // le menu de contexte de son instance unique
        // à l'instance présente d'ImageView.
        ContextMenuController.addFunctionToView(this.view);
    }

    // On passe par la voie d'interfaces fonctionnelles afin de donner accès à l'objet
    // Perspective dédiée à l'instance du contrôleur.
    public void setPerspectiveAccessors(PerspectiveGetter getter, PerspectiveSetter setter) {
        this.perspectiveGetter = getter;
        this.perspectiveSetter = setter;

        // Ajout des interfaces fonctionnelles en tant que propriétés dans le panneau
        // afin que les commandes de type ImageViewCommand/ContextMenuCommand puissent avoir accès.
        // Cela ajoute ainsi une couche additionnelle de découplage.
        this.view.getProperties().put("PerspectiveGetter", this.perspectiveGetter);
        this.view.getProperties().put("PerspectiveSetter", this.perspectiveSetter);
    }

    // Getter utlisant l'interface fonctionelle.
    public Perspective getPerspective() {
        return this.perspectiveGetter.getPerspective();
    }

    // Setter utlisant l'interface fonctionelle.
    public void setPerspective(Perspective perspective) {
        this.perspectiveSetter.setPerspective(perspective);
    }

    // Invocation de la mise à jour de la superclasse(qui invoque updateImage()),
    // et par après, la mise à jour de la vue.
    @Override
    public void update(Observable observable) {
        super.update(observable);

        Perspective perspective = this.getPerspective();
        this.updateViewport(perspective);
    }

    // Si la méthode de la superclasse retourne vrai, on adapte la taille
    // de la vue selon les bornes de l'image ainsi qu'on change la taille
    // de l'écran pour inclure la totalité des éléments.
    public boolean updateImage(Image image) {
        if (!super.updateImage(image)) return false;

        Rectangle2D bounds = this.getPerspective().getBounds();
        this.view.setFitWidth(bounds.getWidth());
        this.view.setFitHeight(bounds.getHeight());

        Stage stage = Application.getStage();
        stage.sizeToScene();
        stage.centerOnScreen();

        return true;
    }

    // Mise à jour de la vue
    public void updateViewport(Perspective perspective) {
        Rectangle2D viewport = perspective.getViewport();

        this.view.setViewport(viewport);
    }
}

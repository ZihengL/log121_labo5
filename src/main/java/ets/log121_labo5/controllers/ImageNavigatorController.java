package ets.log121_labo5.controllers;

import ets.log121_labo5.models.PerspectiveGetter;
import ets.log121_labo5.models.PerspectiveSetter;
import ets.log121_labo5.controllers.command.CommandsManager;
import ets.log121_labo5.controllers.command.commands.navigation.PanCommand;
import ets.log121_labo5.controllers.command.commands.navigation.ZoomCommand;
import ets.log121_labo5.models.Perspective;
import ets.log121_labo5.models.observer.Observable;
import ets.log121_labo5.models.observer.Observer;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class ImageNavigatorController extends ImageController {

//    @FXML private StackPane rootPane;
//    @FXML private ImageView view;

    private PerspectiveGetter perspectiveGetter;
    private PerspectiveSetter perspectiveSetter;

    @FXML
    protected void initialize() {
//        CommandsManager manager = CommandsManager.getInstance();
//        manager.addObserver(this);
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
//        CommandsManager manager = (CommandsManager) observable;
//
//        Image image = manager.getImage();
//        Perspective perspective = this.getPerspective();
//
//        this.updateImage(image, perspective.getBounds());

        super.update(observable);

        Perspective perspective = this.getPerspective();
        this.updateViewport(perspective);
    }

    public void updateImage(Image image, Rectangle2D bounds) {
        Image current = this.view.getImage();
        if (image == null || image.equals(current)) return;

        this.view.setImage(image);
        this.view.setFitWidth(bounds.getWidth());
        this.view.setFitHeight(bounds.getHeight());
    }

    public boolean updateImage(CommandsManager manager) {
//        Image current = this.view.getImage();
//        if (image == null || image.equals(current)) return;
//
//        this.view.setImage(image);
        if (!super.updateImage(manager)) return false;

        Rectangle2D bounds = this.getPerspective().getBounds();
        this.view.setFitWidth(bounds.getWidth());
        this.view.setFitHeight(bounds.getHeight());

        return true;
    }

    public void updateViewport(Perspective perspective) {
        Rectangle2D viewport = perspective.getViewport();

        this.view.setViewport(viewport);
    }
}

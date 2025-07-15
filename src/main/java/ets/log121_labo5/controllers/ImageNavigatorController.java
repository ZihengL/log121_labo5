package ets.log121_labo5.controllers;

import ets.log121_labo5.controllers.command.CommandsManager;
import ets.log121_labo5.controllers.command.commands.navigation.ZoomCommand;
import ets.log121_labo5.models.Perspective;
import ets.log121_labo5.models.Vector;
import ets.log121_labo5.models.observer.Observable;
import ets.log121_labo5.models.observer.Observer;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class ImageNavigatorController implements Observer {

    @FXML private BorderPane rootPane;
    @FXML private ImageView imageContainer;

    private PerspectiveGetter perspectiveGetter;
    private PerspectiveSetter perspectiveSetter;

    @FXML
    protected void initialize() {
        CommandsManager manager = CommandsManager.getInstance();
        manager.addObserver(this);

        // Sauvegarde l'instance de la classe en tant que propriété
        // afin d'y avoir accès dans CopyCommand lorsqu'on source un événement.
        this.rootPane.getProperties().put("controller", this);

        // Liaison de données des dimensions de l'ImageView et son conteneur parent.
        this.imageContainer.fitWidthProperty().bind(this.rootPane.widthProperty().subtract(20));
        this.imageContainer.fitHeightProperty().bind(this.rootPane.heightProperty().subtract(20));

        // ZOOM
        this.imageContainer.setOnScroll(new ZoomCommand());

        // NAVIGATION
    }

    // SET/GET PERSPECTIVE LAMBDA GIVEN BY MAINCONTROLLER
    public void setPerspectiveLambdas(PerspectiveGetter getter, PerspectiveSetter setter) {
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
        CommandsManager manager = (CommandsManager) observable;

        Image image = manager.getImage();
        this.updateImage(image);

        Perspective perspective = this.getPerspective();
        this.updateZoom(perspective);
        this.updatePosition(perspective);
    }

    public void updateImage(Image image) {
        Image current = this.imageContainer.getImage();

        if (image != null && !image.equals(current)) {
            this.imageContainer.setImage(image);
            this.imageContainer.setViewport(new Rectangle2D(0, 0, image.getWidth(), image.getHeight()));
        }
    }

    public void updateZoom(Perspective perspective) {
        double zoom = perspective.getZoom();

        // TODO UPDATE ZOOM
    }

    public void updatePosition(Perspective perspective) {
        Vector position = perspective.getPosition();

        // TODO: UPDATE POSITION
    }
}

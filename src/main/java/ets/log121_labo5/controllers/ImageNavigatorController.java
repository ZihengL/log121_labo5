package ets.log121_labo5.controllers;

import ets.log121_labo5.controllers.command.CommandsManager;
import ets.log121_labo5.models.Perspective;
import ets.log121_labo5.models.Vector;
import ets.log121_labo5.models.observer.Observable;
import ets.log121_labo5.models.observer.Observer;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;

public class ImageNavigatorController extends ImageViewerController {

    @FXML private AnchorPane rootPane;
    @FXML private ImageView imageContainer;

    private ContextMenuController contextMenuController;

    private PerspectiveGetter perspectiveGetter;

    private Perspective perspective;

    @FXML
    protected void initialize() {
        super.initialize();

        this.contextMenuController = new ContextMenuController();
        this.rootPane.addEventHandler(ContextMenuEvent.CONTEXT_MENU_REQUESTED,
                );
    }

    @Override
    public void update(Observable observable) {
        super.update(observable);

        this.updateZoom();
        this.updatePosition();
    }

    // TODO
    public void updateZoom() {
        double zoom = this.perspective.getZoom();
    }

    // TODO
    public void updatePosition() {
        Vector position = this.perspective.getPosition();
    }

    public Perspective getPerspective() {
        return this.perspective;
    }
}

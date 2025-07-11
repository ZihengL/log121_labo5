package ets.log121_labo5.controllers;

import ets.log121_labo5.controllers.command.CommandsManager;
import ets.log121_labo5.controllers.command.commands.LoadImageCommand;
import ets.log121_labo5.models.Perspective;
import ets.log121_labo5.models.observer.Observable;
import ets.log121_labo5.models.observer.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;


public class MainController implements Observer {

    @FXML private MenuItem itemSavePerspective;
    @FXML private MenuItem itemLoadImage;

    @FXML private ImageView thumbnail;
    @FXML private ImageView leftside;
    @FXML private ImageView rightside;

    // OBSERVER IMPLEMENTATION
    @Override
    public void update(Observable observable) {
        CommandsManager manager = (CommandsManager) observable;
        Image image = manager.getImage();
        Perspective leftside = manager.getLeftside(),
                    rightside = manager.getRightside();

        if (!this.updateImage(manager.getImage()))
            return;
    }

    // TODO: NEED TO SET THUMBNAIL IMAGEVIEW SO THAT ITS MAX WIDTH TO THE WIDTH OF THE PARENT
    public boolean updateImage(Image image) {
        Image current = this.thumbnail.getImage();

        if (current == null || !current.equals(image)) {
            this.thumbnail.setImage(image);

            return true;
        }

        return false;
    }

    // UI

    @FXML
    private void initialize() {
        CommandsManager instance = CommandsManager.getInstance();
        instance.addObserver(this);

        // MENUBAR
        this.itemLoadImage.setOnAction(new LoadImageCommand());
    }

    @FXML
    private void onOpenSettings(MouseEvent mouseEvent) {

    }

    @FXML
    private void uploadImage(ActionEvent actionEvent) {
        FileChooser chooser = new FileChooser();

    }

    public void onPressRect(MouseEvent mouseEvent) {
        double x = mouseEvent.getX(), y = mouseEvent.getY();

        System.out.printf("Pressed: [ %.2f, %.2f ]\n", x, y);
    }
}
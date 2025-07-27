package ets.log121_labo5.models.command;

import ets.log121_labo5.models.Perspective;
import ets.log121_labo5.models.observer.Observable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;

/**
 * Class: CommandsManager
 * Created on: 7/6/2025
 * Description: Classe à instance unique qui se trouve au centre de tous les commandes
 * de l'utilisateur
 *
 * @author liuzi | Zi heng Liu
 */

public class CommandsManager extends Observable implements Serializable {

    /* --------- STATIC (SINGLETON) --------- */

    @Serial
    private static final long serialVersionUID = 1L;

    private static final CommandsManager instance = new CommandsManager();

    public static CommandsManager getInstance() {
        return CommandsManager.instance;
    }

    /* --------- INSTANCE --------- */

    // TODO: CONSIDER CHANGING IMAGE TO A CUSTOM IMAGEVIEW THAT'LL BE THE ORIGINAL IMAGE?
    private transient Image image;  // Image n'implémente pas Serializable
    private Perspective leftside;
    private Perspective rightside;

    private CommandsManager() {
        this.image = null;
        this.leftside = new Perspective();
        this.rightside = new Perspective();

        this.notifyObservers();
    }

    // ACCESSORS

    public Image getImage() {
        return this.image;
    }

    public Perspective getLeftside() {
        return this.leftside;
    }

    public Perspective getRightside() {
        return this.rightside;
    }

    // MUTATORS

    public void setImage(Image image) {
        this.image = image;

        double width = this.image.getWidth(), height = this.image.getHeight();
        this.leftside.setDimensions(width, height);
        this.rightside.setDimensions(width, height);

        this.notifyObservers();
    }

    public void setLeftside(Perspective leftside) {
        this.leftside = leftside.copy();

        this.notifyObservers();
    }

    public void setRightside(Perspective rightside) {
        this.rightside = rightside.copy();

        this.notifyObservers();
    }

    public void setAll(Image image, Perspective leftside, Perspective rightside) {
        this.image = image;
        this.leftside = leftside;
        this.rightside = rightside;

        this.notifyObservers();
    }

    // MENUBAR: FILE MENU

    public void saveState(File file) throws IOException {
        String path = file.getPath().toLowerCase();
        file = !path.endsWith(".ser") ? new File(path + ".ser") : file;

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(file))) {
            oos.writeObject(this);
        }
    }

    public void loadState(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file))) {
            Object object = ois.readObject();

            if (object instanceof CommandsManager loaded) {
                this.leftside = loaded.leftside;
                this.rightside = loaded.rightside;

                this.notifyObservers();
            }
        }
    }

    public void loadImage(File file) {
        this.setImage(new Image(file.toURI().toString()));
    }

    // TODO: SAVE BEFORE QUITTING?
    public void quitApplication(Stage stage) {
        stage.close();
    }

    // MENUBAR: EDITION

    public void undo() {

    }

    public void redo() {

    }

    // MENUBAR: "PRESSE-PAPIER"

    public void chooseStratagem() {

    }

    // ZOOM
    public void zoom(Perspective perspective, Point2D position, double delta) {
        perspective.zoom(position, delta);

        this.notifyObservers();
    }

    public void pan(Perspective perspective, Point2D position, Bounds bounds) {
        perspective.pan(position, bounds);

        this.notifyObservers();
    }

    // OTHER

    public String toString() {
        return String.format("Leftside: %s - Rightside: %s", this.leftside, this.rightside);
    }
}

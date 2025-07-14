package ets.log121_labo5.controllers.command;

import ets.log121_labo5.models.Perspective;
import ets.log121_labo5.models.State;
import ets.log121_labo5.models.Vector;
import ets.log121_labo5.models.observer.Observable;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Class: CommandsManager
 * Created on: 7/6/2025
 * Description:
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

    private transient Image image;  // On opte de ne pas convertir l'Image, donc transigeant
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

    public State getState() {
        return new State(this.image, this.leftside, this.rightside);
    }

    // MUTATORS

    public void setImage(Image image) {
        this.image = image;

        this.notifyObservers();
    }

    public void setImage(WritableImage writableImage) {
        this.image = writableImage;

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

    /* -- MENUBAR: FILE MENU -- */

    public void saveState(File file) throws IOException {
        String path = file.getPath().toLowerCase();
        file = !path.toLowerCase().endsWith(".ser") ? new File(path + ".ser") : file;

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

    public void quitApplication(Stage stage) {
        stage.close();  // TODO: SAVE BEFORE QUIT??
    }

    /* -- MENUBAR: EDITION -- */

    public void undo() {

    }

    public void redo() {

    }

    /* -- MENUBAR: "PRESSE-PAPIER" -- */

    public void chooseStratagem() {

    }

    /* -- IMAGE NAVIGATION -- */

    public void translateOnLeftside(Vector translation) {

    }

    public void translateOnRightside(Vector translation) {

    }

    public void zoomOnLeftside(double zoom) {

    }

    public void zoomOnRightside(double zoom) {

    }

    public String toString() {
        return String.format("Leftside: %s - Rightside: %s", this.leftside, this.rightside);
    }
}

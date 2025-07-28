package ets.log121_labo5.models.command;

import ets.log121_labo5.models.Perspective;
import ets.log121_labo5.models.memento.HistoryManager;
import ets.log121_labo5.models.memento.State;
import ets.log121_labo5.models.observer.Observable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;

/**
 * Class: CommandsManager
 * Created on: 7/6/2025
 * Description: Classe à instance unique qui centralise la gestion de tous les commandes
 * de l'utilisateur. Il est à noter que ce n'est pas tous les actions qui sont considérées
 * comme une commande. L'action de copier un item ou d'ouvrir un menu, par exemple, ne se
 * qualifient pas comme étant
 *
 * @author liuzi | Zi heng Liu
 */

// TODO: REMOVE SERIALIZABLE
public class CommandsManager extends Observable implements Serializable {

    /* --------- STATIC (SINGLETON) --------- */

    @Serial
    private static final long serialVersionUID = 1L;

    private static final CommandsManager instance = new CommandsManager();

    public static CommandsManager getInstance() {
        return CommandsManager.instance;
    }

    /* --------- INSTANCE --------- */

    private transient Image image;  // Image n'implémente pas Serializable
    private Perspective leftside;
    private Perspective rightside;

    private transient HistoryManager historyManager; // On ne sauvegarde pas l'historique

    private CommandsManager() {
        this.image = null;
        this.leftside = new Perspective();
        this.rightside = new Perspective();
        this.historyManager = new HistoryManager();

        this.notifyObservers();
    }

    // SERIALIZATION

    @Serial
    private void writeObject(ObjectOutputStream output) throws IOException {
        output.defaultWriteObject();

        boolean validImage = this.image != null && this.image.getUrl() != null;
        output.writeBoolean(validImage);

        if (validImage)
            output.writeUTF(this.image.getUrl());
    }

    @Serial
    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
        input.defaultReadObject();

        boolean validImage = input.readBoolean();
        this.image = validImage ? new Image(input.readUTF()) : this.image;
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
        String imageURL = this.image != null ? this.image.getUrl() : "";

        return new State(imageURL, this.leftside, this.rightside);
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

    public void setState(State state) {
        if (state == null) return;

        this.image = new Image(state.imageURL());
        this.leftside = state.leftside();
        this.rightside = state.rightside();

        this.notifyObservers();
    }

    // MENUBAR: FILE MENU

    public void saveStateToFiles(File file) throws IOException {
        String path = file.getPath().toLowerCase();
        file = !path.endsWith(".ser") ? new File(path + ".ser") : file;

        try (ObjectOutputStream output = new ObjectOutputStream(
                new FileOutputStream(file))) {
            output.writeObject(this);
        }
    }

    public void loadStateFromFiles(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(
                new FileInputStream(file))) {
            Object object = input.readObject();

            if (object instanceof State(String imageURL, Perspective leftside1, Perspective rightside1)) {
                this.image = new Image(imageURL);
                this.leftside = leftside1;
                this.rightside = rightside1;

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

    // MENUBAR: EDITING

    public void undo() {
        State state = this.historyManager.getPrevious();

        this.setState(state);
    }

    public void redo() {
        State state = this.historyManager.getNext();

        this.setState(state);
    }

    public void recordCurrentState() {
        State state = this.getState();

        this.historyManager.add(state);
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

package ets.log121_labo5.models.command;

import ets.log121_labo5.models.Perspective;
import ets.log121_labo5.models.memento.State;
import ets.log121_labo5.models.memento.StatesManager;
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

    private transient StatesManager statesManager; // On ne sauvegarde pas l'historique

    private CommandsManager() {
        this.image = null;
        this.leftside = new Perspective();
        this.rightside = new Perspective();
        this.statesManager = new StatesManager();

        this.recordCurrentState();
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

    // Retourne un record de l'état actuel des composantes du programme.
    // Utilise copie des objets Perspective afin de maintenir leur état courant.
    public State getState() {
        String imageURL = this.image != null ? this.image.getUrl() : "";

        return new State(imageURL, this.leftside.copy(), this.rightside.copy());
    }

    // MUTATORS

    public void setImage(Image image) {
        this.image = image;
        double width = this.image.getWidth(), height = this.image.getHeight();
        this.leftside.setDimensions(width, height);
        this.rightside.setDimensions(width, height);

        this.update();
    }

    public void setLeftside(Perspective leftside) {
        this.recordCurrentState();
        this.leftside = leftside.copy();

        this.update();
    }

    public void setRightside(Perspective rightside) {
        this.recordCurrentState();
        this.rightside = rightside.copy();

        this.update();
    }

    public void setState(State state) {
        if (state == null) return;

        this.image = !state.imageURL().isEmpty() ? new Image(state.imageURL()) : null;
        this.leftside = state.leftside();
        this.rightside = state.rightside();

        this.notifyObservers();
    }

    // MENUBAR: FILE MENU

    public void saveStateToFile(File file) throws IOException {
        String path = file.getPath().toLowerCase();
        file = !path.endsWith(".ser") ? new File(path + ".ser") : file;

        try (ObjectOutputStream output = new ObjectOutputStream(
                new FileOutputStream(file))) {
            output.writeObject(this);
        }
    }

    public void loadStateFromFile(File file) throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(
                new FileInputStream(file))) {
            Object object = input.readObject();

            if (object instanceof CommandsManager loaded) {
                this.image = loaded.image;
                this.leftside = loaded.leftside;
                this.rightside = loaded.rightside;

                this.update();
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
        if (!this.statesManager.moveLeft()) return;

        State previous = this.statesManager.getCurrent().getState();
        this.setState(previous);
    }

    public void redo() {
        if (!this.statesManager.moveRight()) return;

        State next = this.statesManager.getCurrent().getState();
        this.setState(next);
    }

    public void recordCurrentState() {
        State state = this.getState();

        this.statesManager.add(state);
    }

    // ZOOM
    public void zoom(Perspective perspective, Point2D position, double delta) {
        perspective.zoom(position, delta);

        this.update();
    }

    public void pan(Perspective perspective, Point2D position, Bounds bounds) {
        perspective.pan(position, bounds);

        this.update();
    }

    // OTHER

    public void update() {
        this.recordCurrentState();
        this.notifyObservers();
    }

    public String toString() {
        return String.format("Leftside: %s - Rightside: %s", this.leftside, this.rightside);
    }
}

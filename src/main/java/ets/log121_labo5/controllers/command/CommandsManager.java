package ets.log121_labo5.controllers.command;

import ets.log121_labo5.models.Perspective;
import ets.log121_labo5.models.State;
import ets.log121_labo5.models.Vector;
import ets.log121_labo5.models.observer.Observable;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

/**
 * Class: CommandsManager
 * Created on: 7/6/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class CommandsManager extends Observable {

    /* --------- STATIC (SINGLETON) --------- */

    private static final CommandsManager instance = new CommandsManager();

    public static CommandsManager getInstance() {
        return CommandsManager.instance;
    }

    /* --------- INSTANCE --------- */

    private Image image;
    private Perspective leftside;
    private Perspective rightside;

    private Perspective currentCopy;

    private CommandsManager() {
        this.image = null;
        this.leftside = new Perspective();
        this.rightside = new Perspective();
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

    public void setLeftside(Perspective leftside) {
        this.leftside = leftside;

        this.notifyObservers();
    }

    public void setRightside(Perspective rightside) {
        this.rightside = rightside;

        this.notifyObservers();
    }

    public void setState(State state) {
        this.image = state.image();
        this.leftside = state.leftside();
        this.rightside = state.rightside();

        this.notifyObservers();
    }

    /* -- MENUBAR: FILE MENU -- */

    public void saveState(File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(file))) {
            State state = this.getState();

            oos.writeObject(state);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadState(File file) {
        try (ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(file))) {
            State state = (State) inputStream.readObject();

            this.setState(state);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadImage(File file) {
        Image image = new Image(file.toURI().toString());
        this.setState(new State(image, new Perspective(), new Perspective()));
    }

    // TODO: maybe not necessary, unless we save state and reload upon app start or smt.
    public void quitApplication(Stage stage) {
        stage.close();
    }

    /* -- MENUBAR: EDITION -- */

    public void undo() {

    }

    public void redo() {

    }

    /* -- MENUBAR: "PRESSE-PAPIER" -- */

    public void chooseStratagem() {

    }

    /* -- CONTEXT MENU -- */

    public void copyPerspective() {

    }

    /* -- IMAGE NAVIGATION -- */

    public void translate(Perspective perspective, Vector translation) {

    }
}

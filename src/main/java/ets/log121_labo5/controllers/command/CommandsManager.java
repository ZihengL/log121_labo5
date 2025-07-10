package ets.log121_labo5.controllers.command;

import ets.log121_labo5.models.Perspective;
import ets.log121_labo5.models.SaveState;
import ets.log121_labo5.models.Vector;
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

public class CommandsManager {

    private Image image;
    private Perspective leftside;
    private Perspective rightside;

    /* --------- STATIC --------- */

    // Implémentation du patron Singleton
    private static final CommandsManager instance = new CommandsManager();

    public static CommandsManager getInstance() {
        return CommandsManager.instance;
    }

    /* --------- INSTANCE --------- */

    private CommandsManager() {}

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

    public SaveState getBundledStates() {
        return new SaveState(this.image, this.leftside, this.rightside);
    }

    // MUTATORS

    public void setImage(Image image) {
        this.image = image;
    }

    public void setLeftside(Perspective leftside) {
        this.leftside = leftside;
    }

    public void setRightside(Perspective rightside) {
        this.rightside = rightside;
    }

    /* -- MENUBAR: FILE MENU -- */

    // SAVE APP STATE
    public void saveState(String filename) {
        SaveState bundledStates = this.getBundledStates();

        try (ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            outputStream.writeObject(bundledStates);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // LOAD APP STATE
    public void loadState(File file) {
        try (ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(file))) {
            SaveState saveState = (SaveState) inputStream.readObject();

            this.setImage(saveState.image());
            this.setLeftside(saveState.leftside());
            this.setRightside(saveState.rightside());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // LOAD IMAGE
    public Image loadImage(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("");

        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
        }

        return null;
    }

    // QUIT
    public void quitApplication() {

    }

    /* -- MENUBAR: EDITION -- */

    // UNDO/REDO
    public void undo() {

    }

    public void redo() {

    }

    /* -- MENUBAR: "PRESSE-PAPIER" -- */

    // CHOOSE STRATAGEM
    public void chooseStratagem() {

    }

    /* -- IMAGE NAVIGATION -- */

    public void translate(Perspective perspective, Vector translation) {

    }
}

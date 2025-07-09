package ets.log121_labo5.controllers.command;

import ets.log121_labo5.models.Perspective;
import javafx.scene.image.Image;

public class CommandsManager {

    // STATIC

    private static final CommandsManager instance = new CommandsManager();

    public static CommandsManager getManager() {
        return CommandsManager.instance;
    }

    // INSTANCE

    private CommandsManager() {

    }

    /* ------- FILE MENU ------- */

    // SAVE/LOAD

    public void savePerspective() {

    }

    public Perspective loadPerspective() {
        return null;
    }

    // LOAD IMAGE

    public Image loadImage() {
        return null;
    }

    // QUIT

    public void quitApplication() {

    }

    /* ------- EDITION MENU ------- */

    // UNDO/REDO

    public void undo() {

    }

    public void redo() {

    }

    /* ------- PRESSE-PAPIER MENU ------- */

    // CHOOSE STRATAGEM
}

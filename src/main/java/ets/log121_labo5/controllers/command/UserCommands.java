package ets.log121_labo5.controllers.command;

import java.util.ArrayList;

public class UserCommands {

    /* --------- STATIC --------- */

    private static final UserCommands instance = new UserCommands();

    public static UserCommands getInstance() {
        return UserCommands.instance;
    }

    /* --------- INSTANCE --------- */

    private final ArrayList<Command> commands;
    private int index;

    private UserCommands() {
        this.commands = new ArrayList<Command>();
        this.index = 0;
    }

    // ACCESSORS

    public ArrayList<Command> getCommands() {
        return this.commands;
    }

    public Command getLastCommand() {
        return this.index > 0 ? this.commands.get(this.index - 1) : null;
    }

    // MUTATORS

    public void addCommand(Command command) {
        this.commands.add(command);
        this.index++;
    }

    // OTHER

    public void undoCommand() {
        if (this.index > 0) {

        }
    }
}

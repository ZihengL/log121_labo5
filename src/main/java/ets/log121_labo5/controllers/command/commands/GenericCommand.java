package ets.log121_labo5.controllers.command.commands;

import ets.log121_labo5.controllers.command.Command;
import javafx.event.ActionEvent;

public abstract class GenericCommand implements Command {

    @Override
    public final void handle(ActionEvent event) {
        this.execute();
    }
}

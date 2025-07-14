module ets.log121_labo5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.swing;

    opens ets.log121_labo5 to javafx.fxml;
    exports ets.log121_labo5;

    // CONTROLLERS
    opens ets.log121_labo5.controllers to javafx.fxml;
    exports ets.log121_labo5.controllers;

    opens ets.log121_labo5.controllers.command to javafx.fxml;
    exports ets.log121_labo5.controllers.command;

    opens ets.log121_labo5.controllers.command.commands to javafx.fxml;
    exports ets.log121_labo5.controllers.command.commands;

//    opens ets.log121_labo5.controllers.command.navigation to javafx.fxml;
//    exports ets.log121_labo5.controllers.command.navigation;

    // MODELS
    opens ets.log121_labo5.models to javafx.fxml;
    exports ets.log121_labo5.models;

//    opens ets.log121_labo5.models.memento to javafx.fxml;
//    exports ets.log121_labo5.models.memento;

    opens ets.log121_labo5.models.observer to javafx.fxml;
    exports ets.log121_labo5.models.observer;
    exports ets.log121_labo5.controllers.command.actioncommands;
    opens ets.log121_labo5.controllers.command.actioncommands to javafx.fxml;

}
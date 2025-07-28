module ets.log121_labo5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    opens ets.log121_labo5 to javafx.fxml;
    exports ets.log121_labo5;

    // CONTROLLERS
    opens ets.log121_labo5.controllers to javafx.fxml;
    exports ets.log121_labo5.controllers;

    opens ets.log121_labo5.models.command to javafx.fxml;
    exports ets.log121_labo5.models.command;

    opens ets.log121_labo5.models.command.commands.menubar to javafx.fxml;
    exports ets.log121_labo5.models.command.commands.menubar;

    opens ets.log121_labo5.models.command.commands.contextmenu to javafx.fxml;
    exports ets.log121_labo5.models.command.commands.contextmenu;

    opens ets.log121_labo5.models.command.commands.navigation to javafx.fxml;
    exports ets.log121_labo5.models.command.commands.navigation;

//    opens ets.log121_labo5.models.command.navigation to javafx.fxml;
//    exports ets.log121_labo5.models.command.navigation;

    // MODELS
    opens ets.log121_labo5.models to javafx.fxml;
    exports ets.log121_labo5.models;

//    opens ets.log121_labo5.models.memento to javafx.fxml;
//    exports ets.log121_labo5.models.memento;

    opens ets.log121_labo5.models.observer to javafx.fxml;
    exports ets.log121_labo5.models.observer;
    exports ets.log121_labo5.models.memento;
    opens ets.log121_labo5.models.memento to javafx.fxml;
}
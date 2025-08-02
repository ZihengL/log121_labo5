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

    opens ets.log121_labo5.controllers.commands.menubar.edit to javafx.fxml;
    exports ets.log121_labo5.controllers.commands.menubar.edit;

    opens ets.log121_labo5.controllers.commands.menubar.files to javafx.fxml;
    exports ets.log121_labo5.controllers.commands.menubar.files;

    opens ets.log121_labo5.controllers.commands.contextmenu to javafx.fxml;
    exports ets.log121_labo5.controllers.commands.contextmenu;

    opens ets.log121_labo5.controllers.commands.navigation to javafx.fxml;
    exports ets.log121_labo5.controllers.commands.navigation;

    // MODELS
    opens ets.log121_labo5.models to javafx.fxml;
    exports ets.log121_labo5.models;

    opens ets.log121_labo5.models.observer to javafx.fxml;
    exports ets.log121_labo5.models.observer;

    exports ets.log121_labo5.models.memento;
    opens ets.log121_labo5.models.memento to javafx.fxml;
    exports ets.log121_labo5.controllers.commands;
    opens ets.log121_labo5.controllers.commands to javafx.fxml;
}
module ets.log121_labo5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens ets.log121_labo5 to javafx.fxml;
    exports ets.log121_labo5;
//    opens ets.log121_labo5.models to javafx.fxml;
    exports ets.log121_labo5.models.observer;
    opens ets.log121_labo5.models.observer to javafx.fxml;
    exports ets.log121_labo5.controllers;
    opens ets.log121_labo5.controllers to javafx.fxml;
    exports ets.log121_labo5.controllers.command;
    opens ets.log121_labo5.controllers.command to javafx.fxml;

}
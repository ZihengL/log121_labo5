package ets.log121_labo5.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

/**
 * Class: CopyStrategyMenuController
 * Created on: 7/30/2025
 * Description: Classe contrôleur de la fenêtre de stratégie de copie.
 *
 * @author liuzi | Zi heng Liu
 */

public class CopyStrategyMenuController {

    @FXML private RadioButton radioTogglePosition;
    @FXML private RadioButton radioToggleZoom;

    @FXML private Button btnConfirm;
    @FXML private RadioButton btnCancel;

    public CopyStrategyMenuController() {
        this.radioTogglePosition = new RadioButton("Position");
//        this.radioTogglePosition.setOnAction(new );
    }


}

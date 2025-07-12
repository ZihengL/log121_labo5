package ets.log121_labo5.controllers;


import ets.log121_labo5.controllers.command.Command;
import ets.log121_labo5.controllers.command.commands.CopyCommand;
import ets.log121_labo5.controllers.command.commands.PasteCommand;
import ets.log121_labo5.models.Perspective;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;


/**
 * Class: ContextMenuController
 * Created on: 7/12/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class ContextMenuController {

    @FXML private TableView<MenuItem> commandsTable;

    @FXML
    private void initialize() {
        MenuItem copyItem = new MenuItem("Copier");
        copyItem.setOnAction(new CopyCommand());

        MenuItem pasteItem = new MenuItem("Coller");
        pasteItem.setOnAction(new PasteCommand());

        this.commandsTable.getItems().addAll(copyItem, pasteItem);
    }
}

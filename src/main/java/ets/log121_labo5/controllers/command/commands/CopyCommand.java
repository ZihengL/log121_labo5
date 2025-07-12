package ets.log121_labo5.controllers.command.commands;


import ets.log121_labo5.controllers.ImageNavigatorController;
import ets.log121_labo5.controllers.command.Command;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * Class: CopyCommand
 * Created on: 7/12/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class CopyCommand extends Command {



    // TODO: FIND A WAY TO GET THE CONTROLLER OF THE EVENT AND GET THE PERSPECTIVE FROM THE CONTROLLER
    @Override
    public void execute(ActionEvent event) {
        Node source = (Node) event.getSource();
        FXMLLoader loader = (FXMLLoader) source.getScene().getUserData();
        ImageNavigatorController controller = loader.getController();


    }

    @Override
    public void undo() {

    }
}

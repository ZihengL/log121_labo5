package ets.log121_labo5.controllers.command;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * Class: Commander
 * Created on: 7/9/2025
 * Description: Classe abstraite générique servant de racine pour la totalité
 * des actions disponibles de l'application du laboratoire. Ainsi, la classe
 * implémente également l'interface EventHandler.
 *
 * Autrement, nous avons besoins d'avoir
 *
 * @author liuzi | Zi heng Liu
 */

public abstract class Command implements EventHandler<ActionEvent> {

    // STATIC

    protected static Stage stage;

    public static void setStage(Stage stage) {
        Command.stage = stage;
    }

    // INSTANCE

    @Override
    public final void handle(ActionEvent event) {
        this.execute(event);
    }

    public abstract void execute(ActionEvent event);

    public abstract void undo();

    // TODO: POTENTIALLY LOOK TO FIX THIS.
    protected Stage getEventStage(ActionEvent event) {
        Node node = (Node) event.getSource();

        return (Stage) node.getScene().getWindow();
    }
}

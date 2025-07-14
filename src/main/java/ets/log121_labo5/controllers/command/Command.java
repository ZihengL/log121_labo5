package ets.log121_labo5.controllers.command;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * Class: Commander
 * Created on: 7/9/2025
 * Description: Classe abstraite générique servant de racine pour la totalité
 * des commandes disponibles de l'application du laboratoire. Ainsi, la classe
 * implémente également l'interface EventHandler avec un type ambigue afin d'avoir
 * la flexibilité de créer un classe wrapper pour tous les types d'événements.
 *
 * Autrement, nous avons besoins d'avoir
 *
 * @author liuzi | Zi heng Liu
 */

public abstract class Command<T extends Event> implements EventHandler<T> {

    // STATIC

    protected static Stage stage;

    public static void setStage(Stage stage) {
        Command.stage = stage;
    }

    // INSTANCE

    @Override
    public final void handle(T event) {
        this.execute(event);
    }

    public abstract void execute(T event);

    public abstract void undo();

    // TODO: POTENTIALLY LOOK TO FIX THIS.
    protected Stage getEventStage(ActionEvent event) {
        Node node = (Node) event.getSource();

        return (Stage) node.getScene().getWindow();
    }
}

package ets.log121_labo5.models.command;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 * Class: Commander
 * Created on: 7/9/2025
 * Description: Classe abstraite générique servant de racine pour la totalité
 * des commandes disponibles de l'application du laboratoire. La classe
 * implémente également l'interface EventHandler de quelconque type dérivant
 * d'Event. Ainsi, il permet beaucoup de flexibilité lorsque nous créons des classes
 * enfants de Command, puisqu'ils pourront prendre en paramètre n'importe quel type d'événement
 * sans besoins de gestions supplémentaires(ex: typecasting d'événement).
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
}

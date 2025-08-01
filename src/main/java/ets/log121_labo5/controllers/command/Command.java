package ets.log121_labo5.controllers.command;


import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * Class: Commander
 * Created on: 7/9/2025
 * Description: Classe abstraite générique servant de racine pour la totalité
 * des commandes disponibles de l'application du laboratoire. La classe
 * implémente également l'interface EventHandler de quelconque type dérivant
 * d'Event. Ainsi, il permet une bonne flexibilité au niveau de la création
 * des sous-classes puisque le type d'événement est déclaré dans la signature
 * de la classe, ce qui permet moins de gestions supplémentaires au niveau
 * des méthodes.
 *
 * @author liuzi | Zi heng Liu
 */

public abstract class Command<T extends Event> implements EventHandler<T> {

    // STATIC

    // Nous gardons ici une instance de la fenêtre racine de l'application
    // accessible pour les sous-classes de Command.
    protected static Stage stage;

    public static void setStage(Stage stage) {
        Command.stage = stage;
    }

    // INSTANCE

    // Déclaration explicite pour la clarté.
    public abstract void handle(T event);
}

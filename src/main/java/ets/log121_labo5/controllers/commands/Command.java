package ets.log121_labo5.controllers.commands;


import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * Class: Command
 * Created on: 7/9/2025
 * Description: Interface wrapper pour EventHandler servant de racine pour les classes commandes.
 * L'existence de cet interface n'est pas nécéssaire puisqu'il ne fait que redéclarer
 * le service d'EventHandler, il ne sert donc qu'à renforcer, et rendre plus explicite,
 * la hiérarchie de l'application pour les autres développeurs.
 *
 * @author liuzi | Zi heng Liu
 */

public interface Command<T extends Event> extends EventHandler<T> {

    // Déclaration explicite pour la clarté.
    void handle(T event);
}

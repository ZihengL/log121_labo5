package ets.log121_labo5.controllers.commands;


import javafx.event.Event;
import javafx.event.EventHandler;

/**
 * Class: Command
 * Created on: 7/9/2025
 * Description: Interface wrapper pour EventHandler servant de racine pour les classes commandes.
 * L'interface en soit est une redondance puisqu'il ne fait que redéclarer la méthode handle()
 * d'EventHandler. Son rôle ne sert donc qu'à renforcer une visualisation de l'hiérarchie de
 * l'application d'une manière explicite pour les autres développeurs.
 *
 * @author liuzi | Zi heng Liu
 */

public interface Command<T extends Event> extends EventHandler<T> {

    // Déclaration explicite pour la clarté.
    void handle(T event);
}

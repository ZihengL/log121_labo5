package ets.log121_labo5.models.observer;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Class: Observer
 * Created on: 7/6/2025
 * Description: Interface observateur. Les classes qui l'implémente sont les classes contrôleurs pour les
 * ImageView, donc ImageViewController et ses classes enfants.
 *
 * @author liuzi | Zi heng Liu
 */

public interface Observer {

    public void update(Observable observable);
}

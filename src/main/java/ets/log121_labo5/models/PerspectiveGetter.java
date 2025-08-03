package ets.log121_labo5.models;


/**
 * Class: PerspectiveGetter
 * Created on: 7/12/2025
 * Description: Interface fonctionelle où utile pour stocker un callback
 * de méthode get() du gestionnaire de commandes. Cette manière de faire
 * nous permet également de pouvoir facilement adapter notre code pour ajouter
 * un nombre aléatoire de fenêtres d'images. Il suffirait de passer de
 * la composition de Perspective dans CommandsManager(leftside/rightside)
 * à l'agrégation par une collection d'objets Perspective et de contrôleurs
 * ImageViewController.
 *
 * @author liuzi | Zi heng Liu
 */

public interface PerspectiveGetter {

    public Perspective getPerspective();
}

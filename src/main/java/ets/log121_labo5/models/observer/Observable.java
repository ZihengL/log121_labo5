package ets.log121_labo5.models.observer;


import java.util.ArrayList;

/**
 * Class: Subject
 * Created on: 7/6/2025
 * Description: Classe abstraite observable/sujet générique incluant
 * une liste d'abonnés permettant l'ajout ou la suppression d'éléments,
 * et une méthode notify() qui invoque update() à tous les instances d'observer
 * dans sa liste.
 *
 * @author liuzi | Zi heng Liu
 */

public abstract class Observable {

    protected ArrayList<Observer> observers;

    public Observable() {
        this.observers = new ArrayList<Observer>();
    }

    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : this.observers)
            observer.update(this);
    }
}

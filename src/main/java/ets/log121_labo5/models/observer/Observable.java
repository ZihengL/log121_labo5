package ets.log121_labo5.models.observer;


import java.util.ArrayList;

/**
 * Class: Subject
 * Created on: 7/6/2025
 * Description:
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

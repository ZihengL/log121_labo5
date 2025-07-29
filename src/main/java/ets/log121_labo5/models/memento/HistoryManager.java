package ets.log121_labo5.models.memento;


import java.util.ArrayList;

/**
 * Class: HistoryManager
 * Created on: 7/27/2025
 * Description: Implémentation du patron Memento
 *
 * @author liuzi | Zi heng Liu
 */

public class HistoryManager {

    public static final int MAX_HISTORY = 20;

    private ArrayList<State> history;
    private State current;

    public HistoryManager() {
        this.history = new ArrayList<State>();
        this.current = null;
    }

    // ACCESSORS

    public State getCurrent() {
        return this.current;
    }

    public int getCurrentIndex() {
        return this.current != null ? this.history.indexOf(this.current) : -1;
    }

    // MUTATORS

    // TODO: FIX THIS SHIT ASDASJKHDASKDHASHJK
    public void add(State state) {
        if (this.size() >= MAX_HISTORY)
            this.remove(0);

//        if (this.current != null && !this.current.equals(this.history.getLast()))
//            this.removePastCurrent();

        this.history.add(state);
        this.current = state;

        System.out.println("added: " + state + "\nIDX: " + this.history.indexOf(this.current));
    }

    public void remove(State state) {
        this.history.remove(state);
    }

    public void remove(int index) {
        this.history.remove(index);
    }

    // OTHER

    public int size() {
        return this.history.size();
    }

    public State getNext() {
        int nextIdx = this.getCurrentIndex() + 1;
        if (nextIdx > this.history.size() - 1) return null;

        this.current = this.history.get(nextIdx);
        return this.current;
    }

    public State getPrevious() {
        int prevIdx = this.getCurrentIndex() - 1;
        if (prevIdx < 0) return null;

        this.current = this.history.get(prevIdx);
        System.out.println(this.current);
        return this.current;
    }

    public void removePastCurrent() {
        int threshold = this.getCurrentIndex();
        System.out.println("THRESHOLD: " + threshold);
        if (threshold == -1) return;

        int i = this.size() - 1;
        while (i > threshold)
            this.history.remove(i--);
    }

    public String toString() {
        return this.history.toString();
    }
}

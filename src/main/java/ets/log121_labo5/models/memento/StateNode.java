package ets.log121_labo5.models.memento;


/**
 * Class: StateNode
 * Created on: 7/29/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class StateNode {

    private State state;
    private StateNode previous;
    private StateNode next;

    // CONSTRUCT

    public StateNode(State state) {
        this(state, null);
    }

    public StateNode(State state, StateNode previous) {
        this(state, previous, null);
    }

    public StateNode(State state, StateNode previous, StateNode next) {
        this.state = state;
        this.previous = previous;
        this.next = next;
    }

    // ACCESSORS

    public State getState() {
        return this.state;
    }

    public StateNode previous() {
        return this.previous;
    }

    public StateNode next() {
        return this.next;
    }

    // MUTATORS

    public void setNext(StateNode next) {
        this.next = next;

        if (this.hasNext())
            this.next.previous = this;
    }

    public void removeNext() {
        if (this.hasNext())
            this.next.previous = null;

        this.next = null;
    }

    public boolean hasNext() {
        return this.next != null;
    }

    public void setPrevious(StateNode previous) {
        this.previous = previous;

        if (this.hasPrevious())
            this.previous.next = this;
    }

    public void removePrevious() {
        if (this.hasPrevious())
            this.previous.next = null;

        this.previous = null;
    }

    public boolean hasPrevious() {
        return this.previous != null;
    }

    // OTHER

    public int countPastSelf(int count) {
        return this.hasNext() ? this.next.countPastSelf(count + 1) : count;
    }

    public int countBeforeSelf(int count) {
        return this.hasPrevious() ? this.previous.countBeforeSelf(count + 1) : count;
    }

    public String toString() {
        return this.state.toString();
    }
}

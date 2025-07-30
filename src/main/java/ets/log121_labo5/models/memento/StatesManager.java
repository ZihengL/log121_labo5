package ets.log121_labo5.models.memento;


/**
 * Class: StatesManager
 * Created on: 7/29/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class StatesManager {

    public static final int MAX_STATES = 20;

    private StateNode current;
    private StateNode head;
    private StateNode tail;

    private int size;

    public StatesManager() {
        this.current = this.head = this.tail = null;
        this.size = 0;
    }

    // ACCESSORS

    public StateNode getCurrent() {
        return this.current;
    }

    public StateNode getHead() {
        return this.head;
    }

    public StateNode getTail() {
        return this.tail;
    }

    // MUTATORS
    public void add(State state) {
        StateNode node = new StateNode(state);

        if (this.current == null && this.head == null && this.tail == null) {
            this.current = this.head = this.tail = node;
        } else {
            this.tail.setNext(node);
            this.current = this.tail.next();
            this.tail = this.tail.next();
        }

        if (this.size >= MAX_STATES) {
            this.head = this.head.next();
            this.head.removePrevious();
        } else
            this.size++;
    }

    public void remove(StateNode node) {
        if (this.size == 1) {
            this.current = this.head = this.tail = null;
        } else if (node.equals(this.head)) {
            this.head = this.head.next();
            this.size--;
        } else if (node.equals(this.tail)) {
            this.tail = this.tail.previous();
            this.size--;
        }

        if (node.equals(this.current)) {
            this.current = this.tail = this.current.previous();
            this.tail.removeNext();
            this.recount();
        }
    }

    public void removePastNode(StateNode node) {
        this.size -= node.countPastSelf(0);
        node.removeNext();
    }

    public int recount() {
        this.size = this.head.countPastSelf(1);
        return this.size;
    }

    // OTHER

    public int size() {
        return this.size;
    }

    public boolean moveLeft() {
        if (this.current == null || !this.current.hasPrevious())
            return false;

//        System.out.println("CURRENT: " + this.current);
//        System.out.println("PREV: " + this.current.previous());
        this.current = this.current.previous();
        return true;
    }

    public boolean moveRight() {
        if (this.current == null || !this.current.hasNext())
            return false;

        this.current = this.current.next();
        return true;
    }
}

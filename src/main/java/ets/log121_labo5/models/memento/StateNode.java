package ets.log121_labo5.models.memento;


/**
 * Class: StateNode
 * Created on: 7/29/2025
 * Description: Classe représentant un noeud avec une référence au
 * noeud prochain, ainsi qu'au noeud précédent. Chaque noeud possède
 * une instance de State, qui stocke l'état de l'application.
 *
 * @author liuzi | Zi heng Liu
 */

public class StateNode {

    private final State state;

    private StateNode previous;
    private StateNode next;

    // CONSTRUCT

    public StateNode(State state) {
        this.state = state;
        this.previous = null;
        this.next = null;
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

    // Assigne le pointeur du noeud prochain à celui en paramètre,
    // et assigne le pointeur précédent de ce noeud à soi-même.
    public void setNext(StateNode next) {
        if (this.hasNext())
            this.next.remove();

        this.next = next;

        if (this.hasNext())
            this.next.previous = this;
    }

    // Enlève le pointeur au noeud prochain.
    public void removeNext() {
        this.next = null;
    }

    // Retourne vrai si le noeud prochain n'est pas nulle.
    public boolean hasNext() {
        return this.next != null;
    }

    // Assigne le pointeur du noeud précédent à celui en paramètre,
    // et assigne le pointeur prochain de ce noeud à soi-même
    public void setPrevious(StateNode previous) {
        this.previous = previous;

        if (this.hasPrevious())
            this.previous.next = this;
    }

    // Enlève le pointeur au noeud précédent.
    public void removePrevious() {
        this.previous = null;
    }

    // Retourne vrai si le noeud précédent n'est pas nulle.
    public boolean hasPrevious() {
        return this.previous != null;
    }

    // Enlève les pointeurs au noeud précédent et prochain.
    public void remove() {
        this.previous = this.next = null;
    }

    // OTHER

    // Retourne le nombre de noeuds prochain, incluant soi-même, plus
    // la valeur originale donnée en paramètre à la première invocation de la méthode.
    public int countPastSelf(int count) {
        return this.hasNext() ? this.next.countPastSelf(++count) : ++count;
    }

    // Même principe que la méthode précédente, mais compte le nombre de noeuds derrière
    // celle-ci plus la valeur originale.
    public int countBeforeSelf(int count) {
        return this.hasPrevious() ? this.previous.countBeforeSelf(++count) : ++count;
    }

    public String toString() {
        return this.state.toString();
    }
}

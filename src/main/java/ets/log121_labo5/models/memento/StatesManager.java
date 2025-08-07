package ets.log121_labo5.models.memento;


/**
 * Class: StatesManager
 * Created on: 7/29/2025
 * Description: Classe de gestionnaire d'états. Il consiste essentiellement
 * en un LinkedList puisque cette structure nous permet de pouvoir facilement
 * se déplacer d'un noeud à l'autre, ainsi que de délester une partie de la chaîne.
 * Le déplacement est justement utile lors des opérations défaire/refaire, tandis
 * que le bris de référence nous est utile lorsque l'utilisateur éxécute une action
 * suite à une opération défaire. En effet, nous jettons carrément tous les noeuds
 * subséquents lorsqu'un nouveau noeud s'ajoute tandis que le noeud présent(current)
 * n'est pas dernier dans la liste.
 *
 * @author liuzi | Zi heng Liu
 */

public class StatesManager {

    // Maximum de noeuds. Le nombre choisis est aléatoire.
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

    public int size() {
        return this.size;
    }

    // Ajout d'un nouveau noeud dans la chaîne.
    public void add(State state) {
        StateNode node = new StateNode(state);

        // Si le gestionnaire est à son état initiale, tous
        // les noeuds référencerons celui ajouté, sinon seulement
        // la queue change sa référence au nouveau noeud ainsi
        // que l'état courante à condition qu'il soit également
        // à la fin de la liste.
        if (this.current == null && this.head == null && this.tail == null) {
            this.current = this.head = this.tail = node;
        } else {
            this.tail.setNext(node);
            this.tail = this.tail.next();
            this.current = this.current.equals(this.tail.previous()) ? this.tail : this.current;
        }

        // On n'incrémente pas le compteur dans le cas où on ajoute un noeud
        // alors que nous sommes au maximum puisque nous enlevons celui à la tête de la chaîne.
        if (this.size >= MAX_STATES) {
            this.head = this.head.next();
            this.head.removePrevious();
        } else
            this.size++;
    }

    // OTHER

    // Retourne vrai si le pointeur courant se déplace au noeud qui lui précède.
    public boolean moveToPrevious() {
        if (this.current == null || !this.current.hasPrevious())
            return false;

        this.current = this.current.previous();
        return true;
    }

    // Retourne vrai si le pointeur courant se déplace au noeud suivant.
    public boolean moveToNext() {
        if (this.current == null || !this.current.hasNext())
            return false;

        this.current = this.current.next();
        return true;
    }
}

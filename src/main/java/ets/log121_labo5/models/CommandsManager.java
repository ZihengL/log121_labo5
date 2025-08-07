package ets.log121_labo5.models;

import ets.log121_labo5.models.memento.State;
import ets.log121_labo5.models.memento.StatesManager;
import ets.log121_labo5.models.observer.Observable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import java.io.*;

/**
 * Class: CommandsManager
 * Created on: 7/6/2025
 * Description: Classe à instance unique qui centralise la gestion de tous les commandes
 * de l'utilisateur. Il est à noter que ce n'est pas tous les actions qui sont considérées
 * comme une commande. L'action de copier un item ou d'ouvrir un menu, par exemple, ne se
 * qualifient pas comme étant
 *
 * @author liuzi | Zi heng Liu
 */

public class CommandsManager extends Observable implements Serializable {

    // STATIC

    @Serial
    private static final long serialVersionUID = 1L;

    // Gestionnaire de commande singleton avec accéssibilité universelle.
    private static final CommandsManager instance = new CommandsManager();

    public static CommandsManager getInstance() {
        return CommandsManager.instance;
    }

    // INSTANCE

    private transient Image image;  // Image n'implémente pas Serializable
    private Perspective leftside;
    private Perspective rightside;

    private transient StatesManager statesManager; // On ne sauvegarde pas l'historique

    private CommandsManager() {
        this.image = null;
        this.leftside = new Perspective();
        this.rightside = new Perspective();
        this.statesManager = new StatesManager();

        this.recordCurrentState();
    }

    // SERIALIZATION

    // On doit définir une méthode propre pour la sérialisation
    // puisque l'objet d'instance Image n'est pas Sérialisable.
    // Nous gardons uniquement la référence de l'Image et nous assumons
    // que l'utilisateur s'occupe de ne pas déplacer l'image.
    @Serial
    private void writeObject(ObjectOutputStream output) throws IOException {
        output.defaultWriteObject();

        boolean validImage = this.image != null && this.image.getUrl() != null;
        output.writeBoolean(validImage);

        if (validImage)
            output.writeUTF(this.image.getUrl());
    }

    // Définition propre de la désérialisation qui fonction en tandem avec notre
    // algorithme de sérialisation.
    @Serial
    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
        input.defaultReadObject();

        boolean validImage = input.readBoolean();
        this.image = validImage ? new Image(input.readUTF()) : this.image;
    }

    // ACCESSORS

    public Image getImage() {
        return this.image;
    }

    public Perspective getLeftside() {
        return this.leftside;
    }

    public Perspective getRightside() {
        return this.rightside;
    }

    // Retourne un record de l'état actuel des composantes du programme.
    // Utilise copie des objets Perspective afin de maintenir leur état courant.
    public State getState() {
        String imageURL = this.image != null ? this.image.getUrl() : "";

        return new State(imageURL, this.leftside.copy(), this.rightside.copy());
    }

    // MUTATORS

    // Au besoin, on recharge l'Image en paramètre selon les contraintes définies
    // par Perspective, et on l'assigne à l'attribut Image de l'instance présent.
    // Ensuite, on remet à neuf l'état des cadres de photos selon les dimensions
    // de Image, et finalement, on appele les contrôleurs pour qu'ils font une mise à jour.
    public void setImage(Image image) {
        this.image = Perspective.validateDimensions(image);

        double width = this.image.getWidth(),
               height = this.image.getHeight();
        this.leftside.setDimensions(width, height);
        this.rightside.setDimensions(width, height);

        this.update();
    }

    // On sauvegarde l'état courante avant de procéder, et nous assignons
    // leftside à une copie de la Perspective en paramètre afin de briser la référence
    public void setLeftside(Perspective leftside) {
        this.recordCurrentState();
        this.leftside = leftside.copy();

        this.update();
    }

    public void setRightside(Perspective rightside) {
        this.recordCurrentState();
        this.rightside = rightside.copy();

        this.update();
    }

    // MENUBAR: FILE MENU

    // Assure que l'extension du fichier en paramètre est valide, et
    // sauvegarde l'état courant du programme dans le fichier.
    public void saveStateToFile(File file) throws IOException {
        String path = file.getPath().toLowerCase();
        file = !path.endsWith(".ser") ? new File(path + ".ser") : file;

        try (ObjectOutputStream output = new ObjectOutputStream(
                new FileOutputStream(file))) {
            output.writeObject(this);
        }
    }

    // TODO: FIX WEIRD REGRESSION BUG WITH PERSPECTIVE UPON LOADING.
    // Si l'extension du fichier en paramètre est valide, on procède à la
    // lecture et le rétablissement de l'état du programme à partir de celle-ci.
    public void loadStateFromFile(File file) throws IOException, ClassNotFoundException {
        if (!file.getPath().endsWith(".ser")) return;

        try (ObjectInputStream input = new ObjectInputStream(
                new FileInputStream(file))) {
            Object object = input.readObject();

            if (object instanceof CommandsManager loaded) {
                this.setState(loaded.getState());
//                this.image = Perspective.validateDimensions(loaded.image);
//                this.leftside = loaded.leftside;
//                this.rightside = loaded.rightside;
//
//                this.update();
            }
        }
    }

    // MENUBAR: EDITING

    // Si possible, déplace et prends la référence courante dans le gestionnaire à celui qui lui
    // précède, et rétabli les attributs de CommandsManager selon celle-ci.
    public void undo() {
        if (!this.statesManager.moveToPrevious()) return;

        State previous = this.statesManager.getCurrent().getState();
        this.setState(previous);
    }

    // Si possible, déplace la référence courante dans le gestionnaire au prochain,
    // et rétabli les attributs de CommandsManager selon celle-ci.
    public void redo() {
        if (!this.statesManager.moveToNext()) return;

        State next = this.statesManager.getCurrent().getState();
        this.setState(next);
    }

    // Mutateur qui prends en paramètre un objet State qui comprends
    // tous les objets d'instances de CommandsManager à l'exception
    // du gestionnaire d'états. On ne fait qu'invoquer une mise à jour
    // et non la sauvegarde d'état puisque cette méthode est seulement utilisé
    // par les commande undo/redo/défaire/refaire.
    public void setState(State state) {
        if (state == null) return;

        if (!state.imageURL().isEmpty())
            this.image = Perspective.validateDimensions(new Image(state.imageURL()));
        else
            this.image = null;

        this.leftside = state.leftside();
        this.rightside = state.rightside();

        this.notifyObservers();
    }

    // NAVIGATION

    // Commande qui éxécute un zoom. L'objet Perspective est injecté à partir du EventHandler
    // qui invoque cette méthode afin d'éviter de la répétition de code (zoomLeftside, zoomRightside, etc).
    // En effet, nous utilisons un système d'Interface fonctionnelles en confluence avec
    // les instances de contrôleurs afin de donner accès à la bonne instance de Perspective.
    public void zoom(Perspective perspective, Point2D position, double delta) {
        perspective.zoom(position, delta);

        this.update();
    }

    // Commande qui éxécute un défilement suivant le même flux de données que la méthode zoom().
    public void pan(Perspective perspective, Point2D position, Bounds bounds) {
        perspective.pan(position, bounds);

        this.update();
    }

    // OTHER

    // Sauvegarde l'état actuel de l'objet dans un State de type Record, et on l'ajoute
    // dans la liste d'une classe dédiée à la gestion des états(StatesManager).
    public void recordCurrentState() {
        State state = this.getState();

        this.statesManager.add(state);
    }

    // On regroupe l'invocation de la sauvegarde d'état avec la notification d'observateurs.
    // Cette méthode est invoquée après chaque instance où une modification d'état
    public void update() {
        this.recordCurrentState();
        this.notifyObservers();
    }

    public String toString() {
        return String.format("Leftside: %s - Rightside: %s", this.leftside, this.rightside);
    }
}

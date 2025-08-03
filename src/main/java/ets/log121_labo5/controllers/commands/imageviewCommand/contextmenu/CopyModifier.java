package ets.log121_labo5.controllers.commands.imageviewCommand.contextmenu;


import ets.log121_labo5.models.Perspective;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioMenuItem;

/**
 * Class: CopyModifierCommand
 * Created on: 7/31/2025
 * Description: Classe abstraite dérivant de ContextMenuCommand
 * qui défini le noyau pour tout commande appliquant une stratégie de copie.
 *
 * @author liuzi | Zi heng Liu
 */

public abstract class CopyModifier extends ContextMenuCommand {

    protected boolean isActive;

    public CopyModifier() {
        this(true);
    }

    // isActive est vrai par défaut puisque nous voulons copier
    // l'entièreté de la Perspective en copie.
    public CopyModifier(boolean isActive) {
        this.isActive = isActive;

        ContextMenuCommand.addModifier(this);
    }

    // Gouverne l'état actif de l'algorithme selon l'état du bouton radio provenant
    // de la source de l'événement. On peut également déléguer le traitement de handle()
    // aux objets héritiers, si jamais nous voulons ajouter des stratégies
    // avancés qui utilisent d'autres types de composantes UI qu'un bouton radio.
    @Override
    public void handle(ActionEvent event) {
        this.isActive = ((RadioMenuItem) event.getSource()).isSelected();
    }

    // Déclaration de la méthode qui applique le transfert d'attributs du modèle au récipient.
    public abstract void applyModifier(Perspective recipient, Perspective model);
}

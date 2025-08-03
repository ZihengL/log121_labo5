package ets.log121_labo5.controllers.commands.imageviewCommand.contextmenu;


import ets.log121_labo5.controllers.commands.imageviewCommand.ImageViewCommand;
import javafx.scene.control.ContextMenu;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Class: ShowContextMenuCommand
 * Created on: 8/2/2025
 * Description: Commande qui cache/révèle le menu de contexte dépendamment de l'état actuelle
 * du menu.
 *
 * @author liuzi | Zi heng Liu
 */

public class ShowContextMenuCommand extends ImageViewCommand<MouseEvent> {

    private final ContextMenu menu;

    public ShowContextMenuCommand(ContextMenu menu) {
        this.menu = menu;
    }

    // Révèle le menu de contexte si n'est pas présentement ouvert
    // et que le bouton cliqué est le clique-droit. Autrement on cache le menu.
    // L'événement est consommé afin de d'assurer que l'évènement ne se propage pas
    // de manière inattendu.
    @Override
    public void handle(MouseEvent event) {
        ImageView view = this.getImageView(event);
        if (view == null) return;

        if (!this.menu.isShowing() && event.isSecondaryButtonDown())
            this.menu.show(view, event.getScreenX(), event.getScreenY());
        else
            this.menu.hide();

        event.consume();
    }
}

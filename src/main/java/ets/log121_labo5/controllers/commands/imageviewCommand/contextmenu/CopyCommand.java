package ets.log121_labo5.controllers.commands.imageviewCommand.contextmenu;


import ets.log121_labo5.models.Perspective;
import javafx.event.ActionEvent;

/**
 * Class: CopyCommand
 * Created on: 7/12/2025
 * Description: Classe commande qui invoque setCopy() de ContextMenuCommand.
 *
 * @author liuzi | Zi heng Liu
 */

public class CopyCommand extends ContextMenuCommand {

    // Instance de Perspective donnée par le PerspectiveGetter de leftside/rightside.
    // On utilise une copie pour briser la référence et maintenir son état actuel.
    @Override
    public void handle(ActionEvent event) {
        Perspective perspective = this.getPerspective(event);
        ContextMenuCommand.setCopy(perspective);
    }
}

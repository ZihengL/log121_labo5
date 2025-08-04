package ets.log121_labo5.models.memento;

import ets.log121_labo5.models.Perspective;
import java.io.*;

/**
 * Record: State
 * Created on: 7/6/2025
 * Description: Record de l'état de l'application. Utilisé dans notre
 * implémentation du patron memento. L'action de défaire ou de refaire
 * implique la restoration d'un objet State.
 *
 * @author liuzi | Zi heng Liu
 */

public record State(String imageURL, Perspective leftside, Perspective rightside) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // On ne prend que le nom du fichier du URL de l'image.
    public String toString() {
        String filename = "";

        if (!this.imageURL.isEmpty())
            filename = this.imageURL.substring(this.imageURL.lastIndexOf("\\") + 1);

        return String.format("(L%s ; R%s, url=\"%s\")", this.leftside, this.rightside, filename);
    }
}

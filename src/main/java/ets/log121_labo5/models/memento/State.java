package ets.log121_labo5.models.memento;

import ets.log121_labo5.models.Perspective;
import java.io.*;

/**
 * Record: State
 * Created on: 7/6/2025
 * Description: Record de l'état de l'application
 *
 * @author liuzi | Zi heng Liu
 */

public record State(String imageURL, Perspective leftside, Perspective rightside) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public String toString() {
        String filename = this.imageURL.substring(this.imageURL.lastIndexOf("\\") + 1);

        return String.format("(L%s ; R%s, url=\"%s\")", this.leftside, this.rightside, filename);
    }
}

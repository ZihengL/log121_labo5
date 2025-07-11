package ets.log121_labo5.models;

import javafx.scene.image.Image;

import java.io.Serializable;

public record SaveState(Image image, Perspective leftside, Perspective rightside) implements Serializable {

    public String toString() {
        String url = this.image != null ? this.image.getUrl() : "";

        return String.format("(L: %s, R: %s, url=\"%s\")", this.leftside, this.rightside, url);
    }
}

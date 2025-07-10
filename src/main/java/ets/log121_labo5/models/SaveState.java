package ets.log121_labo5.models;

import javafx.scene.image.Image;

import java.io.Serializable;

public record SaveState(Image image, Perspective leftside, Perspective rightside) implements Serializable {
}

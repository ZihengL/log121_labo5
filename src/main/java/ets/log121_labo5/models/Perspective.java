package ets.log121_labo5.models;


import java.io.Serializable;
import java.util.Objects;

/**
 * Class: Perspective
 * Created on: 7/7/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class Perspective implements Serializable {

    // STATIC

    public static final double MIN_ZOOM = -2.;
    public static final double MAX_ZOOM = 2.;

    public static boolean isValidZoom(double zoom) {
        return zoom >= MIN_ZOOM && zoom <= MAX_ZOOM;
    }

    // INSTANCE

    private Vector position;
    private double zoom;


    public Perspective() {
        this(0., 0., 0.);
    }

    public Perspective(double x, double y, double zoom) {
        this(new Vector(x, y), zoom);
    }

    public Perspective(Vector position, double zoom) {
        this.position = position;
        this.zoom = zoom;
    }

    // ACCESSORS

    public Vector getPosition() {
        return this.position;
    }

    public double getPositionX() {
        return this.position.x;
    }

    public double getPositionY() {
        return this.position.y;
    }

    public double getZoom() {
        return this.zoom;
    }

    // MUTATORS

    public void setPosition(double x, double y) {
        this.position.setPosition(x, y);
    }

    public boolean setZoom(double zoom) {
        if (Perspective.isValidZoom(zoom)) {
            this.zoom = zoom;
            return true;
        }

        return false;
    }

    public int hashCode() {
        return Objects.hash(this.position, this.zoom);
    }

    // OTHER

    public String toString() {
        return String.format("(Position: %s | Zoom: %.2f)", this.position, (this.zoom * 100.));
    }
}

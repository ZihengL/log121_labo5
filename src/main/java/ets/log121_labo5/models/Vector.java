package ets.log121_labo5.models;


import java.io.Serial;
import java.io.Serializable;

/**
 * Class: Vector
 * Created on: 7/6/2025
 * Description: Représentation d'un vecteur à 2 dimensions avec des fonctions génériques typiques d'un vecteur.
 *
 * @author liuzi | Zi heng Liu
 */

public class Vector implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public double x;
    public double y;

    public Vector() {
        this(0., 0.);
    }

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // UNITV

    public Vector unit() {
        double norm = this.norm();

        return new Vector(this.x / norm, this.y / norm);
    }

    public double unitX() {
        return this.x / this.norm();
    }

    public double unitY() {
        return this.y / this.norm();
    }

    // SET POSITION

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setPosition(Vector position) {
        this.x = position.x;
        this.y = position.y;
    }

    // TRANSLATE

    public void translate(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void translate(Vector displacement) {
        this.x += displacement.x;
        this.y += displacement.y;
    }

    // NORM/LENGTH

    public double norm() {
        return Math.sqrt(Math.pow(this.x, 2.) + Math.pow(this.y, 2.));
    }

    // DISTANCE

    public double distance(Vector other) {
        return this.delta(other).norm();
    }

    public Vector delta(Vector other) {
        return new Vector(other.x - this.x, other.y - this.y);
    }

    // OTHER

    public String toString() {
        return String.format("[ %.2f  %.2f ]", this.x, this.y);
    }
}

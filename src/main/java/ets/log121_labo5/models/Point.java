package ets.log121_labo5.models;


import javafx.geometry.Point2D;

import java.io.Serial;
import java.io.Serializable;

/**
 * Class: Point
 * Created on: 7/15/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class Point implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public double x;
    public double y;

    public Point() {
        this(0., 0.);
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // ACCESSORS

    public Point2D getPoint2D() {
        return new Point2D(this.x, this.y);
    }

    // MUTATORS

    public Point unit() {
        return this.divide(this.norm());
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setPosition(Point position) {
        this.x = position.x;
        this.y = position.y;
    }

    // NORM/LENGTH

    public double norm() {
        return Math.sqrt(Math.pow(this.x, 2.) + Math.pow(this.y, 2.));
    }

    // DISTANCE

    public double distance(Point other) {
        return this.delta(other).norm();
    }

    public Point delta(Point other) {
        return new Point(other.x - this.x, other.y - this.y);
    }

    // OTHER

    public void translate(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void translate(Point displacement) {
        this.x += displacement.x;
        this.y += displacement.y;
    }

    public Point multiply(double scalar) {
        return new Point(this.x * scalar, this.y * scalar);
    }

    public Point divide(double scalar) {
        return new Point(this.x / scalar, this.y / scalar);
    }

    public String toString() {
        return String.format("[ %.2f  %.2f ]", this.x, this.y);
    }
}

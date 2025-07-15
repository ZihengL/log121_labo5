package ets.log121_labo5.models;


import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

import java.io.Serial;
import java.io.Serializable;

/**
 * Class: Zoom
 * Created on: 7/15/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class Rectangle implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Point minBounds;
    private Point maxBounds;

    public Rectangle() {
        this(0., 0., 0., 0.);
    }

    public Rectangle(double x, double y, double width, double height) {
        this(new Point(x, y), new Point(width, height));
    }

    public Rectangle(Point minBounds, Point maxBounds) {
        this.minBounds = minBounds;
        this.maxBounds = maxBounds;
    }

    // ACCESSORS

    public Point getMinBounds() {
        return this.minBounds;
    }

    public Point getMaxBounds() {
        return this.maxBounds;
    }

    public Rectangle2D getRectangle2D() {
        return new Rectangle2D(
                this.minBounds.x, this.minBounds.y,
                this.maxBounds.x, this.maxBounds.y);
    }

    // MUTATORS

    public void setBounds(Rectangle2D rect) {
        this.setBounds(rect.getMinX(), rect.getMinY(), rect.getMaxX(), rect.getMaxY());
    }

    public void setBounds(double x, double y, double width, double height) {
        this.setMinBounds(x, y);
        this.setMaxBounds(width, height);
    }

    public void setMinBounds(double x, double y) {
        this.minBounds.x = x;
        this.minBounds.y = y;
    }

    public void setMaxBounds(double width, double height) {
        this.maxBounds.x = width;
        this.maxBounds.y = height;
    }

    // OTHER

    // IS WITHIN BOUNDS
    public boolean isWithinBounds(Point2D point) {
        return this.isWithinBoundsX(point.getX()) && this.isWithinBoundsY(point.getY());
    }

    public boolean isWithinBoundsX(double x) {
        return x >= this.minBounds.x && x <= this.maxBounds.x;
    }

    public boolean isWithinBoundsY(double y) {
        return y >= this.minBounds.y && y <= this.maxBounds.y;
    }

    // CLOSEST POINT WITHIN BOUNDS
    public Point2D getClosestWithin(Point2D point) {
        return new Point2D(
                this.getClosestWithinX(point.getX()),
                this.getClosestWithinY(point.getY())
        );
    }

    public double getClosestWithinX(double x) {
        return Math.max(this.minBounds.x, Math.min(this.maxBounds.x, x));
    }

    public double getClosestWithinY(double y) {
        return Math.max(this.minBounds.y, Math.min(this.maxBounds.y, y));
    }

    public String toString() {
        Point min = this.minBounds, max = this.maxBounds;

        return String.format("x[%.2f  %.2f] y[%.2f  %.2f]", min.x, max.x, min.y, max.y);
    }
}

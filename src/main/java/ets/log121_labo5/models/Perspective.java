package ets.log121_labo5.models;


import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

import java.io.Serial;
import java.io.Serializable;

/**
 * Class: Perspective
 * Created on: 7/7/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class Perspective implements Serializable {

    // STATIC

    @Serial
    private static final long serialVersionUID = 1L;

    public static final double MIN_ZOOM = 10.;

    public static final double ZOOM_FACTOR = 1.01;


    // INSTANCE

    private Rectangle2D bounds;
    private Rectangle2D viewport;

    public Perspective() {
        this(0., 0., 0., 0.);
    }

    public Perspective(double minX, double minY, double maxX, double maxY) {
        this(new Rectangle2D(minX, minY, maxX, maxY), new Rectangle2D(minX, minY, maxX, maxY));
    }

    public Perspective(Rectangle2D viewport, Rectangle2D bounds) {
        this.viewport = viewport;
        this.bounds = bounds;
    }

    // ACCESSORS

    public Rectangle2D getViewport() {
        return this.viewport;
    }

    public Rectangle2D getBounds() {
        return this.bounds;
    }

    // MUTATORS

    public void setViewport(double x, double y, double width, double height) {
        this.setViewport(new Rectangle2D(x, y, width, height));
    }

    public void setViewport(Rectangle2D viewport) {
        this.viewport = viewport;
    }

    public void setAll(double width, double height) {
        this.viewport = this.bounds = new Rectangle2D(0., 0., width, height);
    }

    public void reset() {
        this.viewport = this.bounds;
    }

    // COPY

    public Perspective copy() {
        return new Perspective(this.viewport, this.bounds);
    }

    // ZOOM

    public void zoom(double delta) {
        double magnitude = this.getZoomMagnitude(delta);

        double x,
               minX = this.viewport.getMinX(),
               centerX = this.viewport.getWidth() / 2 + minX,
               width = this.viewport.getWidth() * magnitude,
               maxWidth = this.bounds.getWidth();
        x = this.getZoomPosition(centerX, minX, width, maxWidth, magnitude);

        double y,
               minY = this.viewport.getMinY(),
               centerY = this.viewport.getHeight() / 2 + minY,
               height = this.viewport.getHeight() * magnitude,
               maxHeight = this.bounds.getHeight();
        y = this.getZoomPosition(centerY, minY, height, maxHeight, magnitude);

        this.setViewport(x, y, width, height);
    }

    private double getZoomPosition(double pos, double minPos, double size, double maxSize, double magnitude) {
        double value = pos - (pos - minPos) * magnitude,
               max = maxSize - size;

//        return Math.max(0, Math.min(value, max));
        return Math.clamp(value, 0, max);
    }

    // PANNING ZOOM
    public void zoom(Point2D position, double magnitude) {
        double x, y, width, height;

        width = this.viewport.getWidth() * magnitude;
        x = this.clamp(position.getX() - (position.getX() - this.viewport.getMinX()) * magnitude, 0, this.bounds.getWidth() - width);

        height = this.viewport.getHeight() * magnitude;
        y = this.clamp(position.getY() - (position.getY() - this.viewport.getMinY()) * magnitude, 0, this.bounds.getHeight() - height);

        this.setViewport(x, y, width, height);
    }

    public void zoom(Bounds bounds, Point2D position, double zoom) {
        Point2D target = this.getRelativePosition(bounds, position);

        double x, y, width, height, magnitude = this.getZoomMagnitude(zoom);
        width = this.viewport.getWidth() * magnitude;
        x = Math.clamp(target.getX() - (target.getX() - this.viewport.getMinX()) * magnitude, 0, this.bounds.getWidth() - width);

        height = this.viewport.getHeight() * magnitude;
        y = Math.clamp(target.getY() - (target.getY() - this.viewport.getMinY()) * magnitude, 0, this.bounds.getHeight() - height);

        this.setViewport(x, y, width, height);
    }

    public double getZoomMagnitude(double delta) {
        double width = this.viewport.getWidth(), height = this.viewport.getHeight(),
               boundsX = this.bounds.getWidth(), boundsY = this.bounds.getHeight();

        double magnitude = Math.pow(ZOOM_FACTOR, delta),
               min = Math.min(MIN_ZOOM / width, MIN_ZOOM / height),
               max = Math.max(boundsX / width, boundsY / height);

        return Math.clamp(magnitude, min, max);
    }

    // PAN
    public void pan(Point2D target, Bounds localBounds) {
        double x, y;

        x = this.getRelativePosition(
            target.getX(),
            this.viewport.getMinX(),
            this.viewport.getWidth(),
            localBounds.getWidth()
        );

        y = this.getRelativePosition(
            target.getY(),
            this.viewport.getMinY(),
            this.viewport.getHeight(),
            localBounds.getHeight()
        );

        Point2D newCenter = new Point2D(x, y);
        Point2D currentCenter = this.getViewportCenter();

        Point2D delta = currentCenter.subtract(newCenter);
        delta = newCenter.subtract(currentCenter);

        double width = this.bounds.getWidth();
        double height = this.bounds.getHeight();

        double maxX = width - this.viewport.getWidth();
        double maxY = height - this.viewport.getHeight();

//        double minX = this.clamp(this.viewport.getMinX() - delta.getX(), 0, maxX);
//        double minY = this.clamp(this.viewport.getMinX() - delta.getY(), 0, maxY);
        double minX = this.clamp(this.viewport.getMinX() + delta.getX(), 0, maxX);
        double minY = this.clamp(this.viewport.getMinX() + delta.getY(), 0, maxY);

        this.setViewport(minX, minY, maxX, maxY);
    }

    public double getRelativePosition(double pos, double min, double size, double localBound) {
        return min + size * (pos / localBound);
    }

    private void shift(Point2D delta) {
        double minX, minY;

        minX = this.getShiftedPosition(
                this.viewport.getWidth(),
                this.viewport.getMinX(),
                this.bounds.getWidth(),
                delta.getX()
        );

        minY = this.getShiftedPosition(
                this.viewport.getHeight(),
                this.viewport.getMinY(),
                this.bounds.getHeight(),
                delta.getY()
        );

        this.setViewport(minX, minY, this.viewport.getWidth(), this.viewport.getHeight());
    }

    private double getShiftedPosition(double size, double minPos, double bound, double delta) {
        return Math.clamp(minPos - delta, 0, bound - size);
    }

    public Point2D getRelativePosition(Bounds bounds, Point2D target) {
        double minX = this.viewport.getMinX(), minY = this.viewport.getMinY(),
                width = this.viewport.getWidth(), height = this.viewport.getHeight();

        return new Point2D(
                (target.getX() / bounds.getWidth()) * width + minX,
                (target.getY() / bounds.getHeight()) * height + minY
        );
    }

    // OTHER

    public Point2D getClosestWithin(Point2D position) {
        return null;
    }

    public double clamp(double value, double min, double max) {
        return Math.min(max, Math.max(min, value));
    }

    public Point2D getCenter() {
        return new Point2D(this.bounds.getWidth() / 2, this.bounds.getHeight() / 2);
    }

    public Point2D getViewportCenter() {
        double x = (this.viewport.getWidth() / 2) + this.viewport.getMinX(),
               y = (this.viewport.getHeight() / 2) + this.viewport.getMinY();

        return new Point2D(x, y);
    }

    public String toString() {
        return this.bounds + " | " + this.viewport;
    }
}

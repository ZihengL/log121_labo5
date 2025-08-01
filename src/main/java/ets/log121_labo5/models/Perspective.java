package ets.log121_labo5.models;


import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

import java.io.*;

/**
 * Class: Perspective
 * Created on: 7/7/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class Perspective implements Serializable {

    // STATIC

    public static final double MAX_HEIGHT = 800.;

    @Serial
    private static final long serialVersionUID = 1L;

    public static final double MIN_ZOOM = 10.;
    public static final double ZOOM_FACTOR = 1.01;

    // Méthode utilitaire pour s'assurer que les coordonnées de zoom et pan ne
    // dépassent pas les bornes de l'image. Même implémentation que Math.clamp()
    // exluant la gestions d'erreurs.
    public static double clamp(double value, double min, double max) {
        return Math.min(max, Math.max(min, value));
    }

    // Retourne l'Image si sa hauteur ne dépasse pas le max défini,
    // sinon retourne une nouvelle Image avec les mêmes proportions
    // à la hauteur maximale.
    public static Image setImageDimensions(Image image) {
        double width = image.getWidth(), height = image.getHeight();
        if (height <= MAX_HEIGHT) return image;

        double scale = MAX_HEIGHT / height;
        width = width * scale;
        height = height * scale;

        return new Image(image.getUrl(), width, height, true, true);
    }

    // INSTANCE

    // Objets Rectangle2D transigeants puisqu'ils ne sont pas sérializables.
    // Alternativement, nous aurons pu implémenter une classe adaptateur pour
    // la sérialization.
    private transient Rectangle2D viewport;
    private transient Rectangle2D bounds;

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

    // SERIALIZATION

    @Serial
    private void writeObject(ObjectOutputStream output) throws IOException {
        output.writeDouble(this.viewport.getMinX());
        output.writeDouble(this.viewport.getMinY());
        output.writeDouble(this.viewport.getWidth());
        output.writeDouble(this.viewport.getHeight());

        output.writeDouble(this.bounds.getMinX());
        output.writeDouble(this.bounds.getMinY());
        output.writeDouble(this.bounds.getWidth());
        output.writeDouble(this.bounds.getHeight());
    }

    @Serial
    private void readObject(ObjectInputStream input) throws IOException, ClassNotFoundException {
        double minX, minY, width, height;

        minX = input.readDouble();
        minY = input.readDouble();
        width = input.readDouble();
        height = input.readDouble();
        this.viewport = new Rectangle2D(minX, minY, width, height);

        minX = input.readDouble();
        minY = input.readDouble();
        width = input.readDouble();
        height = input.readDouble();
        this.bounds = new Rectangle2D(minX, minY, width, height);
    }

    // ACCESSORS

    public Rectangle2D getViewport() {
        return this.viewport;
    }

    public Rectangle2D getBounds() {
        return this.bounds;
    }

    // Retourne le centre des bornes de l'Image.
    public Point2D getCenter() {
        return new Point2D(this.bounds.getWidth() / 2, this.bounds.getHeight() / 2);
    }

    // Retourne le centre du rectangle de la vue actuelle.
    public Point2D getViewportCenter() {
        double x = (this.viewport.getWidth() / 2) + this.viewport.getMinX(),
                y = (this.viewport.getHeight() / 2) + this.viewport.getMinY();

        return new Point2D(x, y);
    }

    // Retourne la position du coin supérieur gauche de la vue actuelle.
    public Point2D getViewportPosition() {
        return new Point2D(this.viewport.getMinX(), this.viewport.getMinY());
    }

    // Retourne un vecteur représentant la taille de la vue actuelle.
    public Point2D getSize() {
        return new Point2D(this.viewport.getWidth(), this.viewport.getHeight());
    }

    // MUTATORS

    // Remet à neuf la vue et les bornes de l'Image selon les valeurs en paramètres.
    public void setDimensions(double width, double height) {
        this.viewport = this.bounds = new Rectangle2D(0., 0., width, height);
    }

    public void setViewport(double x, double y, double width, double height) {
        this.setViewport(new Rectangle2D(x, y, width, height));
    }

    public void setViewport(Rectangle2D viewport) {
        this.viewport = viewport;
    }

    public void setPosition(Point2D position) {
        double width = this.viewport.getWidth(), height = this.viewport.getHeight();

        this.setViewport(position.getX(), position.getY(), width, height);
    }

    public void setCenter(Point2D position) {
        // TODO: REPLACE SETPOSITION WITH THIS.
    }

    public void setSize(Point2D size) {
        double x = this.viewport.getMinX(), y = this.viewport.getMinY();

        this.setViewport(x, y, size.getX(), size.getY());
    }

    // OTHER

    // COPY
    public Perspective copy() {
        return new Perspective(this.viewport, this.bounds);
    }

    // ZOOM
    // Partiellement basé sur: https://gist.github.com/james-d/ce5ec1fd44ce6c64e81a
    public void zoom(Point2D position, double delta) {
        double magnitude = this.getZoomMagnitude(delta);
        Rectangle2D port = this.viewport;

        double minX = port.getMinX(),
               maxX = port.getWidth() * magnitude,
               boundX = this.bounds.getWidth();
        double x = this.getZoomPosition(position.getX(), minX, maxX, boundX, magnitude);

        double minY = port.getMinY(),
               maxY = port.getHeight() * magnitude,
               boundY = this.bounds.getHeight();
        double y = this.getZoomPosition(position.getY(), minY, maxY, boundY, magnitude);

        this.setViewport(x, y, maxX, maxY);
    }

    // Retourne
    public double getZoomMagnitude(double delta) {
        System.out.println(delta);

        double width = this.viewport.getWidth(), height = this.viewport.getHeight(),
                boundX = this.bounds.getWidth(), boundY = this.bounds.getHeight();

        double magnitude = Math.pow(ZOOM_FACTOR, delta),
               min = Math.min(MIN_ZOOM / width, MIN_ZOOM / height),
               max = Math.max(boundX / width, boundY / height);

        return Perspective.clamp(magnitude, min, max);
    }

    private double getZoomPosition(double center, double min, double max, double bound, double magnitude) {
        return Perspective.clamp(
                center - (center - min) * magnitude,
                0,
                bound - max
        );
    }

    // PAN
    // Partiellement basé sur: https://gist.github.com/james-d/ce5ec1fd44ce6c64e81a
    public void pan(Point2D position, Bounds bounds) {
        Rectangle2D port = this.viewport;

        double posX = position.getX(),
               minX = port.getMinX(),
               width = port.getWidth(),
               localBoundX = bounds.getWidth(),
               boundX = this.bounds.getWidth();
        double x = this.getPanPosition(posX, minX, width, localBoundX, boundX);

        double posY = position.getY(),
               minY = port.getMinY(),
               height = port.getHeight(),
               localBoundY = bounds.getHeight(),
               boundsY = this.bounds.getHeight();
        double y = this.getPanPosition(posY, minY, height, localBoundY, boundsY);

        this.setViewport(x, y, width, height);
    }

    private double getPanPosition(double center, double min, double max, double localBound, double bound) {
        return Perspective.clamp(
                (min + center * (max / localBound)) - (max / 2),
                0,
                bound - max
        );
    }

    public String toString() {
        double x = this.viewport.getMinX(), y = this.viewport.getMinY(),
               width = this.viewport.getWidth(), height = this.viewport.getHeight();

        return String.format("(%.2f, %.2f, %.2f, %.2f)", x, y, width, height);
    }
}

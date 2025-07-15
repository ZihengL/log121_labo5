package ets.log121_labo5.controllers.command.commands.navigation;


import ets.log121_labo5.controllers.command.Command;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;

/**
 * Class: ZoomCommand
 * Created on: 7/14/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class ZoomCommand extends Command<ScrollEvent> {

    // TODO: CLEAN THIS UP
    // TODO: GOTTA REPLACE THE 2D VECTOR IN PERSPECTIVE FOR RECTANGLE2D
    @Override
    public void execute(ScrollEvent event) {
        ImageView view = (ImageView) event.getSource();

        if (view.getImage() == null) return;

        double delta = -event.getDeltaY();
        Rectangle2D viewport = view.getViewport();

        Image image = view.getImage();
        double minpixels = 10.;
        double scale = this.clamp(
                Math.pow(1.01, delta),
                Math.min(minpixels / viewport.getWidth(), minpixels / viewport.getHeight()),
                Math.max(image.getWidth() / viewport.getWidth(), image.getHeight() / viewport.getHeight())
        );

        Point2D mouse = this.imageViewToImage(view, new Point2D(event.getX(), event.getY()));

        double newWidth = viewport.getWidth() * scale;
        double newHeight = viewport.getHeight() * scale;

        double newMinX = clamp(mouse.getX() - (mouse.getX() - viewport.getMinX()) * scale,
                0, image.getWidth() - newWidth);
        double newMinY = clamp(mouse.getY() - (mouse.getY() - viewport.getMinY()) * scale,
                0, image.getHeight() - newHeight);

        Rectangle2D newViewPort = new Rectangle2D(newMinX, newMinY, newWidth, newHeight);
        view.setViewport(newViewPort);
    }

    @Override
    public void undo() {

    }

    private double clamp(double value, double min, double max) {
        return Math.min(max, Math.max(min, value));
    }

    private Point2D imageViewToImage(ImageView imageView, Point2D imageViewCoordinates) {
        double xProportion = imageViewCoordinates.getX() / imageView.getBoundsInLocal().getWidth();
        double yProportion = imageViewCoordinates.getY() / imageView.getBoundsInLocal().getHeight();

        Rectangle2D viewport = imageView.getViewport();
        return new Point2D(
                viewport.getMinX() + xProportion * viewport.getWidth(),
                viewport.getMinY() + yProportion * viewport.getHeight());
    }
}

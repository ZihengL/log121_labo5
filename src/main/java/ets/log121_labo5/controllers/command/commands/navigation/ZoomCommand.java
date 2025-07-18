package ets.log121_labo5.controllers.command.commands.navigation;


import ets.log121_labo5.controllers.ImageNavigatorController;
import ets.log121_labo5.controllers.command.Command;
import ets.log121_labo5.controllers.command.CommandsManager;
import ets.log121_labo5.models.Perspective;
import javafx.geometry.Bounds;
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

// Code basé sur: https://gist.github.com/james-d/ce5ec1fd44ce6c64e81a

public class ZoomCommand extends Command<ScrollEvent> {

    @Override
    public void execute(ScrollEvent event) {
        ImageView view = (ImageView) event.getSource();
        if (view.getImage() == null) return;

        ImageNavigatorController controller = (ImageNavigatorController) view.getProperties().get("controller");

        Perspective perspective = controller.getPerspective();
        double delta = -event.getDeltaY();
//        CommandsManager.getInstance().zoom(perspective, delta);

        Bounds bounds = view.getBoundsInLocal();
        Point2D target = new Point2D(event.getX(), event.getY());
//        CommandsManager.getInstance().zoom(perspective, bounds, target, delta);

        this.oldZoom1(event);
    }

    @Override
    public void undo() {

    }

    // OLD
    private void oldZoom1(ScrollEvent event) {
        ImageView view = (ImageView) event.getSource();
        if (view.getImage() == null) return;

        ImageNavigatorController controller = (ImageNavigatorController) view.getProperties().get("controller");

        Perspective perspective = controller.getPerspective();
        Bounds bounds = view.getBoundsInLocal();
        Point2D position = new Point2D(event.getX(), event.getY());
        double zoom = -event.getDeltaY();

        CommandsManager.getInstance().zoom(perspective, bounds, position, zoom);
    }

    private void oldZoom2(ScrollEvent event) {
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


    private double clamp(double value, double min, double max) {

        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
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

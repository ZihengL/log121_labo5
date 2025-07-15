package ets.log121_labo5.models;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.beans.Transient;
import java.io.*;

public record State(@Transient Image image, Perspective leftside, Perspective rightside) implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public static WritableImage toWritableImage(Image image) {
        if (image == null) return null;

        int width = (int) image.getWidth(), height = (int) image.getHeight();

        WritableImage writableImage = new WritableImage(width, height);
        writableImage.getPixelWriter().setPixels(
                0, 0, width, height,
                image.getPixelReader(), 0, 0);

        return writableImage;
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {

    }

    @Serial
    private void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException {
        ois.defaultReadObject();

        BufferedImage buffered = ImageIO.read(ois);
//        if (buffered != null)
//            this.image = SwingFXUtils.toFXImage(buffered, null);
    }

    public String toString() {
        String url = this.image != null ? this.image.toString() : "";

        return String.format("(L: %s, R: %s, url=\"%s\")", this.leftside, this.rightside, url);
    }
}

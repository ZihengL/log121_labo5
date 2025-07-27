package ets.log121_labo5;


import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;

import java.io.*;
import java.sql.Time;
import java.util.ArrayList;

/**
 * Class: Tools
 * Created on: 7/7/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class Tools {

    // SERIALIZING TOOLS

    public static void serialize(String filename, Serializable ...objects) throws RuntimeException {
        try {
            FileOutputStream outputStream = new FileOutputStream(filename + ".ser");
            ObjectOutputStream output = new ObjectOutputStream(outputStream);

            for (Serializable object : objects)
                output.writeObject(object);

            output.close();
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void serialize(Serializable ...objects) throws RuntimeException {
        for (int i = 0; i < objects.length; i++) {
            serialize(String.valueOf(i), objects[i]);
        }
    }

    public static Serializable deserialize(String filename) {
        try {
            FileInputStream inputStream = new FileInputStream(filename + ".ser");
            ObjectInputStream input = new ObjectInputStream(inputStream);

            Serializable serializables = (Serializable) input.readObject();

            input.close();
            inputStream.close();

            return serializables;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Point2D centerOf(Rectangle2D rectangle) {
        return new Point2D(rectangle.getWidth() / 2, rectangle.getHeight() / 2);
    }
}

package ets.log121_labo5;


import ets.log121_labo5.models.Perspective;
import ets.log121_labo5.models.CommandsManager;

import java.io.*;
import java.util.ArrayList;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/**
 * Class: Tester
 * Created on: 7/7/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class Tester {

    public static void serialPerspectiveTest() {
        Rectangle2D viewport = new Rectangle2D(10, 20, 100, 50);
        Rectangle2D bounds = new Rectangle2D(0, 0, 500, 500);
        Perspective perspective = new Perspective(viewport, bounds);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("object.ser"))) {
            out.writeObject(perspective);
            System.out.println("Serialized: " + perspective);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("object.ser"))) {
            Perspective deserialized = (Perspective) in.readObject();
            System.out.println("Deserialized: " + deserialized);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void serialImageTest() {
        String path = System.getProperty("user.dir") + "\\src\\main\\resources\\ets\\log121_labo5\\saves\\xp_background.png";
        Image image = new Image(path);

        System.out.println(image.getUrl());
    }

    public static void main(String[] args) {
        CommandsManager manager = CommandsManager.getInstance();

//        serialPerspectiveTest();

//        serialImageTest();

        ArrayList<String> list = new ArrayList<>();

        String item = "AA";
        list.add(item);

        String item2 = "BB";
        list.add(item2);

        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        System.out.println(list + " " + list.indexOf(item) + " " + list.indexOf(item2));

        System.out.println(list.size());
        list.remove(item);
        System.out.println(list.size());
//        System.out.println(list.indexOf(item2));
        list.remove(0);
        System.out.println(list.size());

//        Stack<String> stack = new Stack<>();
//        stack.add(item);
//        stack.add(item2);
//
//        System.out.println(stack);
//        stack.remove(stack.getFirst());
//        System.out.println(stack);
//        System.out.println(stack.indexOf(item2));
    }
}

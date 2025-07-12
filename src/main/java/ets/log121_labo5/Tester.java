package ets.log121_labo5;


import ets.log121_labo5.controllers.command.CommandsManager;
import ets.log121_labo5.models.Perspective;
import ets.log121_labo5.models.State;

import java.io.*;

import javafx.scene.image.Image;

import javax.swing.*;

/**
 * Class: Tester
 * Created on: 7/7/2025
 * Description:
 *
 * @author liuzi | Zi heng Liu
 */

public class Tester {

    public static void main(String[] args) {

//        Vector v = new Vector(2., 3.);
//
//        System.out.println(v);
//        v.translate(new Vector(3., 4.));
//        System.out.println(v);

        CommandsManager instance = CommandsManager.getInstance();
        State state = instance.getState();

        System.out.println(state);

//        instance.saveState("test.ser");

        try {
            System.out.println("FILECHOOSer");
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();

                FileInputStream fis = new FileInputStream(file);
                Image image = new Image(fis);

                instance.setImage(image);
//                instance.loadState(file);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        FileChooser fc = new FileChooser();
//        File file = fc.showOpenDialog(null);
//
//        if (file != null)
//            try {
//                Image image = new Image(new FileInputStream(file));
//                instance.setImage(image);
//            } catch (FileNotFoundException e) {
//                    throw new RuntimeException(e);
//                }

        Perspective perspective = new Perspective();
        perspective.setPosition(5., 12.);
        perspective.setZoom(1.42);
        System.out.println(perspective);

        Perspective perspective2 = new Perspective();
        perspective2.setPosition(4.7, 3.9);
        perspective2.setZoom(1.96);
        System.out.println(perspective2);

        // Serialization
//        Perspective[] perspectives = new Perspective[] { perspective, perspective2 };
//        System.out.println("SERIALIZE TEST");
//        // Serialization
        Tools.serialize("test123", perspective, perspective2);

        // Deserialization
//        Perspective[] deserialized = (Perspective[]) Tools.deserialize("test123");

//        System.out.println("\nDESERIALIZED");
//        for (Perspective p : deserialized)
//        System.out.println(deserialized);
    }
}

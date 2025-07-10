package ets.log121_labo5;


import ets.log121_labo5.models.Perspective;
import ets.log121_labo5.models.Vector;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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

        // Serializable
        System.out.println("BEFORE SERIALIZATION");



        Perspective perspective = new Perspective();
        perspective.setPosition(5., 12.);
        perspective.setZoom(1.42);
        System.out.println(perspective);

        Perspective perspective2 = new Perspective();
        perspective2.setPosition(4.7, 3.9);
        perspective2.setZoom(1.96);
        System.out.println(perspective2);

        // Serialization
        Perspective[] perspectives = new Perspective[] { perspective, perspective2 };
//        System.out.println("SERIALIZE TEST");
//        // Serialization
//        Tools.serialize("Pers", perspectives);

        // Deserialization
        Perspective deserialized = (Perspective) Tools.deserialize("Pers");

        System.out.println("\nDESERIALIZED");
//        for (Perspective p : deserialized)
        System.out.println(deserialized);
    }
}

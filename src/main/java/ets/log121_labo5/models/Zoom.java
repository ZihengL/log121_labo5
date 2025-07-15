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

public class Zoom implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Point2D position;
    private Rectangle2D bounds;

    public Zoom() {

    }
}

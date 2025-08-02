package ets.log121_labo5.controllers;


import ets.log121_labo5.models.CommandsManager;

/**
 * Class: ImageViewController
 * Created on: 7/12/2025
 * Description: Contrôleur pour l'Image qui ne peut pas être modifié
 * par une Perspective. Les mises à jours ne se font que lorsqu'on change l'Image.
 *
 * @author liuzi | Zi heng Liu
 */

public class ImageViewerController extends ImageController {

    public static final int FITWIDTH = 400;
    public static final int FITHEIGHT = 300;

    public boolean updateImage(CommandsManager manager) {
        if (!super.updateImage(manager)) return false;

        this.view.setFitWidth(FITWIDTH);
        this.view.setFitHeight(FITHEIGHT);

        return true;
    }
}

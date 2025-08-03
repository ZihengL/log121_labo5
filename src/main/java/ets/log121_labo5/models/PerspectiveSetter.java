package ets.log121_labo5.models;


/**
 * Class: PerspectiveSetter
 * Created on: 7/13/2025
 * Description: Interface fonctionelle pour stocker un mutateur de Perspective
 * invoquant le CommandsManager. Il nous permet de faire référence à la bonne
 * instance de Perspective avec facilité.
 *
 * @author liuzi | Zi heng Liu
 */

public interface PerspectiveSetter {

    public void setPerspective(Perspective perspective);
}

package com.beingchilling.model;

import com.beingchilling.controller.SporeController;
import com.beingchilling.view.SporeView;

/**
 * Az osztály a normál spóra megvalósítása.
 */
public class Spore implements SporeController, SporeView {
    /**
     * Eltárolja, mennyi tápanyagot tartalmaz egy spóra
     */
    protected final int sporeNutrient;
    /**
     * Konstruktor, amely beállítja a spóra tápanyagtartalmát.
     *
     * @param sporeNutrient A spóra által tartalmazott tápanyag mennyisége.
     */

    public Spore(int sporeNutrient) {
        this.sporeNutrient = sporeNutrient;
    }
    /**
     * Normál spóránál nem csinál semmit. Más (leszármazott) spórafajtáknak kell felüldefiniálni.
     *
     * @param r A rovar, amelyre a spóra hatással lehet.
     */
    public void sporeEffect(Insect r) {
    }
    /**
     * Visszaadja a spóra tápanyagtartalmát.
     *
     * @return A spóra által tartalmazott tápanyag mennyisége.
     */
    public int getSporeNutrient() {
        return sporeNutrient;
    }

    @Override
    public SporeView toView() {
        return this;
    }

    @Override
    public SporeController toController() {
        return this;
    }
    /**
     * Kimeneti nyelvvel megeggyező stringgé írja át az adott objectet.
     * @return A szöveg amit kikéne írni
     */
    @Override
    public String toString() {
        return "nutrient="+ sporeNutrient +"; type=N";
    }
}

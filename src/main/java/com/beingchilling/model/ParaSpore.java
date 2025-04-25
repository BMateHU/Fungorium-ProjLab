package com.beingchilling.model;

/**
 * Ez az osztály a bénító spóra megvalósítása.
 */
public class ParaSpore extends Spore {
    /**
     * Konstruktor, amely beállítja a spóra tápanyagtartalmát (Használva a Spore konstruktorát)..
     *
     * @param sporeNutrient A spóra által tartalmazott tápanyag mennyisége.
     */
    public ParaSpore(int sporeNutrient) {
        super(sporeNutrient);
    }
    /**
     * A bénító spóra által kifejtett hatás.
     * Megbénítja a rovart, amely elfogyasztja.
     *
     * @param r A rovar, amelyre a spóra hatással lehet.
     */
    @Override
    public void sporeEffect(Insect r) {
        r.paraEffect();
    }
    /**
     * Kimeneti nyelvvel megeggyező stringgé írja át az adott objectet.
     * @return A szöveg amit kikéne írni
     */
    public String toString() {
        return "Nutrient:"+ sporeNutrient +"; type:P";
    }
}
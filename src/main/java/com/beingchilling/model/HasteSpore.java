package com.beingchilling.model;

/**
 * Ez az osztály a gyorsító spóra megvalósítása.
 */
public class HasteSpore extends Spore {
    /**
     * Konstruktor, amely beállítja a spóra tápanyagtartalmát (Használva a Spore konstruktorát)..
     *
     * @param sporeNutrient A spóra által tartalmazott tápanyag mennyisége.
     */
    public HasteSpore(int sporeNutrient) {
        super(sporeNutrient);
    }
    /**
     * A Gyorsító spóra által kifejtett hatás.
     * Felgyorsítja a rovart, amely elfogyasztja.
     *
     * @param r A rovar, amelyre a spóra hatással lehet.
     */
    @Override
    public void sporeEffect(Insect r) {
        r.hasteEffect();
    }
    /**
     * Kimeneti nyelvvel megeggyező stringgé írja át az adott objectet.
     * @return A szöveg amit kikéne írni
     */
    @Override
    public String toString() {
        return "Nutrient:"+ sporeNutrient +"; type:H";
    }
}
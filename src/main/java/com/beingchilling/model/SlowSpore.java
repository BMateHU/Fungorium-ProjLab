package com.beingchilling.model;

/**
 * Ez az osztály a lassító spóra megvalósítása.
 */
public class SlowSpore extends Spore {
    /**
     * Konstruktor, amely beállítja a spóra tápanyagtartalmát (Használva a Spore konstruktorát)..
     *
     * @param sporeNutrient A spóra által tartalmazott tápanyag mennyisége.
     */
    public SlowSpore(int sporeNutrient) {
        super(sporeNutrient);
    }
    /**
     * A Lassító spóra által kifejtett hatás.
     * Lelassítja a rovart, amely elfogyasztja.
     *
     * @param r A rovar, amelyre a spóra hatással lehet.
     */
    @Override
    public void sporeEffect(Insect r) {
        r.slowEffect();
    }

    @Override
    public String toString() {
        return "Nutrient:"+ sporeNutrient +"; type:S";
    }
}
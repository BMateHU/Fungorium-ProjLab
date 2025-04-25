package com.beingchilling.model;

/**
 * Ez az osztály a némító spóra megvalósítása.
 */
public class MuteSpore extends Spore {
    /**
     * Konstruktor, amely beállítja a spóra tápanyagtartalmát (Használva a Spore konstruktorát)..
     *
     * @param sporeNutrient A spóra által tartalmazott tápanyag mennyisége.
     */
    public MuteSpore(int sporeNutrient) {
        super(sporeNutrient);
    }
    /**
     * A Némító spóra által kifejtett hatás.
     * Megnémítja a rovart, amely elfogyasztja.
     *
     * @param r A rovar, amelyre a spóra hatással lehet.
     */
    @Override
    public void sporeEffect(Insect r) {
        r.muteEffect();
    }
    /**
     * Kimeneti nyelvvel megeggyező stringgé írja át az adott objectet.
     * @return A szöveg amit kikéne írni
     */
    @Override
    public String toString() {
        return "Nutrient:"+ sporeNutrient +"; type:M";
    }
}

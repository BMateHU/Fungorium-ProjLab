package com.beingchilling.model;

public class CloneSpore extends Spore {
    /**
     * Konstruktor, amely beállítja a spóra tápanyagtartalmát.
     *
     * @param sporeNutrient A spóra által tartalmazott tápanyag mennyisége.
     */
    public CloneSpore(int sporeNutrient) {
        super(sporeNutrient);
    }

    /**
     * A klónozó effektust oldja meg
     * @param r A rovar, amelyre a spóra hatással lehet.
     */
    @Override
    public void sporeEffect(Insect r) {
        r.cloneEffect();
    }

    /**
     * Kimeneti nyelvvel megeggyező stringgé írja át az adott objectet.
     * @return A szöveg amit kikéne írni
     */
    @Override
    public String toString() {
        return "nutrient="+ sporeNutrient +"; type:C";
    }
}

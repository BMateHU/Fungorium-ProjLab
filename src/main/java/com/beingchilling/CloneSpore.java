package com.beingchilling;

public class CloneSpore extends Spore {
    /**
     * Konstruktor, amely beállítja a spóra tápanyagtartalmát.
     *
     * @param sporeNutrient A spóra által tartalmazott tápanyag mennyisége.
     */
    public CloneSpore(int sporeNutrient) {
        super(sporeNutrient);
    }

    @Override
    public void sporeEffect(Insect r) {
        r.cloneEffect();
    }
}

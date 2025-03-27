package com.beingchilling;

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
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">SlowSpore.sporeEffect()");
        r.slowEffect();
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;

    }
}
package com.beingchilling;

/**
 * Ez az osztály a gyorsító spóra megvalósítása.
 */
class HasteSpore extends Spore {
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
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">HasteSpore.sporeEffect()");
        r.hasteEffect();
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }
}
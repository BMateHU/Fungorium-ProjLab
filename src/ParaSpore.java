/**
 * Ez az osztály a bénító spóra megvalósítása.
 */
class ParaSpore extends Spore {
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
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">ParaSpore.sporeEffect()");
        r.paraEffect();
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;

    }
}
/**
 * Ez az osztály a némító spóra megvalósítása.
 */
class MuteSpore extends Spore {
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
        System.out.println(">MuteSpore.sporeEffect()");
        System.out.println("<");
    }
}

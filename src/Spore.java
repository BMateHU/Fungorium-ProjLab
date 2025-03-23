/**
 * Az osztály a normál spóra megvalósítása.
 */
public class Spore {
    /**
     * Eltárolja, mennyi tápanyagot tartalmaz egy spóra
     */
    private int sporeNutrient;
    /**
     * Konstruktor, amely beállítja a spóra tápanyagtartalmát.
     *
     * @param sporeNutrient A spóra által tartalmazott tápanyag mennyisége.
     */
    public Spore(int sporeNutrient) {
        this.sporeNutrient = sporeNutrient;
    }
    /**
     * Normál spóránál nem csinál semmit. Más (leszármazott) spórafajtáknak kell felüldefiniálni.
     *
     * @param r A rovar, amelyre a spóra hatással lehet.
     */
    public void sporeEffect(Insect r) {
        System.out.println(">Spore.sporeEffect()");
        System.out.println("<");
    }
    /**
     * Visszaadja a spóra tápanyagtartalmát.
     *
     * @return A spóra által tartalmazott tápanyag mennyisége.
     */
    public int getSporeNutrient() {
        System.out.println(">Spore.getSporeNutrient()");
        System.out.println("<");
        return sporeNutrient;
    }
}

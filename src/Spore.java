public class Spore {
    public int sporeNutrient;

    public Spore(int sporeNutrient) {
        this.sporeNutrient = sporeNutrient;
    }
    public void sporeEffect(Insect r) {
        System.out.println(">Spore.sporeEffect()");
        System.out.println("<");
    }
    public int getSporeNutrient() {
        System.out.println(">Spore.getSporeNutrient()");
        System.out.println("<");
        return sporeNutrient;
    }
}

public class Spore {
    private int sporeNutrient;
    public Spore(int sporeNutrient) {
        this.sporeNutrient = sporeNutrient;
    }
    public void sporeEffect(Insect r) {
        System.out.println("Spore effect");
    }
    public int getSporeNutrient() {
        return sporeNutrient;
    }
}

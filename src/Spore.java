public class Spore {
    public int sporeNutrient;

    public Spore(int sporeNutrient) {
        this.sporeNutrient = sporeNutrient;
    }
    public void sporeEffect(Insect r) {
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Spore.sporeEffect()");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }
    public int getSporeNutrient() {
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Spore.getSporeNutrient()");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;

        return sporeNutrient;

    }
}

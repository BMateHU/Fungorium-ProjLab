class HasteSpore extends Spore {
    public HasteSpore(int sporeNutrient) {
        super(sporeNutrient);
    }

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
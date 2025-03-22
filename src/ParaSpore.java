class ParaSpore extends Spore {
    public ParaSpore(int sporeNutrient) {
        super(sporeNutrient);
    }

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
class SlowSpore extends Spore {
    public SlowSpore(int sporeNutrient) {
        super(sporeNutrient);
    }

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
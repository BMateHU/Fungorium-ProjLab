class SlowSpore extends Spore {
    public SlowSpore(int sporeNutrient) {
        super(sporeNutrient);
    }

    @Override
    public void sporeEffect(Insect r) {
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">SlowSpore.sporeEffect()");
        Szkeleton.printIndentation();
        r.slowEffect();
        System.out.println("<");
        Szkeleton.indentation--;

    }
}
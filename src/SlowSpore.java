class SlowSpore extends Spore {
    public SlowSpore(int sporeNutrient) {
        super(sporeNutrient);
    }

    @Override
    public void sporeEffect(Insect r) {
        System.out.println(">SlowSpore.sporeEffect()");
        System.out.println("<");

    }
}
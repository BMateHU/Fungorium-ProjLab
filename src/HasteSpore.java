class HasteSpore extends Spore {
    public HasteSpore(int sporeNutrient) {
        super(sporeNutrient);
    }

    @Override
    public void sporeEffect(Insect r) {
        System.out.println(">HasteSpore.sporeEffect()");
        r.hasteEffect();
        System.out.println("<");
    }
}
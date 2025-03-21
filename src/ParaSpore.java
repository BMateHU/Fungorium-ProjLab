class ParaSpore extends Spore {
    public ParaSpore(int sporeNutrient) {
        super(sporeNutrient);
    }

    @Override
    public void sporeEffect(Insect r) {
        System.out.println("Para Spore effect.");
    }
}
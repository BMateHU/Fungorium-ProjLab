class MuteSpore extends Spore {
    public MuteSpore(int sporeNutrient) {
        super(sporeNutrient);
    }

    @Override
    public void sporeEffect(Insect r) {
        System.out.println(">MuteSpore.sporeEffect()");
        System.out.println("<");
    }
}

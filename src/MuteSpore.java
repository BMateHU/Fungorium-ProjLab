class MuteSpore extends Spore {
    public MuteSpore(int sporeNutrient) {
        super(sporeNutrient);
    }

    @Override
    public void sporeEffect(Insect r) {
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">MuteSpore.sporeEffect()");
        r.muteEffect();
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }
}

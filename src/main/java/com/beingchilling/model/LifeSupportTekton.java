package com.beingchilling.model;

public class LifeSupportTekton extends Tekton {
    public LifeSupportTekton() {}

    @Override
    public void absorb() {}

    @Override
    public boolean addThread(MushroomThread thread) {
        thread.setLifeSupport(true);
        return super.addThread(thread);
    }
}

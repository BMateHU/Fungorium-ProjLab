package com.beingchilling.model;

public class MultiThreadTekton extends Tekton{

    /**
     * Felülírja a tekton addThread() metódusát, hogy a tektonon több gombaFonalat lehet növeszteni.
     * @param mt, gombaFonal, ami a tektonhoz kiván növeszteni
     * @return True, mert többfonalasTekton
     */
    @Override
    public boolean addThread(MushroomThread mt){
        getThreads().add(mt);
        return true;
    }
}

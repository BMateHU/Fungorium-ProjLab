package com.beingchilling;

public class MultiThreadTekton extends Tekton{

    /**
     * Felülírja a tekton addThread() metódusát, hogy a tektonon több gombaFonalat lehet növeszteni.
     * @param mt, gombaFonal, ami a tektonhoz kiván növeszteni
     * @return True, mert többfonalasTekton
     */
    @Override
    public boolean addThread(MushroomThread mt){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.addThread(): Boolean");
        Szkeleton.printIndentation();

        mushroomThread.add(mt);

        System.out.println("<true");
        Szkeleton.indentation--;

        return true;
    }
}

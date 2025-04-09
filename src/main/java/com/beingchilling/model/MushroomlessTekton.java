package com.beingchilling.model;

public class MushroomlessTekton extends Tekton{

    /**
     * Felülírja a tekton growMushroomBody() metódusát, hogy a tektonon nem lehet gombaTestet növeszteni.
     * @param ms, gombaTest tulajdonosa, meghatározza, hogy melyik az ő gombaTestje
     * @return False, mert gombaTestnélküli Tekton
     */
    @Override 
    public boolean growMushroomBody(MushroomSpecies ms){
        System.out.println(">Tekton.growMushroomBody(): boolean");

        System.out.println("<result:false");

        return false;
    }
}

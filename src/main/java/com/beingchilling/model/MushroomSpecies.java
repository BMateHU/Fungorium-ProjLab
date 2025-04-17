package com.beingchilling.model;

import java.util.List;

/**
 * Az osztály egy gombafaj összes gombatest példányát tárolja el.
 */
public class MushroomSpecies {
    /**
     * Nyilvántartja, egy gombafaj összes létező egyedét.
     */
    private List<MushroomBody> mushroomBodies;

    /**
     * Lekéri a Gombatest listát
     * @return Visszaadja az összes létező példányt.
     */
    public List<MushroomBody> checkMushroomBody(){
        return mushroomBodies;
    }

    /**
     * Felvesz egy új gombatestet
     * @param mushroomBody gombatest amit fel akarunk vanni
     */
    public void addMushroomBody(MushroomBody mushroomBody){

    }

    public void setMushroomBodies(List<MushroomBody> mushroomBodies) {
        this.mushroomBodies = mushroomBodies;
    }
}

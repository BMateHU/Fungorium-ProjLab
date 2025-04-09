package com.beingchilling.model;

import com.beingchilling.controller.MushroomSpeciesController;
import com.beingchilling.view.MushroomSpeciesView;

import java.util.List;

/**
 * Az osztály egy gombafaj összes gombatest példányát tárolja el.
 */
public class MushroomSpecies implements MushroomSpeciesController, MushroomSpeciesView {
    /**
     * Nyilvántartja, egy gombafaj összes létező egyedét.
     */
    private List<MushroomBody> mushroomBodies;

    /**
     * Lekéri a Gombatest listát
     * @return Visszaadja az összes létező példányt.
     */
    public List<MushroomBody> checkMushroomBody(){
        System.out.println(">MushroomSpecies.checkMushroomBody():List<MushroomBody>");
        System.out.println("<mushruooms:List<MushroomBody>");

        return mushroomBodies;
    }

    /**
     * Felvesz egy új gombatestet
     * @param mushroomBody gombatest amit fel akarunk vanni
     */
    public void addMushroomBody(MushroomBody mushroomBody){

        System.out.println(">MushroomSpecies.addMushroomBody(MushroomBody mushroomBody):void");

        System.out.println("<");

    }

    public List<MushroomBody> getMushroomBodies() {
        return mushroomBodies;
    }

    public void setMushroomBodies(List<MushroomBody> mushroomBodies) {
        this.mushroomBodies = mushroomBodies;
    }
}

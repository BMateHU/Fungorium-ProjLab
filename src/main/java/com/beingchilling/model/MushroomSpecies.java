package com.beingchilling.model;

import com.beingchilling.game.GameModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Az osztály egy gombafaj összes gombatest példányát tárolja el.
 */
public class MushroomSpecies {
    /**
     * Nyilvántartja, egy gombafaj összes létező egyedét.
     */
    private List<MushroomBody> mushroomBodies = new ArrayList<>();

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
        mushroomBodies.add(mushroomBody);
    }

    public void setMushroomBodies(List<MushroomBody> mushroomBodies) {
        this.mushroomBodies = mushroomBodies;
    }

    /**
     * Kimeneti nyelvvel megeggyező stringgé írja át az adott objectet.
     * @return A szöveg amit kikéne írni
     */
    @Override
    public String toString() {
        if(mushroomBodies.isEmpty())
            return "type=G";

        StringBuilder own = new StringBuilder("own=");
        for(MushroomBody mushroomBody : mushroomBodies){
            own.append(GameModel.gameObjects.getK(mushroomBody)).append(", ");
        }
        own.delete(own.length()-2, own.length());
        return "type=G; " + own;
    }
}

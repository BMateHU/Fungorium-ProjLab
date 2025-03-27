package com.beingchilling;

import java.util.List;

/**
 * Az osztály egy gombafaj összes gombatest példányát tárolja el.
 */
public class MushroomSpecies {
    /**
     * Nyilvántartja, egy gombafaj összes létező egyedét.
     */
    public List<MushroomBody> mushroomBodies;

    /**
     * Lekéri a Gombatest listát
     * @return Visszaadja az összes létező példányt.
     */
    public List<MushroomBody> checkMushroomBody(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">MushroomSpecies.checkMushroomBody():List<MushroomBody>");
        Szkeleton.printIndentation();
        System.out.println("<mushruooms:List<MushroomBody>");
        Szkeleton.indentation--;

        return mushroomBodies;
    }

    /**
     * Felvesz egy új gombatestet
     * @param mushroomBody gombatest amit fel akarunk vanni
     */
    public void addMushroomBody(MushroomBody mushroomBody){
        Szkeleton.indentation++;

        Szkeleton.printIndentation();

        System.out.println(">MushroomSpecies.addMushroomBody(MushroomBody mushroomBody):void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }

}

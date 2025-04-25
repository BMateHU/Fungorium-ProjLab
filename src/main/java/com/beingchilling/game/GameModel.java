package com.beingchilling.game;

import com.beingchilling.model.Insect;
import com.beingchilling.model.InsectSpecies;
import com.beingchilling.model.MushroomBody;
import com.beingchilling.model.MushroomSpecies;
import java.util.HashMap;

/**
 * Ez a játékban lévő Objekteket tárolja
 */
public class GameModel {
    private GameModel() {}

    /**
     * A teszteléshez való randomizátor ki és bekapcsolása
     */
    public static boolean randomSwitch = true;
    /**
     * A térkép
     */
    public static Map map = new Map();
    /**
     * Gombászokat tároló HashMap
     */
    public static HashMap<MushroomBody, MushroomSpecies> gombasz = new HashMap<>();
    /**
     * Rovarászokat tároló HashMap
     */
    public static HashMap<Insect, InsectSpecies> rovarasz = new HashMap<>();
    /**
     *  Összes object ami a játékban van
     */
    public static BiMap<String, Object> gameObjects = new BiMap<>();
}
package com.beingchilling.game;

import com.beingchilling.model.Insect;
import com.beingchilling.model.InsectSpecies;
import com.beingchilling.model.MushroomBody;
import com.beingchilling.model.MushroomSpecies;
import java.util.HashMap;


public class GameModel {
    private GameModel() {}

    public static Map map = new Map();
    public static HashMap<MushroomBody, MushroomSpecies> gombasz = new HashMap<>();
    public static HashMap<Insect, InsectSpecies> rovarasz = new HashMap<>();

    public static BiMap<String, Object> gameObjects = new BiMap<>();
}
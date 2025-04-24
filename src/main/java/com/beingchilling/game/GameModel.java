package com.beingchilling.game;

import com.beingchilling.model.InsectSpecies;
import com.beingchilling.model.MushroomSpecies;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GameModel {

    private GameModel() {}

    public static boolean randomSwitch = true;
    public static Map map = new Map();
    public static HashMap<String, MushroomSpecies> gombasz = new HashMap<>();
    public static HashMap<String, InsectSpecies> rovarasz = new HashMap<>();
    public static BiMap<String, Object> gameObjects = new BiMap<>();

}

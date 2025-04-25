package com.beingchilling.model;

import com.beingchilling.game.GameModel;

import java.util.ArrayList;
import java.util.List;

public class InsectSpecies {
    /**
     * Ebben tárolja a rovarokat
     */
    private final List<Insect> insects;

    public InsectSpecies() {
        insects = new ArrayList<>();
    }

    /**
     * Hozzáadja a rovarat a Listához
     * @param insect
     */
    public void addInsect(Insect insect) {
        insects.add(insect);
    }

    public void remove(Insect insect) {
        insects.remove(insect);
    }

    public List<Insect> getInsects() {
        return insects;
    }
    /**
     * Kimeneti nyelvvel megeggyező stringgé írja át az adott objectet.
     * @return A szöveg amit kikéne írni
     */
    @Override
    public String toString() {
        if(insects.isEmpty())
            return "type=R";

        StringBuilder own = new StringBuilder("own=");
        for(Insect insect : insects){
            own.append(GameModel.gameObjects.getK(insect)).append(", ");
        }
        own.delete(own.length()-2, own.length());
        return "type=R; " + own;
    }
}

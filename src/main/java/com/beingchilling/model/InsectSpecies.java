package com.beingchilling.model;

import com.beingchilling.game.GameModel;

import java.util.ArrayList;
import java.util.List;

public class InsectSpecies {

    private final List<Insect> insects;

    public InsectSpecies() {
        insects = new ArrayList<>();
    }

    public void addInsect(Insect insect) {
        insects.add(insect);
    }

    public void remove(Insect insect) {
        insects.remove(insect);
    }

    public List<Insect> getInsects() {
        return insects;
    }

    @Override
    public String toString() {
        if(insects.isEmpty())
            return "Type=R";

        StringBuilder own = new StringBuilder("Own=");
        for(Insect insect : insects){
            own.append(GameModel.gameObjects.getK(insect)).append(", ");
        }
        own.delete(own.length()-2, own.length());
        return "Type=R; " + own;
    }
}

package com.beingchilling.controller;

import com.beingchilling.model.Insect;
import com.beingchilling.model.MushroomSpecies;
import com.beingchilling.model.Tekton;

import java.util.List;

public interface TektonController {
    boolean tektonBreak();
    boolean growMushroomBody(MushroomSpecies ms);
    void updateNeighbor(List<Tekton> newAdd, List<Tekton> delete);
    void addInsect(Insect i);
    void removeInsect();
    void absorb();
    void useSporeToGrow();

}

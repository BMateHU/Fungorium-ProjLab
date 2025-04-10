package com.beingchilling.controller;

import com.beingchilling.model.*;
import com.beingchilling.view.TektonView;

import java.util.List;

public interface TektonController {
    boolean tektonBreak();
    Spore popSpore();
    boolean growMushroomBody(MushroomSpecies ms);
    boolean growMushroomBody(MushroomBody MB);
    boolean checkNeighbor(Tekton t);
    void updateNeighbor(List<Tekton> newAdd, List<Tekton> delete);
    void addNeighbor(Tekton t);
    void deleteNeighbor(Tekton t);
    void addInsect(Insect i);
    void removeInsect();
    void absorb();

    TektonView toView();
}

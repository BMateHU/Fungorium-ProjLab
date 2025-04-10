package com.beingchilling.view;

import com.beingchilling.controller.TektonController;
import com.beingchilling.model.*;

import java.util.List;

public interface TektonView {
    List<Tekton> getNeighborWithThread();
    List<Tekton> getNeighbors();
    List<Spore> getSpores();
    Insect getInsect();
    List<MushroomThread> getThreads();
    MushroomBody getBody();

    TektonController toController();
}

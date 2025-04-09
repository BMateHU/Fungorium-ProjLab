package com.beingchilling.controller;

import com.beingchilling.model.MushroomThread;
import com.beingchilling.model.Tekton;

public interface InsectController {
    boolean insectMove(Tekton tek);
    void insectEat();
    boolean insectCut(MushroomThread mt);
    void hasteEffect();
    void slowEffect();
    void paraEffect();
    void muteEffect();
    void cloneEffect();
    void endEffect();
    void setLocation(Tekton location);
    void destroy();
}

package com.beingchilling.controller;

import com.beingchilling.model.MushroomThread;
import com.beingchilling.model.Tekton;
import com.beingchilling.view.InsectView;

public interface InsectController {
    boolean insectMove(Tekton tek);
    void insectEat() throws ArrayIndexOutOfBoundsException;
    boolean insectCut(MushroomThread mt);
    void hasteEffect();
    void slowEffect();
    void paraEffect();
    void muteEffect();
    void cloneEffect();
    void endEffect();
    void setLocation(Tekton location);
    void destroy();

    InsectView toView();
}

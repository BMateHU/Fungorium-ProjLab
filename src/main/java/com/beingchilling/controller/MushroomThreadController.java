package com.beingchilling.controller;

import com.beingchilling.model.MushroomBody;
import com.beingchilling.model.MushroomThread;
import com.beingchilling.model.Tekton;
import com.beingchilling.view.MushroomThreadView;

public interface MushroomThreadController {
    void setLifeSupport(boolean lifeSupport);
    void speedUpGrowing();
    void addThread(MushroomThread thread);
    MushroomBody checkOwner();
    void disconnectThread();
    void lifeReduce();
    void absorbInsect();
    void destroy();
    void remove(MushroomThread mt);
    void setLocation(Tekton location);
    void growThread(Tekton source, Tekton target);


    MushroomThreadView toView();
}

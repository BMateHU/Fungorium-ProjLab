package com.beingchilling.view;

import com.beingchilling.controller.MushroomThreadController;
import com.beingchilling.model.MushroomBody;
import com.beingchilling.model.MushroomThread;
import com.beingchilling.model.Tekton;

import java.util.List;

public interface MushroomThreadView {
    int getLife();
    boolean isLifeSupport();
    MushroomBody checkOwner();
    MushroomThread getPreGrowed();
    List<MushroomThread> getNextGrowed();
    Tekton getLocation();

    MushroomThreadController toController();
}

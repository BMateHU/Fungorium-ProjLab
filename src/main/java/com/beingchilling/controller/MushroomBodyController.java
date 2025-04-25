package com.beingchilling.controller;

import com.beingchilling.model.MushroomThread;
import com.beingchilling.model.Spore;
import com.beingchilling.model.Tekton;
import com.beingchilling.view.MushroomBodyView;

public interface MushroomBodyController {
    boolean spreadSpore(Tekton tekton, Spore toSpread);
    boolean growThread(MushroomThread mushroomThread, Tekton tekton);
    void setBodyAge(int bodyAge);
    void setSporeNumber(int sporeNum);
    MushroomBodyView toView();
}

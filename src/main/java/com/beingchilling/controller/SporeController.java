package com.beingchilling.controller;

import com.beingchilling.model.Insect;
import com.beingchilling.view.SporeView;

public interface SporeController {
    void sporeEffect(Insect r);
    int getSporeNutrient();

    SporeView toView();
}

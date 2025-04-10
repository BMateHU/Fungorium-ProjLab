package com.beingchilling.view;

import com.beingchilling.controller.MushroomBodyController;
import com.beingchilling.model.Tekton;

public interface MushroomBodyView {
    int getBodyAge();
    int getSporeNumber();
    Tekton getLocation();

    MushroomBodyController toController();
}

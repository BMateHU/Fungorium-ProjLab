package com.beingchilling.view;

import com.beingchilling.controller.InsectController;
import com.beingchilling.model.Insect;
import com.beingchilling.model.Tekton;

import java.util.List;

public interface InsectView {
    List<Tekton> getReachableTekton(int speed);
    int getInsectSpeed();
    boolean canCutThread();
    boolean canEatSpore();
    int getCurrentNutrient();
    Tekton getLocation();

    InsectController toController();
}

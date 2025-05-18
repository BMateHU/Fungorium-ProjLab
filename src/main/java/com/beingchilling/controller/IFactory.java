package com.beingchilling.controller;

import com.beingchilling.model.*;
import com.beingchilling.view.TektonView;

public interface IFactory {
    void onCreationTekton(Tekton t);
    void onCreationMushroomBody(MushroomBody mb);
    void onCreationSpore(Spore sp, TektonView t);
    void onCreationInsect(Insect ins);
}

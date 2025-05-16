package com.beingchilling.controller;

import com.beingchilling.model.*;

public interface IFactory {
    void onCreationTekton(Tekton t);
    void onCreationMushroomBody(MushroomBody mb);
    void onCreationSpore(Spore sp);
    void onCreationInsect(Insect ins);
    void onCreationMushroomThread(MushroomThread mt);
}

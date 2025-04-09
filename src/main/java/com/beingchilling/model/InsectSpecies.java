package com.beingchilling.model;

import com.beingchilling.controller.InsectSpeciesController;
import com.beingchilling.view.InsectSpeciesView;

import java.util.List;

public class InsectSpecies implements InsectSpeciesController, InsectSpeciesView {

    private List<Insect> insects;

    public InsectSpecies() {}

    public void addInsect(Insect insect) {
        insects.add(insect);
    }

    public void remove(Insect insect) {
        insects.remove(insect);
    }

    public List<Insect> getInsects() {
        return insects;
    }
}

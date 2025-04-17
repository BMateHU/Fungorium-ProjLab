package com.beingchilling.model;

import java.util.ArrayList;
import java.util.List;

public class InsectSpecies {

    private final List<Insect> insects;

    public InsectSpecies() {
        insects = new ArrayList<>();
    }

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

package com.beingchilling;

import java.util.List;

public class InsectSpecies {

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

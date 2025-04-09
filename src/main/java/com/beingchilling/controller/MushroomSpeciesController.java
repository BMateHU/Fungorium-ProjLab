package com.beingchilling.controller;

import com.beingchilling.model.MushroomBody;

import java.util.List;

public interface MushroomSpeciesController {
    void addMushroomBody(MushroomBody mushroomBody);
    void setMushroomBodies(List<MushroomBody> mushroomBodies);
}

package com.beingchilling.model;

import java.util.ArrayList;
import java.util.List;

public class ThreadAbsorbTekton extends Tekton {

    @Override
    public void absorb() {
        List<MushroomThread> temp = new ArrayList<>();
        for(MushroomThread mt : mushroomThread) {
            mt.lifeReduce();
            if(mt.getLife() <= 0)
                temp.add(mt);
        }
        for(MushroomThread mt : temp) {
            mushroomThread.remove(mt);
        }
    }
}

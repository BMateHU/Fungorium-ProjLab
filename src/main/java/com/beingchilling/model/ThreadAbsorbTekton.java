package com.beingchilling.model;

import com.beingchilling.game.GameModel;

import java.util.ArrayList;
import java.util.List;

public class ThreadAbsorbTekton extends Tekton {

    @Override
    public void absorb() {
        List<MushroomThread> temp = new ArrayList<>();
        for (MushroomThread mt : getThreads()) {
            mt.lifeReduce();
            if (mt.getLife() <= 0)
                temp.add(mt);
        }
        for (MushroomThread mt : temp) {
            getThreads().remove(mt);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("type=TA");

        if (mushroomBody != null) {
            sb.append("; mushroom=").append(GameModel.gameObjects.getK(mushroomBody));
        }

        if (insect != null) {
            sb.append("; insect=").append(GameModel.gameObjects.getK(insect));
        }

        if (!this.mushroomThread.isEmpty()) {
            sb.append("; threads=");
            for (MushroomThread thread : this.mushroomThread) {
                sb.append(GameModel.gameObjects.getK(thread)).append(", ");
            }
            sb.delete(sb.length()-2, sb.length());
        }

        if (!this.spore.isEmpty()) {
            sb.append("; spores=");
            for (Spore s : this.spore) {
                sb.append(GameModel.gameObjects.getK(s)).append(", ");
            }
            sb.delete(sb.length()-2, sb.length());
        }

        if (!this.neighbors.isEmpty()) {
            sb.append("; neighbours=");
            for (Tekton neighbor : this.neighbors) {
                sb.append(GameModel.gameObjects.getK(neighbor)).append(", ");
            }
            sb.delete(sb.length()-2, sb.length());
        }

        return sb.toString();
    }
}

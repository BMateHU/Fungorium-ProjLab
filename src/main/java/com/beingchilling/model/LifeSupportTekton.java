package com.beingchilling.model;

import com.beingchilling.game.GameModel;

public class LifeSupportTekton extends Tekton {
    public LifeSupportTekton() {}

    @Override
    public void absorb() {}

    @Override
    public boolean addThread(MushroomThread thread) {
        thread.setLifeSupport(true);
        return super.addThread(thread);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("type=LS");

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

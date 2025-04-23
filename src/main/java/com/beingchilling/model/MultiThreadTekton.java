package com.beingchilling.model;

import com.beingchilling.game.GameModel;

public class MultiThreadTekton extends Tekton{

    /**
     * Felülírja a tekton addThread() metódusát, hogy a tektonon több gombaFonalat lehet növeszteni.
     * @param mt, gombaFonal, ami a tektonhoz kiván növeszteni
     * @return True, mert többfonalasTekton
     */
    @Override
    public boolean addThread(MushroomThread mt){
        getThreads().add(mt);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("type=MT");

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

package com.beingchilling.model;

import com.beingchilling.controller.MushroomBodyController;
import com.beingchilling.game.GameModel;
import com.beingchilling.view.MushroomBodyView;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Ez egy gombatest osztaly, ami tárolja a gombatest korát, sporaszamát, és melyik tektonon van
 */
public class MushroomBody implements MushroomBodyController, MushroomBodyView {
    /**
     * A gomba osztálynak a korra, pontosabban, hogy hány kört telt el a gomba létrehozása után.
     */
    private int bodyAge;
    /**
     * Hány spóra szám van a Gombában
     */
    private int sporeNumber;
    /**
     * Gombatest melyik tektonon van
     */
    private Tekton location;


    public MushroomBody(Tekton location) {
        bodyAge = 0;
        sporeNumber = 5;
        this.location = location;
    }

    /**
     * Spórát szór egy adott tektonra.
     * @param tekton melyik tentonra akarunk spórát szórni.
     * @return lehet vagy nem lehet
     */
    public boolean spreadSpore(Tekton tekton, Spore toSpread) {
        if(tekton.checkNeighbor(location) && bodyAge >= 2 && sporeNumber > 0) {
            popSpore();
            tekton.addSpore(toSpread);
            return true;
        }
        return false;
    }

    /**
     *  Egy adott tektonra növeszt gombafonalat egy gombatest, attól függően,
     *  hogy van-e a tekton szomszédján ugyanannak a gombatestnek fonala.
     *  Ha nem a gombafonal végét növeszti, akkor egy új ágat hoz létre arra a tektonra.
     * @param mushroomThread a hosszabítani kívánt fonal
     * @param tekton a Tekton ahová szeretnénk fonalat
     * @return sikeresség
     */
    public boolean growThread(MushroomThread mushroomThread, Tekton tekton) {
        if (tekton.checkNeighbor(mushroomThread.getLocation())) {
            //ennel beadja a threadet
            if (mushroomThread.growThread(tekton)) {

                if (!tekton.getSpores().isEmpty() && GameModel.randomSwitch) {
                    Random random = new Random();
                    // osszes neighbourt kikeresi
                    List<Tekton> possibleTargets = tekton.getNeighbors();

                    //a sajat tektont es a kijelolt tektont kizarja
                    List<Tekton> validTargets = possibleTargets.stream()
                            .filter(t -> (t != this.location || t != tekton))
                            .toList();

                    if (!validTargets.isEmpty()) {
                        //while ciklus vizsgalas
                        boolean growsucsess = false;
                        while (!growsucsess) {
                            int randomIndex = random.nextInt(validTargets.size());
                            Tekton randomNeighbor = validTargets.get(randomIndex);
                            if (randomNeighbor.checkNeighbor(tekton)) {
                                if (mushroomThread.growThread(randomNeighbor)) {
                                    growsucsess = true;
                                }
                            }

                        }
                    }
                }
                //ha ki van kapcsolva a random
                else if (!tekton.getSpores().isEmpty() && !GameModel.randomSwitch) {
                    if (!tekton.getSpores().isEmpty()) {
                        for (Tekton t : tekton.getNeighbors()) {
                            //listaban az elsore
                            if (t != location && mushroomThread.getNextGrowed().getLast().growThread(t)) {
                                break;
                            }
                        }
                    }
                }
                return true;
            } else {
                return false;
            }
        } else if (tekton.checkNeighbor(location)) {
            return mushroomThread.growThread(location);
        }
        return false;
    }

    /**
     *  Csökkenti a spórának számát eggyel.
     */
    private void popSpore(){
            sporeNumber--;
    }

    public int getBodyAge() {
        return bodyAge;
    }

    public void setBodyAge(int bodyAge) {
        this.bodyAge = bodyAge;
    }

    public void setSporeNumber(int sporeNum) { this.sporeNumber = sporeNum; }

    public int getSporeNumber() {
        return sporeNumber;
    }

    public Tekton getLocation() {
        return location;
    }

    @Override
    public MushroomBodyController toController() {
        return this;
    }

    @Override
    public MushroomBodyView toView() {
        return this;
    }

    /**
     * Kimeneti nyelvvel megeggyező stringgé írja át az adott objectet.
     * @return A szöveg amit kikéne írni
     */
    @Override
    public String toString() {
        return "Age="+bodyAge+"; spore="+sporeNumber+"; tekton="+ GameModel.gameObjects.getK(location);
    }
}

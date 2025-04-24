package com.beingchilling.model;

import com.beingchilling.controller.MushroomBodyController;
import com.beingchilling.game.GameModel;
import com.beingchilling.view.MushroomBodyView;

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
    public boolean spreadSpore(Tekton tekton) {
        if(tekton.checkNeighbor(location) && bodyAge >= 2 && sporeNumber > 0) {
            popSpore();
            Spore S = new Spore(3);
            tekton.addSpore(S);
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
        if(tekton.checkNeighbor(mushroomThread.getLocation()) ) {
            MushroomThread MT2 = new MushroomThread();
            if (tekton.addThread(MT2)) {
                if(!tekton.getSpores().isEmpty()){
                    //nincs random meg EZT MINDENKEPP
                    for(Tekton t : tekton.getNeighbors()) {
                        if(t != location) {
                            MushroomThread MT3 = new MushroomThread();
                            t.addThread(MT3);
                        }
                    }
                }
                return true;
            }
            else{
                return false;
            }
        }
        else if(tekton.checkNeighbor(location)) {
            MushroomThread MT2 = new MushroomThread();
            mushroomThread.addThread(MT2);
            return tekton.addThread(MT2);
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

    @Override
    public String toString() {
        return "Age="+bodyAge+"; Spore="+sporeNumber+"; tekton="+ GameModel.gameObjects.getK(location);
    }
}

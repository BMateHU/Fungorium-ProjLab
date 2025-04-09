package com.beingchilling.model;

import com.beingchilling.controller.MushroomBodyController;
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
        
        System.out.println(">MushroomBody.spreadSpore(Tekton tekton):boolean)");
        if(tekton.checkNeighbor(location) && bodyAge >= 2 && sporeNumber > 0) {
            popSpore();
            Spore S = new Spore(1);
            tekton.addSpore(S);
            
            System.out.println("<result:true");
            
            return true;
        }
        
        System.out.println("<result:false");
        
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
        
        System.out.println(">MushroomBody.growThread(MushroomThread mushroomThread, Tekton tekton):boolean");
        if(tekton.checkNeighbor(mushroomThread.getLocation()) && mushroomThread != null) {
            MushroomThread MT2 = new MushroomThread();
            if (tekton.addThread(MT2)) {
                if(!tekton.spore.isEmpty()){
                    //nincs random meg
                    for(Tekton t : tekton.neighbors) {
                        if(t != location) {
                            MushroomThread MT3 = new MushroomThread();
                            t.addThread(MT3);
                        }
                    }
                }
                

                System.out.println("<result:true");
                

                return true;
            }
            else{
                

                System.out.println("<result:false");
                

                return false;
            }
        }
        else if(tekton.checkNeighbor(location) && mushroomThread == null) {
            MushroomThread MT2 = new MushroomThread();
            mushroomThread.addThread(MT2);
            return tekton.addThread(MT2);
        }
        

        System.out.println("<result:false");
        

        return false;
    }

    /**
     *  Csökkenti a spórának számát eggyel.
     */
    public void popSpore(){
        
        System.out.println(">MushroomBody.popSpore():void");
        

        System.out.println("<");
        


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
}

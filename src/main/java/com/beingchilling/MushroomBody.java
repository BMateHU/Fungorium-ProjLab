package com.beingchilling;

/**
 * Ez egy gombatest osztaly, ami tárolja a gombatest korát, sporaszamát, és melyik tektonon van
 */
public class MushroomBody {
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
    public MushroomBody() {
        bodyAge = 0;
        sporeNumber = 5;
    }

    /**
     * Spórát szór egy adott tektonra.
     * @param tekton melyik tentonra akarunk spórát szórni.
     * @return lehet vagy nem lehet
     */
    public boolean spreadSpore(Tekton tekton) {
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">MushroomBody.spreadSpore(Tekton tekton):boolean)");
        if(tekton.checkNeighbor(location) && bodyAge >= 2 && sporeNumber > 0) {
            popSpore();
            Spore S = new Spore(1);
            tekton.addSpore(S);
            Szkeleton.printIndentation();
            System.out.println("<result:true");
            Szkeleton.indentation--;
            return true;
        }
        Szkeleton.printIndentation();
        System.out.println("<result:false");
        Szkeleton.indentation--;
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
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
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
                Szkeleton.printIndentation();

                System.out.println("<result:true");
                Szkeleton.indentation--;

                return true;
            }
            else{
                Szkeleton.printIndentation();

                System.out.println("<result:false");
                Szkeleton.indentation--;

                return false;
            }
        }
        else if(tekton.checkNeighbor(location) && mushroomThread == null) {
            MushroomThread MT2 = new MushroomThread();
            mushroomThread.addThread(MT2);
            MT2.setPreGrowed(mushroomThread);
            if(tekton.addThread(MT2)) {
                return true;
            }
            else {
                return false;
            }
        }
        Szkeleton.printIndentation();

        System.out.println("<result:false");
        Szkeleton.indentation--;

        return false;
    }

    /**
     *  Csökkenti a spórának számát eggyel.
     */
    public void popSpore(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">MushroomBody.popSpore():void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;


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

    public void setSporeNumber(int sporeNumber) {
        this.sporeNumber = sporeNumber;
    }

    public Tekton getLocation() {
        return location;
    }

    public void setLocation(Tekton location) {
        this.location = location;
    }
}

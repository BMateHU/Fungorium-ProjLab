package com.beingchilling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Ez egy Tekton osztaly, ami tárolja a Tektonnak a szomszédjait egy Listában
 * , Spórákat, szintén egy Listában, ami rajta van a Tektonon és MushroomThreadet,
 *  illetve egyetlen egy darab Insectet is tárol.
 */
public class Tekton {
    /**
     * A Tekton osztálynak a szomszédjai, ami Listában tároljuk.
     */
    public List<Tekton> neighbors;
    /**
     * A Tektonon lévő spórák.
     */
    public List<Spore> spore;

    /// A Tektonon lévő gombatest.
    public MushroomBody mushroomBody;
    /**
     * A Tektonon lévő MushroomThread, azaz gombaFonal.
     */
    public List<MushroomThread> mushroomThread;
    /**
     * A Tektonon lévő rovar, ami csak egy darab lehet a tektonon.
     */
    public Insect insect;

    //Konstruktor
    public Tekton(){
        neighbors = new ArrayList<>();
        spore = new ArrayList<>();
        mushroomThread = new ArrayList<>();
        insect = null;
    }
    /**
     * A GombaFonal növeszthető-e a Tektonon. 
     * @param mt, kapott paraméterként gombaFonal lehet-e tektonra növeszteni.
     * @return Sikeresség 
     */
    public boolean addThread(MushroomThread mt){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.addThread(): Boolean");
        if(!mushroomThread.isEmpty()){
            Szkeleton.printIndentation();
            System.out.println("<false");
            Szkeleton.indentation--;
            return false;
        }
        Szkeleton.printIndentation();

        mushroomThread.add(mt);

        System.out.println("<true");
        Szkeleton.indentation--;

        return true;
    }
    /**
     * Tekton kettétörese, ha a Tektonon van Gombafonal és Spóra akkor azokat eltávolítjuk a Tektonról
     * Létre hozzuk egy új Tektont és ezt az új létre hozott tekton hozzáadjuk a 
     * jelenlegi Tekton neighborhozÉs az új létre hozott Tektonnak is hozzáadjuk a 
     * jelenlegi Tektont mint neighborként
     */
   public boolean tektonBreak(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.tektonBreak(): void");
        if(insect != null || mushroomThread.isEmpty()){
            Szkeleton.printIndentation();

            System.out.println("<");
            Szkeleton.indentation--;
            return false;
        }

        clearSpore();
        Tekton T2 = new Tekton();
        T2.neighbors.add(this);
        for(Tekton t : neighbors){
            t.updateNeighbor(null,null);
        }
        List<Tekton> L = new LinkedList<Tekton>();
        L.add(T2);
        updateNeighbor(L,null);
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;
        return true;
   }

    /**
     * A függvény megmondja a Tektonon lévő spóra
     * PL: Gombatest nélküli vagy Gombafonal nélküli
     * @return vissza adja a Sporát
     */
    public Spore popSpore(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.PopSpore(): Spore");
        Szkeleton.printIndentation();

        System.out.println("<Spore:spore");
        Szkeleton.indentation--;

        return spore.removeFirst();
    }
    
    /**
     * A Tektonon lévő spórák eltávolítása illetve megsemmisitése 
     */
    public void clearSpore(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.clearSpore(): void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }

    /**
     * A GombaTest növeszthető-e a Tektonon. A GombaTestet növesztéshez,
     * Szükséges három darab spóra és rendelkezik gombaFonal a Tektonon.
     * @param ms tárolja a létre hozott gombaTestet
     * @return gombaTestnek a növesztés sikerességét
     */
    public boolean growMushroomBody(MushroomSpecies ms){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.growMushroomBody(): boolean");
        if(spore.size()>=3 && !mushroomThread.isEmpty()) {
            useSporeToGrow();
            MushroomBody M = new MushroomBody();
            M.setLocation(this);
            ms.addMushroomBody(M);
            mushroomBody = M;
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
     * A függvény visszaadja egy List<Tekton> típusú értéket, ami megmondja, 
     * hogy milyen fonalasTektonnal van szomszédban.
     * @return visszaadja szomszédos FonalasTektonokat 
     */
    
    public List<Tekton> getNeighborWithThread(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.getNeighborWithThread(): List<Tekton>");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
        List<Tekton> temp = new LinkedList<Tekton>();
        for(Tekton t : neighbors){
            for(MushroomThread mt : mushroomThread) {
                for(MushroomThread mt2 : mt.getNextGrowed()) {
                    if(mt2.getLocation() == t) {
                        temp.add(t);
                    }
                }
            }
        }
        return temp;
    }

    /**
     * A függvény eldönti, hogy a kapott paraméterként Tekton szomszédba van-e a jelenlegi Tektonnal
     * @param t, ami vizsgálja, hogy szomszédba van-e
     * @return igen, ha szomszédba van, hamis, ha nincs szomszédban
     */
    
    public boolean checkNeighbor(Tekton t){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.checkNeighbor(): boolean");
        if(neighbors.contains(t))
        {
            Szkeleton.printIndentation();
            System.out.println("<result:true");
            Szkeleton.indentation--;
            return true;
        }
        Szkeleton.printIndentation();
        System.out.println("<false");
        Szkeleton.indentation--;
        return false;
    }

    /**
     * Spóra, ami paraméterként kapott a függvényen, hozzáadása a Tektonra
     * @param s, a spórát ami hozzáadjuk a tektonhoz
     */
    public void addSpore(Spore s){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.addSpore(): void");
        Szkeleton.printIndentation();

        spore.addFirst(s);

        System.out.println("<");
        Szkeleton.indentation--;

    }

    /**
     * A Tektonnak a szomszéd tektonok frissítése
     * @param newAdd a tektonok amiket hozzáadjuk a szomszéd listához
     * @param delete a tektonok amiket kitöröljük a szomszéd listából
     */
    
    public void updateNeighbor(List<Tekton> newAdd, List<Tekton> delete){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.updateNeighbor(): void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }

    /**
     * A tektonnak a szomszédos Tektonok lekérdezése. Ami vissza ad egy List<Tekton> értéket.
     * @return szomszédos Tekton Listában visszaadja
     */
    public List<Tekton> getNeighbors() {
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.getNeighbors(): List<Tekton>");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
        return neighbors;
    }

    /**
     * Tekont kitörlése a szomszédos tektonok listából
     * @param t, tektont amit kitöröljük a szomszéd listából
     */
    public void deleteNeighbor(Tekton t){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.deleteNeighbor(): void");
        Szkeleton.printIndentation();

        neighbors.remove(t);

        System.out.println("<");
        Szkeleton.indentation--;
    }

    /**
     * Tekon hozzáadás a szomszéd tektonok listából
     * @param t, tektont amit hozzáadjuk a szomszéd listára
     */
    public void addNeighbor(Tekton t) {
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.addNeighbor(): void");
        Szkeleton.printIndentation();

        neighbors.add(t);

        System.out.println("<");
        Szkeleton.indentation--;
    }

    /**
     * insect hozzáadás a tektonra, ami csak egy lehetséges
     * @param i, rovart amit hozzáadunk a tektonra
     */
    public void addInsect(Insect i) {
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.addInsect(): void");
        Szkeleton.printIndentation();

        insect = i;

        System.out.println("<");
        Szkeleton.indentation--;
    }

    /**
     * insect kitörlése a tektonból.
     */
    public void removeInsect() {
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.removeInsect(): void");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
    }

    /**
     * A spórákat felhasználva, létre hozza a tekton egy gombaTest
     */
    public void useSporeToGrow(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">Tekton.useSporeToGrow(): void");
        Szkeleton.printIndentation();
        System.out.println("<");
        Szkeleton.indentation--;
        for(int i = 0; i < 3; i++)
            popSpore();
    }

    public void absorb() {
        List<MushroomThread> temp = new ArrayList<>();
        for(MushroomThread mt : mushroomThread) {
            if(!mt.isLifeSupport()) {
                mt.lifeReduce();
                if(mt.getLife() <= 0)
                    temp.add(mt);
            }
        }
        for(MushroomThread mt : temp) {
            mushroomThread.remove(mt);
        }
    }

}

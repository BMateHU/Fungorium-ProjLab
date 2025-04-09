package com.beingchilling.model;

import com.beingchilling.controller.TektonController;
import com.beingchilling.view.TektonView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Ez egy Tekton osztaly, ami tárolja a Tektonnak a szomszédjait egy Listában
 * , Spórákat, szintén egy Listában, ami rajta van a Tektonon és MushroomThreadet,
 *  illetve egyetlen egy darab Insectet is tárol.
 */
public class Tekton implements TektonController, TektonView {
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
        
        System.out.println(">Tekton.addThread(): Boolean");
        if(!mushroomThread.isEmpty()){
            
            System.out.println("<false");
            
            return false;
        }
        

        mushroomThread.add(mt);

        System.out.println("<true");
        

        return true;
    }
    /**
     * Tekton kettétörese, ha a Tektonon van Gombafonal és Spóra akkor azokat eltávolítjuk a Tektonról
     * Létre hozzuk egy új Tektont és ezt az új létre hozott tekton hozzáadjuk a 
     * jelenlegi Tekton neighborhozÉs az új létre hozott Tektonnak is hozzáadjuk a 
     * jelenlegi Tektont mint neighborként
     */
   public boolean tektonBreak(){
        
        System.out.println(">Tekton.tektonBreak(): void");
        if(insect != null || mushroomThread.isEmpty()){
            

            System.out.println("<");
            
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
        

        System.out.println("<");
        
        return true;
   }

    /**
     * A függvény megmondja a Tektonon lévő spóra
     * PL: Gombatest nélküli vagy Gombafonal nélküli
     * @return vissza adja a Sporát
     */
    public Spore popSpore(){
        
        System.out.println(">Tekton.PopSpore(): Spore");
        

        System.out.println("<Spore:spore");
        

        return spore.removeFirst();
    }
    
    /**
     * A Tektonon lévő spórák eltávolítása illetve megsemmisitése 
     */
    public void clearSpore(){
        
        System.out.println(">Tekton.clearSpore(): void");
        

        System.out.println("<");
        

    }

    /**
     * A GombaTest növeszthető-e a Tektonon. A GombaTestet növesztéshez,
     * Szükséges három darab spóra és rendelkezik gombaFonal a Tektonon.
     * @param ms tárolja a létre hozott gombaTestet
     * @return gombaTestnek a növesztés sikerességét
     */
    public boolean growMushroomBody(MushroomSpecies ms){
        
        System.out.println(">Tekton.growMushroomBody(): boolean");
        if(spore.size()>=3 && !mushroomThread.isEmpty()) {
            useSporeToGrow();
            MushroomBody M = new MushroomBody(this);
            ms.addMushroomBody(M);
            mushroomBody = M;
            

            System.out.println("<result:true");
            

            return true;
        }
        

        System.out.println("<result:false");
        

        return false;

    }

    /**
     * A függvény visszaadja egy List<Tekton> típusú értéket, ami megmondja, 
     * hogy milyen fonalasTektonnal van szomszédban.
     * @return visszaadja szomszédos FonalasTektonokat 
     */
    
    public List<Tekton> getNeighborWithThread(){
        
        System.out.println(">Tekton.getNeighborWithThread(): List<Tekton>");
        
        System.out.println("<");
        
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
        
        System.out.println(">Tekton.checkNeighbor(): boolean");
        if(neighbors.contains(t))
        {
            
            System.out.println("<result:true");
            
            return true;
        }
        
        System.out.println("<false");
        
        return false;
    }

    /**
     * Spóra, ami paraméterként kapott a függvényen, hozzáadása a Tektonra
     * @param s, a spórát ami hozzáadjuk a tektonhoz
     */
    public void addSpore(Spore s){
        
        System.out.println(">Tekton.addSpore(): void");
        

        spore.addFirst(s);

        System.out.println("<");
        

    }

    /**
     * A Tektonnak a szomszéd tektonok frissítése
     * @param newAdd a tektonok amiket hozzáadjuk a szomszéd listához
     * @param delete a tektonok amiket kitöröljük a szomszéd listából
     */
    
    public void updateNeighbor(List<Tekton> newAdd, List<Tekton> delete){
        
        System.out.println(">Tekton.updateNeighbor(): void");
        

        System.out.println("<");
        

    }

    /**
     * A tektonnak a szomszédos Tektonok lekérdezése. Ami vissza ad egy List<Tekton> értéket.
     * @return szomszédos Tekton Listában visszaadja
     */
    public List<Tekton> getNeighbors() {
        
        System.out.println(">Tekton.getNeighbors(): List<Tekton>");
        
        System.out.println("<");
        
        return neighbors;
    }

    /**
     * Tekont kitörlése a szomszédos tektonok listából
     * @param t, tektont amit kitöröljük a szomszéd listából
     */
    public void deleteNeighbor(Tekton t){
        
        System.out.println(">Tekton.deleteNeighbor(): void");
        

        neighbors.remove(t);

        System.out.println("<");
        
    }

    /**
     * Tekon hozzáadás a szomszéd tektonok listából
     * @param t, tektont amit hozzáadjuk a szomszéd listára
     */
    public void addNeighbor(Tekton t) {
        
        System.out.println(">Tekton.addNeighbor(): void");
        

        neighbors.add(t);

        System.out.println("<");
        
    }

    /**
     * insect hozzáadás a tektonra, ami csak egy lehetséges
     * @param i, rovart amit hozzáadunk a tektonra
     */
    public void addInsect(Insect i) {
        
        System.out.println(">Tekton.addInsect(): void");
        

        insect = i;

        System.out.println("<");
        
    }

    /**
     * insect kitörlése a tektonból.
     */
    public void removeInsect() {
        
        System.out.println(">Tekton.removeInsect(): void");
        
        System.out.println("<");
        
    }

    /**
     * A spórákat felhasználva, létrehoz a tektonon egy gombatestet
     */
    public void useSporeToGrow(){
        
        System.out.println(">Tekton.useSporeToGrow(): void");
        
        System.out.println("<");
        
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

package com.beingchilling.model;

import com.beingchilling.controller.TektonController;
import com.beingchilling.game.GameModel;
import com.beingchilling.view.TektonView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Ez egy Tekton osztaly, ami tárolja a Tektonnak a szomszédjait egy Listában, Spórákat, szintén egy Listában, ami rajta van a Tektonon és MushroomThreadet,
 *  illetve egyetlen egy darab Insectet is tárol.
 */
public class Tekton implements TektonController, TektonView {
    /**
     * A Tekton osztálynak a szomszédjai, ami Listában tároljuk.
     */
    protected final List<Tekton> neighbors;
    /**
     * A Tektonon lévő spórák.
     */
    protected final List<Spore> spore;

    /// A Tektonon lévő gombatest.
    protected MushroomBody mushroomBody;
    /**
     * A Tektonon lévő MushroomThread, azaz gombaFonal.
     */
    protected final List<MushroomThread> mushroomThread;
    /**
     * A Tektonon lévő rovar, ami csak egy darab lehet a tektonon.
     */
    protected Insect insect;

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
        if(!mushroomThread.isEmpty()){
            return false;
        }
        mushroomThread.add(mt);
        mt.setLocation(this);
        return true;
    }
    /**
     * Tekton kettétörese, ha a Tektonon van Gombafonal és Spóra akkor azokat eltávolítjuk a Tektonról
     * Létre hozzuk egy új Tektont és ezt az új létre hozott tekton hozzáadjuk a 
     * jelenlegi Tekton neighborhozÉs az új létre hozott Tektonnak is hozzáadjuk a 
     * jelenlegi Tektont mint neighborként
     */
    public Tekton tektonBreak() {
        if (insect != null || mushroomBody != null) {
            return null;
        }
        if(GameModel.randomSwitch && !neighbors.isEmpty())
        {
            Random random = new Random();
            int r1, r2;

            r1 = random.nextInt(neighbors.size() - 1);
            do {
                r2 = random.nextInt(neighbors.size() - 1);
            } while (r1 == r2);

            int num1 = Math.min(r1, r2);
            int num2 = Math.max(r1, r2);

            List<Tekton> randomNeighbours = this.neighbors.subList(num1, num2);

            clearSpore();
            getThreads().clear();
            Tekton T2 = new Tekton();

            T2.neighbors.add(this);
            neighbors.add(T2);
            updateNeighbor(new ArrayList<>(), randomNeighbours);
            T2.updateNeighbor(randomNeighbours, new ArrayList<>());
            return T2;
        }
        else {
            clearSpore();
            getThreads().clear();
            Tekton T2 = new Tekton();
            T2.neighbors.add(this);
            neighbors.add(T2);
            updateNeighbor(new ArrayList<>(), new ArrayList<>());
            return T2;
        }
    }

    /**
     * A függvény megmondja a Tektonon lévő spóra
     * PL: Gombatest nélküli vagy Gombafonal nélküli
     * @return vissza adja a Sporát
     */
    public Spore popSpore(){
        return spore.removeFirst();
    }
    
    /**
     * A Tektonon lévő spórák eltávolítása illetve megsemmisitése 
     */
    private void clearSpore(){
        spore.clear();
    }

    /**
     * A GombaTest növeszthető-e a Tektonon. A GombaTestet növesztéshez,
     * Szükséges három darab spóra és rendelkezik gombaFonal a Tektonon.
     * @param ms tárolja a létre hozott gombaTestet
     * @return gombaTestnek a növesztés sikerességét
     */
    public boolean growMushroomBody(MushroomSpecies ms){
        if(spore.size()>=3 && !mushroomThread.isEmpty()) {
            useSporeToGrow();
            MushroomBody M = new MushroomBody(this);
            ms.addMushroomBody(M);
            mushroomBody = M;
            return true;
        }
        return false;
    }

    @Override
    public void addMushroom(MushroomBody mushroomBody) {
        this.mushroomBody = mushroomBody;
    }

    /**
     * A függvény visszaadja egy List<Tekton> típusú értéket, ami megmondja, 
     * hogy milyen fonalasTektonnal van szomszédban.
     * @return visszaadja szomszédos FonalasTektonokat 
     */
    
    public List<Tekton> getNeighborWithThread(){
        List<Tekton> temp = new ArrayList<>();
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
        return neighbors.contains(t);
    }

    /**
     * Spóra, ami paraméterként kapott a függvényen, hozzáadása a Tektonra
     * @param s, a spórát ami hozzáadjuk a tektonhoz
     */
    public void addSpore(Spore s){
        spore.add(s);
    }

    /**
     * A Tektonnak a szomszéd tektonok frissítése
     * @param newAdd a tektonok amiket hozzáadjuk a szomszéd listához
     * @param delete a tektonok amiket kitöröljük a szomszéd listából
     */
    
    public void updateNeighbor(List<Tekton> newAdd, List<Tekton> delete){
        if(!newAdd.isEmpty()){
            neighbors.addAll(newAdd);
            for(Tekton t : newAdd)
                t.addNeighbor(this);
        }
        if(!delete.isEmpty()) {
            neighbors.removeAll(delete);
            for (Tekton t : delete)
                t.deleteNeighbor(this);
        }
    }

    /**
     * A tektonnak a szomszédos Tektonok lekérdezése. Ami vissza ad egy List<Tekton> értéket.
     * @return szomszédos Tekton Listában visszaadja
     */
    public List<Tekton> getNeighbors() {
        return neighbors;
    }

    @Override
    public List<Spore> getSpores() {
        return spore;
    }

    public Insect getInsect() {
        return insect;
    }

    @Override
    public List<MushroomThread> getThreads() {
        return mushroomThread;
    }

    @Override
    public MushroomBody getBody() {
        return mushroomBody;
    }

    /**
     * Tekont kitörlése a szomszédos tektonok listából
     * @param t, tektont amit kitöröljük a szomszéd listából
     */
    public void deleteNeighbor(Tekton t){
        neighbors.remove(t);
    }

    /**
     * Tekon hozzáadás a szomszéd tektonok listából
     * @param t, tektont amit hozzáadjuk a szomszéd listára
     */
    public void addNeighbor(Tekton t) {
        neighbors.add(t);
    }

    /**
     * insect hozzáadás a tektonra, ami csak egy lehetséges
     * @param i, rovart amit hozzáadunk a tektonra
     */
    public void addInsect(Insect i) {
        insect = i;
    }

    /**
     * insect kitörlése a tektonból.
     */
    public void removeInsect() {
        insect = null;
    }

    /**
     * A spórákat felhasználva, létrehoz a tektonon egy gombatestet
     */
    private void useSporeToGrow(){
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
            GameModel.gameObjects.removeByV(mt);
            mushroomThread.remove(mt);
        }
    }

    @Override
    public TektonView toView() {
        return this;
    }

    @Override
    public TektonController toController() {
        return this;
    }

    /**
     * Kimeneti nyelvvel megeggyező stringgé írja át az adott objectet.
     * @return A szöveg amit kikéne írni
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("type=N");

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

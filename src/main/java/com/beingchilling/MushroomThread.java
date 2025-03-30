package com.beingchilling;

import java.util.ArrayList;
import java.util.List;

/**
 * Az osztály a gombatestek által létrehozott gombafonalak funkciójait valósítja meg.
 */
public class MushroomThread {
    /**
     * Eltárolja az előtte lévő gombafonalat.
     */
    private MushroomThread preGrowed;
    /**
     *  Eltárolja az utána lévő gombafonalakat.
     */
    private List<MushroomThread> nextGrowed;
    /**
     * Eltárolja melyik tektonon van a gombafonal.
     */
    private Tekton location;

    /// Stores mushroom thread's life
    private int life;

    /// True if the thread is on life support, otherwise false
    private boolean lifeSupport;

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isLifeSupport() {
        return lifeSupport;
    }

    public void setLifeSupport(boolean lifeSupport) {
        this.lifeSupport = lifeSupport;
    }

    public MushroomThread() {
        preGrowed = null;
        nextGrowed = new ArrayList<MushroomThread>();
        life = 3;
        lifeSupport = false;
    }

    /**
     * Ha fonal tektonjára spóra kerül, akkor egy ideig magától hosszabulni fog a fonal.
     */
    public void speedUpGrowing(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">MushroomThread.speedUpGrowing():void");
        Szkeleton.printIndentation();

        System.out.println("<");
        Szkeleton.indentation--;

    }

    /**
     * A listához hozzáadja az adott fonalat.
     * @param thread adott fonal
     */
    public void addThread(MushroomThread thread){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">MushroomThread.addThread(MushroomThread thread):void");
        Szkeleton.printIndentation();
        System.out.println("<");

        nextGrowed.add(thread);
        thread.preGrowed = this;

        Szkeleton.indentation--;
    }

    /**
     * Megnézi, hogy melyik gombatesthez tartozik a jelen lévő gombafonal.
     * @return A gombatest amihez a fonal tartozik
     */
    public MushroomBody checkOwner(){
        Szkeleton.indentation++;
        Szkeleton.printIndentation();
        System.out.println(">MushroomThread.checkOwner():MushroomBody");
        Szkeleton.printIndentation();

        System.out.println("<mushroom: MushroomBody");
        Szkeleton.indentation--;

        return new MushroomBody();
    }

    public void disconnectThread() {
        preGrowed.nextGrowed.remove(this);
        preGrowed = null;
    }

    public void lifeReduce() {
        life--;
    }

    public void absorbInsect() {
        for(int i=0; i<3; i++)
            location.addSpore(new Spore(1));
        location.growMushroomBody(new MushroomSpecies());
    }

    public void destroy() {
        location.mushroomThread.remove(this);
        location = null;
    }

    public void remove(MushroomThread thread) {

    }

    public MushroomThread getPreGrowed() {
        return preGrowed;
    }

    public void setPreGrowed(MushroomThread preGrowed) {
        this.preGrowed = preGrowed;
    }

    public List<MushroomThread> getNextGrowed() {
        return nextGrowed;
    }

    public void setNextGrowed(List<MushroomThread> nextGrowed) {
        this.nextGrowed = nextGrowed;
    }

    public Tekton getLocation() {
        return location;
    }

    public void setLocation(Tekton location) {
        this.location = location;
    }
}

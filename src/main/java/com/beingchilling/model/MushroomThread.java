package com.beingchilling.model;

import com.beingchilling.controller.MushroomThreadController;
import com.beingchilling.view.MushroomThreadView;

import java.util.ArrayList;
import java.util.List;

/**
 * Az osztály a gombatestek által létrehozott gombafonalak funkciójait valósítja meg.
 */
public class MushroomThread implements MushroomThreadController, MushroomThreadView {
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

    public boolean isLifeSupport() {
        return lifeSupport;
    }

    public void setLifeSupport(boolean lifeSupport) {
        this.lifeSupport = lifeSupport;
    }

    public MushroomThread() {
        preGrowed = null;
        nextGrowed = new ArrayList<>();
        life = 3;
        lifeSupport = false;
    }

    /**
     * Ha fonal tektonjára spóra kerül, akkor egy ideig magától hosszabulni fog a fonal.
     */
    public void speedUpGrowing(){

    }

    /**
     * A listához hozzáadja az adott fonalat.
     * @param thread adott fonal
     */
    public void addThread(MushroomThread thread){
        nextGrowed.add(thread);
        thread.preGrowed = this;
    }

    public void growThread(Tekton source, Tekton target) {

    }

    /**
     * Megnézi, hogy melyik gombatesthez tartozik a jelen lévő gombafonal.
     * @return A gombatest amihez a fonal tartozik
     */
    public MushroomBody checkOwner(){
        return new MushroomBody(new Tekton());
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
        location.getThreads().remove(this);
        location = null;
    }

    public void remove(MushroomThread thread) {

    }

    public MushroomThread getPreGrowed() {
        return preGrowed;
    }

    public List<MushroomThread> getNextGrowed() {
        return nextGrowed;
    }

    public Tekton getLocation() {
        return location;
    }

    public void setLocation(Tekton location) {
        this.location = location;
    }

    @Override
    public MushroomThreadView toView() {
        return this;
    }

    @Override
    public MushroomThreadController toController() {
        return this;
    }
}

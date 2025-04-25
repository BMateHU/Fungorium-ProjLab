package com.beingchilling.model;

import com.beingchilling.controller.MushroomThreadController;
import com.beingchilling.game.GameModel;
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
    private MushroomThread prevGrowed;
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
        prevGrowed = null;
        nextGrowed = new ArrayList<>();
        life = 3;
        lifeSupport = false;
    }


    /**
     * A listához hozzáadja az adott fonalat.
     * @param thread adott fonal
     */
    public void addThread(MushroomThread thread){
        nextGrowed.add(thread);
        thread.prevGrowed = this;
        thread.lifeSupport = lifeSupport;
    }


    public boolean growThread(Tekton target) {
        MushroomThread MT2 = new MushroomThread();
        if(target.addThread(MT2)){
            this.addThread(MT2);
            return true;

        }
        return false;
    }

    /**
     * Megnézi, hogy melyik gombatesthez tartozik a jelen lévő gombafonal.
     * @return A gombatest amihez a fonal tartozik
     */
    public MushroomBody checkOwner(){
        return new MushroomBody(new Tekton());
    }

    public void disconnectThread() {
        if(prevGrowed == null){
            return;//maybe need throw
        }
        prevGrowed.nextGrowed.remove(this);
        prevGrowed = null;
        for(MushroomThread thread : nextGrowed){
            if(thread.getLocation().getBody() != null)
                return;
        }
        for(MushroomThread thread : nextGrowed){
            thread.lifeSupport = false;
        }
    }

    public void lifeReduce() {
        life--;
    }

    public MushroomBody absorbInsect() throws NullPointerException {
        if(location.getInsect() == null){
            throw new NullPointerException("Nincs Insect");
        }
        MushroomBody mb = new MushroomBody(location);
        location.addMushroom(mb);
        location.getInsect().destroy();
        return mb;

    }

    public void destroy() {
        if(prevGrowed != null)
            prevGrowed.nextGrowed.remove(this);

        if(!nextGrowed.isEmpty()) {
            for (MushroomThread thread : nextGrowed) {
                if(!thread.nextGrowed.isEmpty())
                    thread.prevGrowed = null;
            }
        }
        location.getThreads().remove(this);
        location = null;
    }

    public void remove(MushroomThread thread) {
        nextGrowed.remove(thread);
    }

    public MushroomThread getPrevGrowed() {
        return prevGrowed;
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

    @Override
    public String toString() {
        StringBuilder prev = new StringBuilder("; prev");
        StringBuilder next = new StringBuilder("; next");
        StringBuilder current = new StringBuilder("life="+life+";lifeSupport="+lifeSupport+"; tekton="+ GameModel.gameObjects.getK(location));
        if(prevGrowed != null) {
            prev.append(GameModel.gameObjects.getK(prev));
            current.append(prev);
        }
        if(!next.isEmpty())
        {
            for(MushroomThread thread : nextGrowed)
            {
                next.append(GameModel.gameObjects.getK(thread)).append(", ");
            }
            next.delete(next.length()-2, next.length());
            current.append(next);
        }

        return current.toString();
    }
}
